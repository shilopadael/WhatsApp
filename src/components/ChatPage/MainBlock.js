import LeftSide from './leftSide/LeftSide';
import RightSide from './rightSide/RightSide';
import { useState } from 'react';
import './chat_page_style.css'
import LogOut from './LogOutButton';


function MainBlock(props) {

  const { user } = props;
  const [contacts, setContacts] = useState([]);
  const [currentChatId, setCurrentChatId] = useState(null);
  const [contactFullPage, setContactFullPage] = useState(true);

  if (contactFullPage) {
    return (
      <div className="container border shadow container-lg container-md container-sm defaultContactList" >
        <div className="row no-gutters h-100 defaultContactBlock">
          <LeftSide user={user}
            contacts={contacts}
            setContacts={setContacts}
            setCurrentChatId={setCurrentChatId}
            contactFullPage={contactFullPage}
            setContactFullPage={setContactFullPage} />
        </div>
      </div>
    )

  }

  return (
    <div className="container border shadow container-lg container-md container-sm ContactList">
      <div className="row no-gutters h-100 ContactBlock">
        <LeftSide user={user}
          contacts={contacts}
          setContacts={setContacts}
          setCurrentChatId={setCurrentChatId}
          setContactFullPage={setContactFullPage} />
        <RightSide user={user}
          contacts={contacts}
          setContacts={setContacts}
          currentChatId={currentChatId}
          setContactFullPage={setContactFullPage} />
      </div>
    </div>
  );
}

export default MainBlock;
