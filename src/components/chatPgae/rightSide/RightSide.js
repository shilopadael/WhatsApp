import imageOmer from '../../../assests/images/Omer.png';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useContacts } from '../ContactsContext';
import { useState } from 'react';

function RightSide() {
    const { setChat,chats, currentChatId , currentUser} = useContacts();
    // // Assuming the user ID is stored in a variable called 'userId'
    const user = chats.find(chat => chat.id === currentUser.id);
    // console.log(user);
    const userChat = user.chats.find(chat => chat.id === currentChatId);
    console.log(userChat);

    const [message, setMessage] =useState("") ;
    


    return (
            <>
                    <div class="col-8 right-slide line-up p-0 parent-div ">
                        <TopBarRightSlide/>
                        <BoxMessage user={user} userChat={userChat}/>
                        <MessageNav user={user} userChat={userChat} setChat={setChat} setMessage={setMessage}/>
                    </div>
            </>
    );
}

export default RightSide;
