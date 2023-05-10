import imageShilo from '../assests/images/shilo.jpg';
import imageJoe from '../assests/images/joe.jpeg';
import imageOmer from '../assests/images/Omer.png';
import React, { useState } from 'react';



function Data() {
  const [users, setUsers] = useState([]);

  const [currentUser, setCurrentUser] = useState({
    name: 'Shilo Padael',
    image: imageShilo,
    id: 1,
  });

  const [contacts, setContacts] = useState([
    {
      id: 2,
    
    },
    {
      id: 3,
    },
    {
      id: 1,
    },
  ]);


  const [chats, setChats] = useState([  
     {id: 1,
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
            { message: 'Just working. You?', sender: 'them' },
            { message: 'Same here. Anyway, gotta run. Catch you later?', sender: 'me' },
            { message: 'Sure, talk to you soon!', sender: 'them' },
            { message: "Hey, what's up?", sender: 'them' },
            { message: 'Not much. You?', sender: 'me' },
            { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
            { message: 'Yeah, things are good. How about you?', sender: 'me' },
            { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
            { message: "I'm good, thanks. How about you?", sender: 'them' },
            { message: 'Not bad. What have you been up to?', sender: 'me' },
            { message: 'Just working. You?', sender: 'them' },
            { message: 'Same here. Anyway, gotta run. Catch you later?', sender: 'me' },
            { message: 'Sure, talk to you soon!', sender: 'them' },
            { message: "Hey, what's up?", sender: 'them' },
            { message: 'Not much. You?', sender: 'me' },
            { message: 'Just wanted to check in. Everything going okay?', sender: 'them' },
            { message: 'Yeah, things are good. How about you?', sender: 'me' },
            { message: 'Same here. Anyway, gotta go. Talk to you later!', sender: 'them' },
            ],
          },
        ],
  },
  {
    id: 2,
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

  const [currentChatId, setCurrentChatId] = useState(1);


  return {
    currentUser,
    contacts,
    chats,
    currentChatId,
    users,
    setUsers,
    setChats,
    setContacts,
    setCurrentChatId,
    setCurrentUser,
  };
}

export default Data;
