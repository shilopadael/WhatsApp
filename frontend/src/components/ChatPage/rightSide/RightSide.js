import './RightSide.css';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState, useEffect, useMemo, useRef, useCallback } from 'react';
import get from '../../../services/get-service';


async function fetchCurrentUserChat(currentChatId, setContactFullPage, setCurrentUserChat, setUserMessages, setAuthenticated) {
  try {
    const currentUserChat = await get.ChatsById(currentChatId);
    setCurrentUserChat(currentUserChat);
    setUserMessages(currentUserChat.messages);
  } catch (error) {
    console.error(error);
    setContactFullPage(true);
    // the user is not authenticated
    setAuthenticated(false);
    alert("token is expired, please log in again");
  }
}



function RightSide(props) {
  const [userMessages, setUserMessages] = useState([]);
  const [currentUserChat, setCurrentUserChat] = useState(null);
  const { user, contacts, setContacts, currentChatId, setContactFullPage , newMsg , setNewMsg , setAuthenticated} = props;

  const memoizedCurrentUserChat = useMemo(() => currentChatId, [currentChatId]);

  const memoizedCurrentUserChatCallback = useCallback(() => {
    fetchCurrentUserChat(memoizedCurrentUserChat, setContactFullPage, setCurrentUserChat, setUserMessages , setAuthenticated);
  }, [memoizedCurrentUserChat]);

  useEffect(memoizedCurrentUserChatCallback, [memoizedCurrentUserChat]);


  if (currentUserChat === undefined) {
    setContactFullPage(true)
    return (
      <div></div>
    );

  } else {
    let currentUser = contacts.filter((contact) => {
      return contact.id === currentChatId;
    });

    currentUser = currentUser[0].user;
    return (
      <div className="col-8 right-slide line-up p-0 parent-div">
        <TopBarRightSlide currentUser={currentUser}
          setContactFullPage={setContactFullPage}
        />
        <BoxMessage user={user}
          userMessages={userMessages} />
        <MessageNav
          setContacts={setContacts}
          contacts={contacts}
          chat={currentUserChat}
          setNewMsg={setNewMsg}
          newMsg={newMsg}
          setUserMessages={setUserMessages}
          userMessages={userMessages}
        />
      </div>
    );

  }
}

export default RightSide;
