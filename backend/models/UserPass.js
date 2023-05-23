const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the UserPass schema
const UserPass = new Schema({
    username: {
        type: String,
        required: true
    },
    password: {
        type: String,
        require: true
    }
});

module.exports = mongoose.model('UserPass', UserPass);