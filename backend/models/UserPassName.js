const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// this is the UserPassName schema
const UserPassName = new Schema({
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

function getDisplayName() {
    return this.displayName;
}
function getProfilePic() { 
    return this.profilePic;
}
function getUsername() {
    return this.username;
}
function getPassword() {
    return this.password;
}


module.exports = mongoose.model('UserPassName', UserPassName);