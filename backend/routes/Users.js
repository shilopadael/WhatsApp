const userController = require ('../controllers/Users');

const express = require('express');
var router = express.Router();

router.route('/')
    .post(userController.createUser);

router.route('/:id')
    .get(userController.getUserById)
    
module.exports = router;
