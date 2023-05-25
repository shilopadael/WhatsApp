// first Routes -> Controllers -> Models -> Services
const express = require('express');
const app = express();

// Configure middleware
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/api/Users', require('./routes/Users'));
app.use('/api/UserPass', require('./routes/UserPass'));
app.use('/api/UserPassName', require('./routes/UserPassName'));
app.use('/api/Tokens', require('./routes/Tokens'));



app.listen(3000, () => {
    console.log('server started on: http://localhost:3000');
});
