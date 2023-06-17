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
            let username = req.headers['username']
            let firebase = req.headers['firebasetoken']
            console.log(`${username} logged from the android app!`);
            const onlineUsers = new onlineConnection();
            onlineUsers.addOnlineUser({ id: null, username: username , firebaseToken: firebase });
        }
        return res.status(200).json(token);
    }
}

module.exports = { createToken };