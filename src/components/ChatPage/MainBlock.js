import LeftSide from './leftSide/LeftSide';
import RightSide from './rightSide/RightSide';
import { useState } from 'react';
import './chat_page_style.css'


function MainBlock(props) {
  
  const { user } = props;
  const [contacts, setContacts] = useState([]);
  const [ currentChatId , setCurrentChatId ] = useState(null);
  
  return (
    <>
      <div class="container border shadow container-lg container-md container-sm mainBlock">
        <div class="row no-gutters h-100">
          <LeftSide user={user} contacts={contacts} setContacts={setContacts} setCurrentChatId={setCurrentChatId}/>
          <RightSide user={user} contacts={contacts} setContacts={setContacts} currentChatId={currentChatId}/>
        </div>
      </div>
    </>
  );
}

export default MainBlock;
