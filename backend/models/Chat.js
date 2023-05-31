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
  // Define the pre middleware to perform cascading delete
  Chat.pre('remove', async function (next) {
  try {
    // Access the current Chat document being removed
    const chat = this;

    // Delete the chat reference from other schemas
    // For example, assuming there is a User schema with a "chats" field
    await mongoose.model('AllChats').updateMany(
      { chats: chat._id },
      { $pull: { chats: chat._id } }
    );

    // Continue to the next middleware
    next();
  } catch (error) {
    // Handle any error that occurred
    next(error);
  }
});


  
module.exports = mongoose.model('Chat', Chat);
