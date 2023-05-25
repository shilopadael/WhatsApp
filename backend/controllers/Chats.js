const chatService = require('../services/ChatService');


const getChat = async (req,res) => {
    const chat = await chatService.getChat(username);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }
}

const addChat = async (req,res) => {
    const chat = await chatService.addChat(req.body);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }

}
const getChatById = async (req,res) => {
    const chat = await chatService.getChatById(req.params.id);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }
}

const deleteChatById = async (req,res) => { 
    const chat = await chatService.deleteChatById(req.params.id);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }
}


module.exports = { getChat, addChat, getChatById, deleteChatById };