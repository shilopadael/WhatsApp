

const express = require('express');
const FireBaseManager = require('./services/firebaseAdmin');


const app = express();

const cors = require('cors');
app.use(cors());
app.use(express.json());

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

const custonEnv = require('custom-env');
custonEnv.env(process.env.NODE_ENV, './config');


const mongoose = require('mongoose');
mongoose.connect(process.env.CONNECTION_STRING, {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

const http = require('http');
const server = http.createServer(app);
const socketIo = require('socket.io');
const io = socketIo(server, {
    cors: {
        origin: '*',
        method: ['GET', 'POST'],
    }
});
let socketPort = process.env.PORT;
// converting the number
socketPort = Number(socketPort) + 1;
server.listen(socketPort, () => {
    console.log(`Server-io running on port ${socketPort}`);
});

const firebase = new FireBaseManager();


const onlineConnection = require('./onlineConnection');
const onlineUsers = new onlineConnection();
io.on('connection', (socket) => {
    const username = socket.handshake.query.username;
    onlineUsers.addOnlineUser({ id: socket.id, username: username, io: io });
    console.log(`${username} connected to the socket ${socket.id}`);
    socket.on('disconnect', () => {
        console.log(`${username} disconnected from the socket`);
        onlineUsers.removeOnlineUser(username);
        // users = users.filter(user => user.id !== socket.id);
    }
    );
    socket.on('send-message', (recData) => {
        let currentUserName = recData.receiverUsername;
        const user = onlineUsers.getOnlineUser(currentUserName);
        if (user && user.id !== null) {
            // this is message to web
            console.log("sending message to " + user.username)
            io.to(user.id).emit('receive-message', recData);
        } else if (user && user.id === null) {
            // message to android
            // construction the new message
            console.log("sending message to " + user.username + " from firebase");
            const message = {
                data: {
                    // Add your custom data fields here
                    chatId: `${recData.id}`,
                    sender: currentUserName
                },
                notification: {
                    title: recData.data.sender.username,
                    body: recData.data.content
                },
                token: user.firebaseToken // Replace with the FCM device token of the recipient
            };
            firebase.sendNotificationToUser(message);
        }
    });

    socket.on('alert', (data) => {
        const user = onlineUsers.getOnlineUser(data.receiverUsername);
        if (user) {
            io.to(user.id).emit('alert', data);
        }
    });

    socket.on('adding-contact', (data) => {
        const user = onlineUsers.getOnlineUser(data.username);
        if (user && user.id !== null) {
            io.to(user.id).emit('update-contact-list', data);
        }
    })

    socket.on('remove-contact', (data) => {
        const user = onlineUsers.getOnlineUser(data.username);
        if (user && user.id !== null) {
            io.to(user.id).emit('update-contact-list', data);
        }
    });
});



app.use(express.static('public'));

const Users = require('./routes/Users');
const getToken = require('./routes/Tokens');
const Chats = require('./routes/Chats');



app.use('/api/Chats', Chats);

app.use('/api/Users', Users);

app.use('/api/Tokens', getToken)



app.listen(process.env.PORT, () => {
    console.log(`Server running on port ${process.env.PORT}`);
});