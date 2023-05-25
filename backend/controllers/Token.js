const token = require('../services/token');


const createToken = async (req,res) => { 
    const token = await token.getTokenFromHeaders(req);
    if (token.error) {
        res.status(400).json(token);
    } else {
        res.status(200).json(token);
    }
}

module.exports = { createToken };