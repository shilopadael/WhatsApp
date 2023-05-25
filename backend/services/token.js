const jwt = require('jsonwebtoken');


const createToken = async (username) => {
    return jwt.sign({username});
}
const verifyToken = async (authorization) => {
    const token = tokenWithBearer.replace('Bearer ', '');

}
const  getTokenFromHeaders = (req) => {
    const { headers: { authorization } } = req;
    if (authorization && authorization.split(' ')[0] === 'Bearer') {
        const username = authorization.split(' ')[1];
        return username;
    }
    return null;
}

const isLogIn = (req, res, next) => {
    const token = getTokenFromHeaders(req);
    if (token) {
        const data = jwt.verify(token, key);
        console.log('The logged in user is: ' + data.username);
        req.username = data.username;
        return next();
    }
    else {
        return res.status(403).send('Token required');
    }

}
module.exports = { createToken, verifyToken, getTokenFromHeaders, isLogIn };