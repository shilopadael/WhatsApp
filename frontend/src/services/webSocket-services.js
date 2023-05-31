const SERVER_API = require('./api');

const socket = new WebSocket(SERVER_API);

socket.onopen = () => {
    // Connection opened
    console.log(`client has connected successfully to the server: ${SERVER_API}}`);
};

socket.onmessage = (event) => {
    const data = event.body;
    // Handle received message the recived data is the following type:
    //   {
    //     "chatId": Number,
    //     "message": {
    //         "id": Number,
    //         "created": String,
    //         "sender": {
    //             "username": String,
    //             "displayName": String,
    //             "profilePic": String,
    //         },
    //         "content": String
    //     }
    //   }

    // update the state of the chatList


    // update the state of the chat


    // update the state of the message
    if (data) {
        alert(`${data.sender.displayName} sent you: ${data.message.content}`);
    }

};

socket.onclose = () => {
    // Handle disconnection
};
