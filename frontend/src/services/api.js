const PORT = 5000;
const PORT_WS = 5001;
const SERVER_API = `http://localhost:${PORT}`;
const SERVER_API_WS = `http://localhost:${PORT_WS}`;

// extern the server
module.exports = {
    SERVER_API,
    SERVER_API_WS,
}
// export default SERVER_API;
// module.exports = {
//     SERVER_API_WS,
// }


// GET api/Chats
// return list of this object
// [
//     {
//         "id": 5,
//         "user": {
//             "username": "foofoo",
//             "displayName": "Foooo",
//             "profilePic": "picture"
//         },
//         "lastMessage": {
//             "id": 1,
//             "created": "2023-05-22T15:54:34.9245514",
//             "content": "this is a message"
//         }
//     }
// ]

//POST api/Chats
// accept this object
// {
//     username : "foofoo"
// }
// // return this object
// {
//     id: 6,
//     user: {
//       username: "omer",
//       displayName: "Omer",
//       profilePic: "asdiuashduashuidhiasudhuiashdiasu"
//     }
// }

