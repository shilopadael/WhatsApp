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
            nullable: true

        },
        messages: {
            nullable: true,
            type: [Message],
    }
});

module.exports = mongoose.model('Chat', Chat);