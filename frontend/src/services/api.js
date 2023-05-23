const SERVER_API = "http://localhost:5000";

// extern the server
export default SERVER_API;


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

