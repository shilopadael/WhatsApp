const userServices = require('../services/users');
const UserPassName = require('../models/UserPassName');

const createUser = async (req,res) => {
    const UserPassName = await userServices.createUser(req.body);
    if (UserPassName.error) {
        res.status(400).json(UserPassName);
    } else {
        res.status(200).json(UserPassName);
    }
}

const getUserById = async (req,res) => {
    const getUserDetails = await userServices.getUserByUsername(req.params.id);
    if (getUserDetails.error) {
        res.status(401).json(getUserDetails);
    } else {
        res.status(200).json(getUserDetails);
    }

}


module.exports = { createUser, getUserById };