const Tokens = require('../controllers/Token');

const express = require('express');
var router = express.Router();

router.route('/')
    .post(Tokens.createToken);
