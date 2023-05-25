const chatControl = require ('../controllers/Chats');

const express = require('express');
var router = express.Router();

router.route('/')
        .get(chatControl.getChats)
        .post(chatControl.addChat);

router.route('/:id')
        .get(chatControl.getChatById)
        .delete(chatControl.deleteChatById);