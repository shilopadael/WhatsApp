import './RightSide.css';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState, useEffect, useMemo, useRef, useCallback } from 'react';
import get from '../../../services/get-service';


async function fetchCurrentUserChat(currentChatId, setContactFullPage, setCurrentUserChat, setUserMessages) {
  try {
    console.log("test");
    const currentUserChat = await get.ChatsById(currentChatId);
    setCurrentUserChat(currentUserChat);
    setUserMessages(currentUserChat.messages);
  } catch (error) {
    console.error(error);
    setContactFullPage(true);
  }
}


function RightSide(props) {

  const [message, setMessage] = useState("");
  const [userMessages, setUserMessages] = useState([]); // messages
  const [currentUserChat, setCurrentUserChat] = useState(null);
  const { user, contacts, setContacts, currentChatId, setContactFullPage , setCurrentChatId} = props;

  const contactsRef = useRef(contacts);

  const memoizedCurrentUserChat = useMemo(() => currentChatId, [currentChatId]);
  
  const memoizedCurrentUserChatCallback = useCallback(() => {
    fetchCurrentUserChat(memoizedCurrentUserChat, setContactFullPage, setCurrentUserChat, setUserMessages);
  }, [memoizedCurrentUserChat]);

  useEffect(memoizedCurrentUserChatCallback, [memoizedCurrentUserChat]);

  if (currentUserChat === undefined) {
    console.log("in currentUserChat undefine")
    setContactFullPage(true)
    return (
      <div></div>
    );

  } else {
    let currentUser = contactsRef.current.filter((contact) => {
      return contact.id == currentChatId;
    });

    currentUser = currentUser[0].user;
    console.log("currentChatId" + currentChatId);
    
    // console.log("contacts:");
    // console.log(contacts);
    // console.log("user:");
    // console.log(user);
    // console.log("userMessages");
    // console.log(userMessages);
    // console.log("currentChatId:");
    // console.log(currentChatId);


    return (
      <div className="col-8 right-slide line-up p-0 parent-div">
        <TopBarRightSlide currentUser={currentUser}
        setContactFullPage={setContactFullPage}
       />
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
}

export default RightSide;
