const { json } = require('body-parser');
const chatService = require('../services/ChatService');


const getChat = async (req,res) => {
    const chat = await chatService.getChats(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        res.status(200).json(chat);
    }
}

const addChat = async (req,res) => {
    const chat = await chatService.addChat(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        res.status(200).json(chat);
    }

}
const getChatById = async (req,res) => {
    const chat = await chatService.getChatById(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        res.status(200).json(chat);
    }
}

const deleteChatById = async (req,res) => { 
    const chat = await chatService.deleteChatById(req);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }
}

const getMessages = async (req,res) => {
    const messages = await chatService.getMessages(req);
    if (messages.error) {
        res.status(400).json(messages);
    }
    else {
        res.status(200).json(messages);
    }
}

const addMessage = async (req,res) => {
    const message = await chatService.addMessage(req);
    if (message.error) {
        res.status(400).json(message.error);
    }
    else {
        res.status(200).json(message);
    }
}


module.exports = { getChat, addChat, getChatById, deleteChatById, getMessages , addMessage};