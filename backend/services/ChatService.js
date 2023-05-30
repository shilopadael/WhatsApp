const User = require('../models/User');
const Chats = require('../models/Chat');
const Message = require('../models/Message');
const AllChats = require('../models/AllChats');
const {getUserName} = require('./token');
const UserPassName = require('../models/UserPassName');
const { ObjectId } = require('mongodb');
const { all } = require('moongose/routes');
const { trusted } = require('mongoose');
const Chat = require('../models/Chat');


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
            const NOJchat = await Chats.findOne({_id: chat});

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
  
              let chatToAdd = {
                id: NOJchat.id,
                user: {
                  username: otherUserDetails.username,
                  profilePic: otherUserDetails.profilePic,
                  displayName: otherUserDetails.displayName
                },
                lastMessage: null
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
            if (userInChat.username === userToAdd.username) {
              return { error: 'Chat already exists' };
            }
          }
        }
      }



  
      const userPassName2 = await User.findOne({ username: thisUser });

      //all chats in system
      const chat = await Chats.find({});
      const id = generateNewId(chat);
      const newChat = new Chats({
        id: id,
        users: [userToAdd._id, userPassName2._id],
        messages: []
      });
  
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

  
      return toSend;
    } catch (error) {
      // Handle the error appropriately
      return { error: 'An error occurred' };
    }
  };

const getChatById = async (req, res) => {

    const id1 = req.params.id;
    //find the chat with all the messages.
    const chat = await Chats.findOne({id: id1});
    let newChat = {id: chat.id, users: [], messages: []};

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
    const mesggesId = req.params.id;
    const username = getUserName(req);

    //get the chat by the id chat
    const chat = await Chats.findOne({id: mesggesId});

    return chat.messages;
}

const addMessage = async (req, res) => { 
try {    const mesggesId = req.params.id;
    const msgGot = req.body.msg;
    const username = getUserName(req);

    //get the chat by the id chat
    const chat = await Chats.findOne({id: mesggesId});

    const user = await User.findOne({username: username});

    let allMessages = chat.messages.map(idToMesagges);
    allMessages = await Promise.all(allMessages);

    const Number = generateNewId(allMessages);

    const mess = new Message({
        id: generateNewId(allMessages),
        created: Date.now(),
        sender: user,
        content: msgGot
    });
    await mess.save();

    chat.messages.push(mess._id);
    await chat.save();

    return mess;
} catch (error) {
    return { error: 'An error occurred while sending message. go away.' };
}


}


async function idToMesagges(id) {
    const message = await  Message.findOne({_id: id});
    return {id: message.id,
            created: message.created,
            sender: await idToUser(message.sender),
            content: message.content
        };
}

async function idToUser(id) {
    const user = await  User.findOne({_id: id});
    return {id: user.id,
            username: user.username,
            displayName: user.displayName,
            profilePic: user.profilePic
        };
}


const deleteChatById = async (req, res) => { 
    const idToDelete = req.params.id;
    const chatToDelete = await Chats.findOne({id: idToDelete});

    const user = getUserName(req);
    const userToDelete = await AllChats.findOne({username: user});

    userToDelete.chats.pull(chatToDelete._id);
    userToDelete.save();

    return {success: true};

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



module.exports = { getChats, addChat, getChatById, deleteChatById,getMessages, addMessage};