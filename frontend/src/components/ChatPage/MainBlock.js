import LeftSide from './leftSide/LeftSide';
import RightSide from './rightSide/RightSide';
import { useState, useEffect, useCallback } from 'react';
import './chat_page_style.css'


function MainBlock(props) {

  const { user, setAuthenticated } = props;

  const [contacts, setContacts] = useState([]);
  const [newMsg , setNewMsg] = useState(false); // messages
  const [currentChatId, setCurrentChatId] = useState(null);
  const [contactFullPage, setContactFullPage] = useState(true);

  // const memoizedCurrentChatId = useCallback(() => currentChatId, [currentChatId]);

  useEffect(() => {
  }, [currentChatId, contactFullPage, contacts]);

  // console.log("main block: id=" + currentChatId + "  contactfullpage: " + contactFullPage)
  if (contactFullPage) {
    return (
      <>
        <div className="container border shadow container-lg container-md container-sm defaultContactList" >
          <div className="row no-gutters h-100 defaultContactBlock">
            <LeftSide user={user}
              currentChatId={currentChatId}
              setAuthenticated={setAuthenticated}
              contacts={contacts}
              newMsg={newMsg}
              setContacts={setContacts}
              setCurrentChatId={setCurrentChatId}
              contactFullPage={contactFullPage}
              setContactFullPage={setContactFullPage}
            />
          </div>
        </div>
      </>

    )

  } else {
    return (
      <div className="container border shadow container-lg container-md container-sm ContactList">
        <div className="row no-gutters h-100 ContactBlock">
          <LeftSide user={user}
            contacts={contacts}
            currentChatId={currentChatId}
            setAuthenticated={setAuthenticated}
            setContacts={setContacts}
            newMsg={newMsg}
            setCurrentChatId={setCurrentChatId}
            setContactFullPage={setContactFullPage}
            contactFullPage={contactFullPage} />
          <RightSide user={user}
            contacts={contacts}
            newMsg={newMsg}
            setNewMsg={setNewMsg}
            setContacts={setContacts}
            currentChatId={currentChatId}
            setContactFullPage={setContactFullPage}
            setAuthenticated={setAuthenticated}
            setCurrentChatId={setCurrentChatId} />
        </div>
      </div>
    );
  }
}

export default MainBlock;
