const mongoose = require('mongoose');
const User = require('./User');
const Schema = mongoose.Schema;

// this is the Message schema
const Message = new Schema({
    id: {
        type: Number,
        required: true
    },
    created: {
        type: Date,
        default: Date.now()
    },
    sender: {
        type: User,
        required: true
    },
    content: {
        type: String,
        required: true
    },
});

module.exports = mongoose.model('Message', Message);