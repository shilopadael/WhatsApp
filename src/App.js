import './App.css';

import LoginPage from './components/loginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";
import Chat from './components/Chat';
import imageShilo from './assests/images/shilo.jpg';
import imageJoe from './assests/images/joe.jpeg';
import imageOmer from './assests/images/Omer.png';

function App() {

  const [users, setUsers] = useState([  
    {id: 1,
      image: imageShilo,
     chats: [{id: 1, 
              lastSeen: 'today',
              name: 'Shilo Padael',
              unRead: 3,
              image: imageShilo,
              messages:
           [
             { message: 'Not bad. What have you been up to?', sender: 'me' }, 
             { message: 'Not much. You?', sender: 'me' },
             { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
             { message: 'Yeah, things are good. How about you?', sender: 'me' },
             { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
           ],
         },
         {
           id: 3,
           lastSeen: 'today',
           name: 'Omer Bar',
           unRead: 1,
           image: imageOmer,
           messages:
         [
             { message: "Hey, what's up?", sender: 'them' },
             { message: 'Not much. You?', sender: 'me' },
             { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
             { message: 'Yeah, things are good. How about you?', sender: 'me' },
             { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
           ],
         },
         {
           id: 2,
           lastSeen: 'today',
           unRead: 1,
           name: 'Joe Biden',
           image: imageJoe,
           messages:
         [
           { message: 'Hi, how are you?', sender: 'me' },
           { message: "I'm good, thanks. How about you?", sender: 'them' },
           { message: 'Not bad. What have you been up to?', sender: 'me' },
           { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
           { message: 'Yeah, things are good. How about you?', sender: 'me' },
           { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
           ],
         },
       ],
 },
 {
   id: 2,
   image: imageShilo,
   chats: [
     {
       id: 2,
       lastSeen: 'today',
       unRead: 1,
       messages: [
         { message: "Hey, what's up?", sender: 'them' },
         { message: 'Not much. You?', sender: 'me' },
         { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
         { message: 'Yeah, things are good. How about you?', sender: 'me' },
         { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
       ],
     },
     {
       id: 3,
       lastSeen: 'today',
       unRead: 1,
       messages: [
         { message: 'Hey, how have you been?', sender: 'me' },
         { message: 'I have been doing well. Thanks for asking!', sender: 'them' },
       ],
     },
   ],
 },
]);
  
  return (

      <Chat users={users} setUsers={setUsers} />
    // <BrowserRouter>
    //   <Routes>
    //     <Route path="/" element={<LoginPage users={users} setUsers={setUsers} />} />
    //     <Route path="/register" element={<RegisterPage users={users} setUsers={setUsers} />} />
    //     <Route path="/chat/:id" element={<ChatPage users={users} />} />
    //   </Routes>
    // </BrowserRouter>

  );
}

export default App;
