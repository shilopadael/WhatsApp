const { find } = require("../models/User");


function register(req, res) {
    const user = req.body;
    if (!user.email || !user.password) {
        res.status(400).send('Email and password required');
        return;
    }
    else if (user.password !== user.passwordConf) {
        res.status(400).send('Passwords do not match');
        return;
    }
    else if( user.password.length < 8) {
        res.status(400).send('Password must be at least 8 characters');
        return;
    }
    else if( user.password.length > 72) {
        res.status(400).send('Password must be less than 72 characters');
        return;
    }
    else if ( !find(user.email) ) {
        res.status(400).send('Email already exists');
        return;
    }
    else if( !user.email.includes('@') ) {
        res.status(400).send('Email must contain @');
        return;
    }
    else if( !user.email.includes('.') ) {
        res.status(400).send('Email must contain .');
        return;
    }
    else if (!find(user.username)) {
        res.status(400).send('Username already exists');
        return;
    }
    res.render('register', { title: 'Register' });
}