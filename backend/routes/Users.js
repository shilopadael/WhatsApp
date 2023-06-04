const userController = require ('../controllers/Users');
const {isLogIn} = require('../services/token');

const express = require('express');
var router = express.Router();

router.route('/')
    .post(userController.createUser);


router.route('/:username')
    .get(isLogIn, userController.getUserById)
    
module.exports = router;
