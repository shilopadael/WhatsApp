const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the User schema
const User = new Schema({
    id: {
        type: Number,
        required: true
    },
    username: {
        type: String,
        nullable: true,
    },
    displayName: {
        type: String,
        nullable: true,
    },
    profilePic: {
        type: String,
        nullable: true,
    },

});
User.pre('remove', async function (next) {
    const userId = this._id;
  
    // Find all the chats that reference the user being deleted
    const chats = await mongoose.model('Chat').find({ users: userId });
  
    // Iterate through each chat and remove the user from the users array
    const chatPromises = chats.map(async (chat) => {
      chat.users.pull(userId);
      await chat.save();
    });

    
  
    // Wait for all the chat updates to complete
    await Promise.all(chatPromises);
  
    next();
  });


// Function to generate a new ID for a user
User.statics.generateNewUserId = async function() {
    try {
      const maxIdUser = await User.findOne({}, { id: 1 })
        .sort({ id: -1 })
        .limit(1);
        
      let newId = 1;
      if (maxIdUser) {
        newId = maxIdUser.id + 1;
      }
      
      return newId;
    } catch (error) {
      // Handle any potential errors
      console.error('Error generating new user ID:', error);
      throw error;
    }
  }

module.exports = mongoose.model('User', User);