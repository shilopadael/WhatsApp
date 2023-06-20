const { json } = require('body-parser');
const chatService = require('../services/ChatService');
const onlineConnection = require('../onlineConnection');


const getChat = async (req, res) => {
    const chat = await chatService.getChats(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        // let deviceType = req.headers['devicetype'];
        let chats;
        chats = chat.map(chat => {
            if(chat.lastMessage == null) {
                return {
                    id: chat.id,
                    user: chat.user,
                    lastMessage: null
                }
            }
            return {
                id: chat.id,
                user: chat.user,
                lastMessage: {
                    id: chat.lastMessage.id,
                    created: chat.lastMessage.created,
                    content: chat.lastMessage.content
                }
            }
        });
        res.status(200).json(chats);
    }
}

const addChat = async (req, res) => {
    const chat = await chatService.addChat(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        res.status(200).json(chat);
    }

}
const getChatById = async (req, res) => {
    const chat = await chatService.getChatById(req);
    if (chat.error) {
        res.status(400).json(chat.error);
    }
    else {
        res.status(200).json(chat);
    }
}

const deleteChatById = async (req, res) => {
    const chat = await chatService.deleteChatById(req);
    if (chat.error) {
        res.status(400).json(chat);
    }
    else {
        res.status(200).json(chat);
    }
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
    const messages = await chatService.getMessages(req);
    if (messages.error) {
        res.status(400).json(messages);
    }
    else {
        res.status(200).json(messages);
    }
}

const addMessage = async (req, res) => {
    const message = await chatService.addMessage(req);
    if (message.error) {
        res.status(400).json(message.error);
    }
    else {
        // todo
        res.status(200).json(message);
    }
}


module.exports = { getChat, addChat, getChatById, deleteChatById, getMessages, addMessage };