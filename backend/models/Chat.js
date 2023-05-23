const mongoose = require('mongoose');
const User = require('./User');
const Message = require('./Message');
const Schema = mongoose.Schema;

// this is the Chat schema
const Chat = new Schema(
    {
        id: {
            type: Number,
            required: true
        },
        users: {
            type: [User],
            required: true
        },
        messages: {
            type: [Message],
            required: true
    }
});

module.exports = mongoose.model('Chat', Chat);