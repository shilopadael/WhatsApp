const User = require('../models/User');
const Chats = require('../models/AllChats');
const {getTokenFromHeaders} = require('./token');
const UserPassName = require('../models/UserPassName');

// get all chats
const getChats = async (req, res) => {
    try {
        const username = getTokenFromHeaders(req);
        const chats = await Chats.find({username: username});
        res.status(200).json(chats);
    } catch (error) {
        res.status(400).json(error);
    }
}

// add a chat
const addChat = async (req, res) => {
    //getting the new user
    const user = UserPassName.find(req.body.username);
    if (!user) {
        res.status(400).send('Username does not exist');
        return;
    }
    //create new object of user to add to chat
    const newUser = new User({username: user.username, displayName: user.displayName, profilePic: user.profilePic});

    //getting the current user
    const username = getTokenFromHeaders(req);
    const chat = await Chats.find({username: username});
    //adding the new user to the chat
    chat.users.push(newUser);

    //saving the chat
    try {
        await chat.save();
        res.status(200).json(chat);
    }
    catch (error) {
        res.status(400).json(error);
    }

}

const getChatById = async (req, res) => {
    try {
        const chat = await Chats.findById(req.params.id);
        res.status(200).json(chat);
    } catch (error) {
        res.status(400).json(error);
    }
}

const deleteChatById = async (req, res) => { 
    try {
        const chat = await Chats.findByIdAndDelete(req.params.id);
        res.status(200).json(chat);
    } catch (error) {
        res.status(400).json(error);
    }
}


module.exports = { getChats, addChat, getChatById, deleteChatById};