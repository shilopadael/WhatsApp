

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