const mongoose = require('mongoose');
const Chat = require('./Chat');
const Schema = mongoose.Schema;

// this is the Chat schema
const AllChats = new Schema(
    {
        username: {
            type: Number,
            required: true
        },
        Chats: {
            type: [Chat],
            nullable: true
        }

});

module.exports = mongoose.model('AllChats', AllChats);