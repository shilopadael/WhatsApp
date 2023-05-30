const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the UserPassName schema
const UserPassName = new Schema({
    id: {
        type: Number,
        required: true
    },
    username: {
        type: String,
        nullable: true
    },
    password: {
        type: String,
        nullable: true
    },
    displayName: {
        type: String,
        nullable: true
    },
    profilePic: {
        type: String,
        nullable: true
    }
});




module.exports = mongoose.model('UserPassName', UserPassName);