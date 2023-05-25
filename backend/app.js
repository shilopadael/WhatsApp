// first Routes -> Controllers -> Models -> Services
const express = require('express');
const app = express();

const cors = require('cors');
app.use(cors());
app.use(express.json());

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

const custonEnv = require('custom-env');
custonEnv.env(process.env.NODE_ENV, './config');
console.log(process.env.CONNECTION_STRING);
console.log(process.env.PORT);

const mongoose = require('mongoose');
mongoose.connect(process.env.CONNECTION_STRING, {
    useNewUrlParser: true,
     useUnifiedTopology: true
});

app.use(express.static('public'));


const Users = require('./routes/Users');
const addUser = require('./routes/addUser');





const key = "Some super secret key shhhhhhhhhhhhhhhhh!!!!!"


// Define a function that responds with a json response.
// Only logged in users should be able to execute this function
const index = (req, res) => {
    app.use('/Users', Users);
    app.use('/Chats', Chats);
}



const { getTokenFromHeaders, isLogIn } = require('./services/token');

// Show sensitive route index - only if logged in
app.get('/', isLogIn, index)

//adding new user
app.post('/Users', addUser);



app.listen(process.env.PORT);