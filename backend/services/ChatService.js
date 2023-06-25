const User = require('../models/User');
const Chats = require('../models/Chat');
const Message = require('../models/Message');
const AllChats = require('../models/AllChats');
const { getUserName } = require('./token');
const UserPassName = require('../models/UserPassName');
const Chat = require('../models/Chat');
const mongoose = require('mongoose');
const onlineConnections = require('../onlineConnection');
const FireBaseManager = require('./firebaseAdmin');


// get all chats
const getChats = async (req, res) => {
  try {
    const username = getUserName(req);

    if (!username) {
      return { error: 'Username does not exist' };
    } else {
      const user = await UserPassName.findOne({ username: username });

      const allChats = await AllChats.findOne({ username: username });

      const listOfChats = [];

      // Loop over all the chats
      for (const chat of allChats.chats) {
        //find the specific chat
        const NOJchat = await Chats.findOne({ _id: chat });

        let otherUser;

        // Loop over users in each chat and find the other user
        for (const user of NOJchat.users) {
          otherUser = await User.findOne({ _id: user });
          if (otherUser.username !== username) {
            break; // Found the other user, no need to continue the loop
          }
        }

        if (otherUser) {
          const otherUserDetails = await User.findOne({ username: otherUser.username });


          const content = await findLastMessage(NOJchat);
          const populate = await Message.populate(content, { path: 'sender' });
          //wait for sure that  content got his data from db

          let chatToAdd = {
            id: NOJchat.id,
            user: {
              username: otherUserDetails.username,
              profilePic: otherUserDetails.profilePic,
              displayName: otherUserDetails.displayName
            },
            lastMessage: populate
          };

          listOfChats.push(chatToAdd);
        }

      }

      return listOfChats;
    }
  } catch (error) {
    return { error: 'An error occurred' };
  }
};

//function that find the max id in chats.messges
async function findLastMessage(chat) {
  let max = 0;
  let id_Message = null;
  for (const message of chat.messages) {
    let message1 = await Message.findOne({ _id: message });
    if (message1.id > max) {
      max = message1.id;
      id_Message = message1;
    }
  }
  return id_Message;
}



const addChat = async (req, res) => {
  try {
    const thisUser = getUserName(req);
    const userToAdd = await User.findOne({ username: req.body.username });

    if (!userToAdd) {
      return { error: 'Username does not exist' };
    }


    //if he aske to add himself
    if (userToAdd.username === thisUser) {
      return { error: 'You cannot chat with yourself' };
    }

    //check if that user already have an open chat with the user he want to chat with
    const alreadyWithChat = await AllChats.findOne({ username: req.body.username });

    if (alreadyWithChat) {
      for (const chat of alreadyWithChat.chats) {
        let reallChat = await Chats.findOne({ _id: chat });
        for (const user of reallChat.users) {
          const userInChat = await User.findOne({ _id: user });
          if (userInChat.username === thisUser) {
            return { error: 'Chat already exists' };
          }
        }
      }
    }

    const userPassName2 = await User.findOne({ username: thisUser });

    //all chats in system
    const chat = await Chats.find({});
    const id = generateNewId(chat);
    let data = {
      id: id,
      users: [userToAdd._id, userPassName2._id],
      messages: []
    }
    const newChat = new Chats(data);

    await newChat.save();


    let allChats = await AllChats.findOne({ username: thisUser });
    allChats.chats.push(newChat);
    await allChats.save();

    allChats = await AllChats.findOne({ username: req.body.username });
    allChats.chats.push(newChat);
    await allChats.save();

    const toSend = {
      id: id,
      user: {
        username: userToAdd.username,
        profilePic: userToAdd.profilePic,
        displayName: userToAdd.displayName
      }
    };

    const onlineUsers = new onlineConnections();

    // checking if the other user is online
    let userToSend = onlineUsers.getOnlineUser(userToAdd.username);
    if(userToSend !== undefined && userToSend.id != null) {
      // from react
      let newData = {
        username: thisUser
      }
      userToSend.io.to(userToSend.id).emit('update-contact-list', newData);
    } else if(userToSend !== undefined) {
      // from android
      const firebase = new FireBaseManager();
      firebase.sendNotificationToUser({
        data: {
          // Add your custom data fields here
          extra: "new-contact",
          username: `${thisUser}`
        },
        notification: {
          title: 'New Chat',
          body: `You have a new chat with ${thisUser}`
        },
        token: userToSend.firebaseToken // Replace with the FCM device token of the recipient
      });

    }

    return toSend;
  } catch (error) {
    // Handle the error appropriately
    return { error: 'An error occurred' };
  }
};

const getChatById = async (req, res) => {

  const id1 = req.params.id;
  //find the chat with all the messages.
  const chat = await Chats.findOne({ id: id1 });
  let newChat = { id: chat.id, users: [], messages: [] };

  // find all the users that conatin the user
  const userPromises = chat.users.map(idToUser);

  //find all the messages that conatin the user
  const messagePromises = chat.messages.map(idToMesagges);

  newChat.users = await Promise.all(userPromises);
  newChat.messages = await Promise.all(messagePromises);
  newChat.id = chat.id;

  //fins all the messages that conatin the user

  return newChat;


}

const getMessages = async (req, res) => {
  // [
  //     {
  //       "id": 76,
  //       "created": "2023-06-18T01:12:26.5124551",
  //       "sender": {
  //         "username": "omer5"
  //       },
  //       "content": "dasdasD"
  //     }
  //   ]
  const mesggesId = req.params.id;
  const username = getUserName(req);

  //get the chat by the id chat
  const chat = await Chats.findOne({ id: mesggesId });
  if (chat !== null) {
    const chatMessages = await Message.populate(chat, { path: 'messages', populate: { path: 'sender' } });
    const messages = chatMessages.messages;
    let newMessages = messages.map(message => {
      return {
        id: message.id,
        created: message.created,
        sender: {
          username: message.sender.username
        },
        content: message.content
      }
    });
    return newMessages;
  } else {
    return { error: 'Chat does not exist' };
  }
}

const addMessage = async (req, res) => {
  try {
    const mesggesId = req.params.id;
    const msgGot = req.body.msg;
    const username = getUserName(req);

    //get the chat by the id chat
    const chat = await Chats.findOne({ id: mesggesId });

    const user = await User.findOne({ username: username });

    let allMessages = chat.messages.map(idToMesagges);
    allMessages = await Promise.all(allMessages);

    const Number = generateNewId(allMessages);

    const mess = new Message({
      id: Number,
      created: Date.now(),
      sender: user,
      content: msgGot
    });
    await mess.save();

    chat.messages.push(mess._id);
    await chat.save();

    // sending to the other person in the chat, if it is from andorid

    if (req.headers['devicetype'] === 'Android') {
      let users = await User.populate(chat, { path: 'users' });
      users = users.users;
      const sendUserScheme = users.filter(user => user.username !== username);
      const onlineUsers = new onlineConnections();
      let userToSend = onlineUsers.getOnlineUser(sendUserScheme[0].username);
      if (userToSend !== undefined && userToSend.id != null) {
        // sending the message to the other user
        // casting the mesggesId to number
        let number = parseInt(mesggesId);
        let data = {
          data: {
            created: mess.created,
            sender: user,
            content: msgGot,
            id: Number
          },
          receiverUsername: sendUserScheme[0].username,
          id: number
        }
        userToSend.io.to(userToSend.id).emit('receive-message', data);
      } else if (userToSend !== undefined) {
        // from android to android
        // sending the message to the other user
        let number = parseInt(mesggesId);
        let data = {
          data: {
            created: mess.created,
            sender: user,
            content: msgGot,
            id: Number
          },
          receiverUsername: sendUserScheme[0].username,
          id: number
        }

        const firebase = new FireBaseManager();
        const message = {
          data: {
            // Add your custom data fields here
            chatId: `${mesggesId}`,
            sender: username
          },
          notification: {
            title: username,
            body: msgGot
          },
          token: userToSend.firebaseToken // Replace with the FCM device token of the recipient
        };
        console.log(message)
        firebase.sendNotificationToUser(message);
      }
    }
    return mess;
  } catch (error) {
    return { error: 'An error occurred while sending message. go away.' };
  }


}


async function idToMesagges(id) {
  const message = await Message.findOne({ _id: id });
  return {
    id: message.id,
    created: message.created,
    sender: await idToUser(message.sender),
    content: message.content
  };
}

async function idToUser(id) {
  const user = await User.findOne({ _id: id });
  return {
    id: user.id,
    username: user.username,
    displayName: user.displayName,
    profilePic: user.profilePic
  };
}


const deleteChatById = async (req) => {
  // deleting the chat from the database
  const idToDelete = req.params.id;
  const chatToDelete = await Chats.findOne({ id: idToDelete });

  // checking if the current user that want to delete the chat is one of the users in the chat
  const username = getUserName(req);
  let lst = []
  let flag = false;
  // iterating throuw the users in the chat
  for (const user of chatToDelete.users) {
    let _user = await User.findOne({ _id: user });
    lst.push(_user.username);
    if (_user.username === username) {
      flag = true;
    }
  }
  if (flag === true) {
    for (const user of lst) {
      let allChats = await AllChats.findOne({ username: user });
      let index = allChats.chats.indexOf(chatToDelete._id);
      allChats.chats.splice(index, 1);
      await allChats.save();
    }

    console.log(lst);      
    const otherUsername = lst.filter(user => user !== username)[0];

    // deleting the chat messages from the database
    for (const message of chatToDelete.messages) {
      await Message.deleteOne({ _id: message });
    }
    await Chat.deleteOne({ _id: chatToDelete._id });

    const onlineUsers = new onlineConnections();

    let userToSend = onlineUsers.getOnlineUser(otherUsername);
    if(userToSend !== undefined && userToSend.id != null) {
      // from react
      let newData = {
        username: username
      }
      userToSend.io.to(userToSend.id).emit('update-contact-list', newData);
    } else if(userToSend !== undefined) {
      // from android
      const firebase = new FireBaseManager();
      firebase.sendNotificationToUser({
        data: {
          // Add your custom data fields here
          extra: "remove-contact",
          username: `${username}`
        },
        notification: {
          title: `${username}`,
          body: `Removed a chat with you`
        },
        token: userToSend.firebaseToken // Replace with the FCM device token of the recipient
      });

    }

    return { success: true };
  }
  return { success: false, error: 'The user is not allowed to delete this chat' };

}
function generateNewId(objectList) {
  if (objectList.length === 0) {
    // If the list is empty, return 1 as the new ID
    return 1;
  }

  // Find the maximum ID in the list
  const maxId = Math.max(...objectList.map((obj) => obj.id));

  // Generate the new ID by incrementing the maximum ID by 1
  const newId = maxId + 1;

  return newId;
}



module.exports = { getChats, addChat, getChatById, deleteChatById, getMessages, addMessage };