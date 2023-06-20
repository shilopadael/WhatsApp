// singlton class

class onlineConnection {
    constructor() {
        if (!onlineConnection.instance) {
            onlineConnection.instance = this;
        }
        this.onlineUsers = [];
        return onlineConnection.instance;
    }

    addOnlineUser(user) {
        this.onlineUsers.push(user);
    }

    removeOnlineUser(username) {
        this.onlineUsers = this.onlineUsers.filter(u => u.username !== username);
    }

    getOnlineUser(username) {
        console.log("online user  ", this.onlineUsers);
        return this.onlineUsers.find(u => u.username === username);
    }

}

module.exports = onlineConnection;