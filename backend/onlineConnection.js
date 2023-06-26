// singlton class

class onlineConnection {
    constructor() {
        if (!onlineConnection.instance) {
            this.onlineUsers = [];
            onlineConnection.instance = this;
        }
        // this.onlineUsers = [];
        return onlineConnection.instance;
    }

    addOnlineUser(user) {
        this.onlineUsers.push(user);
    }

    removeOnlineUser(username) {
        this.onlineUsers = this.onlineUsers.filter(u => u.username !== username);
    }

    getOnlineUser(username) {
        return this.onlineUsers.find(u => u.username === username);
    }

}

module.exports = onlineConnection;