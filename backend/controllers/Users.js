const userServices = require('../services/users');

const createUser = async (req,res) => {
    const UserPassName = await userServices.createUser(req.body);
    
    if (UserPassName.success === false) {
        res.status(400).json(UserPassName.error);
    } else {
        res.status(200).json(UserPassName.savedUser);
    }
}

const getUserById = async (req,res) => {

    const getUserDetails = await userServices.getUserByUsername(req.params.username);

    if (getUserDetails.error) {
        return res.status(401).json(getUserDetails);
    } else {
        return res.status(200).json(getUserDetails);
    }

}


module.exports = { createUser, getUserById };