const mongoose = require('mongoose');
const Chat = require('./Chat');
// const User = require('./User');
const Schema = mongoose.Schema;

// this is the Chat schema
const AllChats = new Schema(
    {
        username: {
            type: String,
            required: true
        },
        chats: {
            type: [{ type: Schema.Types.ObjectId, ref: 'Chat' }],
            default: [],
            validate: {
              validator: function (chats) {
                return chats.length >= 0;
              },
              message: 'Chats must be iterable'
            }
          }

});

module.exports = mongoose.model('AllChats', AllChats);
