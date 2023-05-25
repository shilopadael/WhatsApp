const User = require('../models/User');
const UserPassName = require('../models/UserPassName');
const {getTokenFromHeaders} = require('../services/token');

const createUser = async (newUser) => {
    const { username, password, displayName, profilePic } = newUser;

    // Create a new instance of the UserPassName model with the provided fields
    const user = new UserPassName({
      username,
      password,
      displayName,
      profilePic
    });

    // Save the user to the database
    try {
        //check if username already exists
        const UserPassName = await getUserPassName(newUser);
        if(!UserPassName) {
            return { error: 'Username already exists' };
        }

        if(!newUser.username || !newUser.password) {
            throw new Error('Missing username or password');
        }
        else if( newUser.password.length < 8) {
            throw new Error('Password must be at least 8 characters');
        }
        else if( newUser.password.length > 72) {
            throw new Error('Password must be less than 72 characters');
        }
        const savedUser = await user.save();
        return { success: true, savedUser }

    } catch (error) {
        console.error('Error creating user:', error.message);
        return { error: error.message };

    }
}

const getUserPassName = async (user) => {
    return await UserPassName.find({ username: user.username });
}
const getUserByUsername = async (username) => {
    const userDetils =  await User.find({ username: username });
    if(userDetilds != getTokenFromHeaders) {
        return { error: 'Unauthorized' };
    }else {
        return { success: true, userDetils };
    }
}

module.exports = { createUser , getUserPassName, getUserByUsername};