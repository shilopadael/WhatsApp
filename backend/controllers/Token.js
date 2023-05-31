const {createNewToken} = require('../services/token');


const createToken = async (req,res) => { 
    const token = await createNewToken(req);
    if (token.error) {
        return res.status(400).json(token);
    } else {
        return res.status(200).json(token);
    }
}

module.exports = { createToken };