import './RightSide.css';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState, useEffect } from 'react';
import get from '../../../services/get-service';

async function RightSide(props) {

  const [message, setMessage] = useState("");
  const [userMessages, setUserMessages] = useState([]); // messages
  const { user, contacts, setContacts, currentChatId, setContactFullPage } = props;

  const currentUserChat = contacts.find((contact) => String(contact.id) == currentChatId);

  useEffect(() => {
    async function fetchMessages() {
      try {
        const messages = await get.Messages(currentChatId);
        setUserMessages(messages || []); // If messages is null, set an empty array
      } catch (error) {
        // Handle error if the API call fails
        console.error(localStorage.getItem("error"));
      }
    }
  
    fetchMessages();
  }, [currentChatId]);
  
  useEffect(() => {
    console.log("userMessages updated:", userMessages);
  }, [userMessages]);

  // useEffect(() => {
  //   let isMounted = true;
  //   async function fetchMessages() {
  //     let messages = await get.Messages(currentChatId);
  //     console.log(messages)
  //     if (isMounted) {
  //       // setting the messages state only here
  //       setUserMessages(messages);
  //     }
  //   }
  //   fetchMessages();

  //   return () => {
  //     isMounted = false; // Clean up the isMounted variable to prevent setting state on an unmounted component
  //   };
  // }, []); // first time only



  // let data = {
  //     id: contact.id,
  //     username: contact.user.username,
  //     displayName: contact.user.displayName,
  //     profilePic: contact.user.profilePic,
  //     lastMessage: contact.lastMessage,
  //     lastMessageDate: lastMessageDate,
  //     lastMessageTime: lastMessageTime,
  // }

  if (currentUserChat === undefined) {
    setContactFullPage(true)
    return null;
  }


  return (
    <div className="col-8 right-slide line-up p-0 parent-div">
      <TopBarRightSlide currentUser={currentUserChat} setContactFullPage={setContactFullPage} />
      <BoxMessage user={user} userMessages={userMessages} />
      <MessageNav
        setContacts={setContacts}
        contacts={contacts}
        user={currentUserChat}
        setUserMessages={setUserMessages}
        setMessage={setMessage}
      />
    </div>
  );


}

export default RightSide;
