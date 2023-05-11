import imageOmer from '../../assests/images/Omer.png';
import imageShilo from '../../assests/images/shilo.jpg';
import LeftSide from './leftSide/LeftSide';
import RightSide from './rightSide/RightSide';
import { useContacts } from './ContactsContext';
import { useState } from 'react';




function MainBlock(props) {
  const {users, id, setUsers} = props;
  const [chat, setChat] = useState("1");
  
  return (
    <>
      <div class="container border shadow container-lg container-md container-sm mainBlock">
        <div class="row no-gutters h-100">
          <LeftSide chat={chat} setChat={setChat} users={users} id={id} setUsers={setUsers}/>
          <RightSide chat={chat} setChat={setChat} users={users} id={id}/>
        </div>
      </div>
    </>
  );
}

export default MainBlock;
