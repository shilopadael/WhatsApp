

const express = require('express');
const admin = require('firebase-admin');

const serviceAccount = require('./firebaseKey.json');
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});


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
const  socketIo = require('socket.io');
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

// let users = [];
const onlineConnection = require('./onlineConnection');
const onlineUsers = new onlineConnection();
io.on('connection', (socket) => {
    const username = socket.handshake.query.username;
    onlineUsers.addOnlineUser({ id: socket.id, username: username });
    console.log(`${username} connected to the socket ${socket.id}`);
    socket.on('disconnect', () => {
        console.log(`${username} disconnected from the socket`);
        onlineUsers.removeOnlineUser(username);
        // users = users.filter(user => user.id !== socket.id);
    }
    );
    socket.on('send-message', (data) => {
        let currentUserName = data.receiverUsername;
        // const user = users.find(user => user.username === currentUserName);
        const user = onlineUsers.getOnlineUser(currentUserName);
        if (user && user.id !== null) {
            // this is message to web
            console.log("sending message to " + user.username)
            io.to(user.id).emit('receive-message', data);
        } else if(user && user.id === null) {
            // message to android
            // construction the new message
            const message = {
                notification: {
                  title: currentUserName,
                  body: "empty message"
                },
                token: user.firebaseToken // Replace with the FCM device token of the recipient
              };
              admin.messaging().send(message).then((response) => {
                // Response is a message ID string.
                console.log('Successfully sent message from firebase!');
              })
              .catch((error) => {
                console.log('Error sending message:', error);
              });
        }
    });

    socket.on('alert', (data) => {
        const user = users.find(user => user.username === data.receiverUsername);
        if (user) {
            io.to(user.id).emit('alert', data);
        }
    });

    socket.on('adding-contact', (data) => {
        const user = users.find(user => user.username === data.username);
        // console.log("sending update-contact-list to ");
        if (user && user.id !== null) {
            io.to(user.id).emit('update-contact-list', data);
        }
    })

    socket.on('remove-contact', (data) => {
        const user = users.find(user => user.username === data.username);
        if (user && user.id !== null) {
            io.to(user.id).emit('update-contact-list', data);
        }
    });
});



app.use(express.static('public'));

const Users = require('./routes/Users');
const getToken = require('./routes/Tokens');
const Chats = require('./routes/Chats');



app.use('/api/Chats',Chats);

app.use('/api/Users', Users);

app.use('/api/Tokens', getToken)



app.listen(process.env.PORT, () => {
    console.log(`Server running on port ${process.env.PORT}`);
});