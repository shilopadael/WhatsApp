const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the UserPass schema
const UserPass = new Schema({
    username: {
        type: String,
        nullable: true
    },
    password: {
        type: String,
        nullable: true,
    }
});

module.exports = mongoose.model('UserPass', UserPass);