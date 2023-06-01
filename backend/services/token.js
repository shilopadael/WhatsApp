const jwt = require('jsonwebtoken');
const UserPassName  = require('../models/UserPassName');
const { json } = require('body-parser');


const key = "Some super secret key shhhhhhhhhhhhhhhhh!!!!!"


const createNewToken = async (req) => {
    const username = req.body.username;
    const password = req.body.password;
    const user = await UserPassName.find({username: username, password: password});
    if (user.length > 0) {
        const data = { username };
        const token = jwt.sign(data , key);
        return token;
    }
    else {
        return { error: 'Invalid username or password' };
    }
}

const  getTokenFromHeaders = (req) => {
    const { headers: { authorization } } = req;
    if (authorization && authorization.split(" ")[0] === 'bearer') {
        const token = authorization.split(" ")[1];
        return token;
    }
    if (authorization && authorization.split(" ")[0] === 'Bearer') {
        const token = authorization.split(" ")[1];
        return token;
    }
    return null;
}

const getUserName = (req) => {
    const token = getTokenFromHeaders(req);
    const formattedToken = token.replace(/"/g, ''); 
    try {
        const data = jwt.verify(formattedToken, key);
        return data.username;
    }
    catch (error) {
        return res.status(403).send('Token required');
    }
}

const isLogIn = (req, res, next) => {
    const token = getTokenFromHeaders(req);
    const formattedToken = token.replace(/"/g, ''); 
    try {
        const data = jwt.verify(formattedToken, key);
        req.username = data.username;
        return next();
    } catch (error) {
        return res.status(403).send('Token required');
    }
}
module.exports = { createNewToken, getTokenFromHeaders, isLogIn, getUserName };