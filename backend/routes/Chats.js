const chatControl = require ('../controllers/Chats');
const {isLogIn} = require('../services/token');

const express = require('express');
var router = express.Router();

router.route('/')
        .get(isLogIn, chatControl.getChat)
        .post(isLogIn, chatControl.addChat);

router.route('/:id/Messages')
        .get(chatControl.getMessages)
        .post(chatControl.addMessage);

router.route('/:id')
        .get(chatControl.getChatById)
        .delete(chatControl.deleteChatById);



module.exports = router;