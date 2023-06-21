const {createNewToken} = require('../services/token');
const onlineConnection = require('../onlineConnection');

const createToken = async (req,res) => { 
    const token = await createNewToken(req);
    if (token.error) {
        return res.status(400).json(token);
    } else {
        // checking if the user logged from andorid or web
        const deviceType = req.headers['devicetype'];
        if (deviceType === "Android") {
            // from android getting the device firebase token
            const onlineUsers = new onlineConnection();
            let username = req.headers['username']
            let firebase = req.headers['firebasetoken']
            // checking if the username already logged in from another device
            let userOnline = onlineUsers.getOnlineUser(username);
            if(userOnline !== undefined && userOnline.firebaseToken !== firebase){
                // user already logged in from another device
                // removing the old user from the online users list
                onlineUsers.removeOnlineUser(username);
                console.log(`${username} was already logged from another device! with different firebase token`);
            }

            console.log(`${username} logged from the android app!`);
            onlineUsers.addOnlineUser({ id: null, username: username , firebaseToken: firebase });
        }
        return res.status(200).json(token);
    }
}

module.exports = { createToken };