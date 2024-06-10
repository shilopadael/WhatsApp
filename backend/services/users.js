const User = require('../models/User');
const UserPassName = require('../models/UserPassName');
const AllChats = require('../models/AllChats');


const createUser = async (newUser) => {
    const { username, password, displayName, profilePic } = newUser;
    try {
        //check if username already exists
        const userPass = await getUserPassName(newUser);
        if(!userPass === null || !userPass === undefined || userPass.length !== 0 ) {
            return { error: 'Username already exists' };
        }
        if(userPass.username === newUser.username) {
            return { error: 'Username already exists' };
        }

        if(!newUser.username || !newUser.password) {
            throw new Error('Missing username or password');
        }
        else if( newUser.password.length < 8) {
            throw new Error('Password must be at least 8 characters');
        }
        else if( newUser.password.length > 20) {
            throw new Error('Password must be less than 20 characters');
        }
        //all the users that in the system
        const users = await User.find({});

        const id = generateNewId(users);
        const user = new UserPassName({
            id: id,
            username,
            password,
            displayName,
            profilePic
        });
          //save the user to Users
        const userNoPass = new User({
              id: id,
              username,
              displayName,
              profilePic
        });

        const allchat = new AllChats({
            username: username,
            chats: []
        });
        const savedAllChats = await allchat.save();
        const savedUser = await userNoPass.save();
        const savedNewUser = await user.save();

        console.log(savedUser);
        return { success: true, savedUser }

    } catch (error) {
        console.error('Error creating user:', error.message);
        return { success: false, error: error.message };

    }
}

const getUserPassName = async (user) => {
    return await UserPassName.find({ username: user.username });
}
const getUserByUsername = async (username) => {
    
    // const thisUser = getUserName(req);


    const userDetils =  await User.findOne({ username: username });
    if(userDetils && userDetils.length === 0) {
        return { error: 'Username does not exist' };
    } else{
        const user = {username: userDetils.username, 
            displayName: userDetils.displayName, 
            profilePic: userDetils.profilePic
        };
        return user;
    };
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

module.exports = { createUser , getUserPassName, getUserByUsername};