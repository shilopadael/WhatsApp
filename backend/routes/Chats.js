const chatControl = require ('../controllers/Chats');
const {isLogIn} = require('../services/token');

const express = require('express');
var router = express.Router();

router.route('/')
        .get(isLogIn, chatControl.getChat)
        .post(isLogIn, chatControl.addChat);

router.route('/:id/Messages')
        .get(isLogIn,chatControl.getMessages)
        .post(isLogIn, chatControl.addMessage);

router.route('/:id')
        .get(isLogIn, chatControl.getChatById)
        .delete(isLogIn, chatControl.deleteChatById);



module.exports = router;