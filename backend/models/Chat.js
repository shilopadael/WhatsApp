const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the Chat schema
const Chat = new Schema({
    id: {
      type: Number,
      required: true
    },
    users: {
      type: [{ type: Schema.Types.ObjectId, ref: 'User' }],
      default: [],
      validate: {
        validator: function (users) {
          return users.length >= 0;
        },
        message: 'Users must be iterable'
      }
    },
    messages: {
      type: [{ type: Schema.Types.ObjectId, ref: 'Message' }],
      default: []
    }
  });


  
module.exports = mongoose.model('Chat', Chat);
