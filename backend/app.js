

const express = require('express');
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
const io = socketIo(server);

let users = [];
io.on('connection', (socket) => {
    const username = socket.handshake.query.username;
    users.push({ id: socket.id, username: username });
    console.log(`${username} connected to the socket`);
    socket.on('disconnect', () => {
        console.log(`${username} disconnected from the socket`);
        users = users.filter(user => user.id !== socket.id);
    }
    );
    socket.on('send-message', (data) => {
        console.log(data);
        const user = users.find(user => user.username === data.receiverUsername);
        console.log(user);
        if (user) {
            io.to(user.id).emit('receive-message', data.message);
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