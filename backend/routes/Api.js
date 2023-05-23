const express = require('express');
const router = express.Router();

// setting all the api
router.use('/Users', require('./Users'));
