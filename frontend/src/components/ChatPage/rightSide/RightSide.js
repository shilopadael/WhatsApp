import './RightSide.css';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState } from 'react';
import get from '../../../services/get-service';

async function RightSide(props) {

    const [message, setMessage] = useState("");
    const { contacts, setContacts, currentChatId , setContactFullPage} = props;

    // const currentUserId = localStorage.getItem("currentUserId");
    const currentUser = contacts.find((contact) => String(contact.id) == currentChatId);

    // const newContact = { id: newId, 
    //     name: name, 
    //     image: defaultProfile, 
    //     lastMessageTime: "now", 
    //     unRead: 0, 
    //     lastMessage: "", 
    //     messages: [], 
    //     status: data[random],
    //     };

    if (currentUser === undefined) {
        return (
            setContactFullPage(true)
        );
    } else {

        // getting user messages
        const userMessages = await get.Messages(currentChatId);

        return (
            <div className="col-8 right-slide line-up p-0 parent-div">
                <TopBarRightSlide currentUser={currentUser} setContactFullPage={setContactFullPage}/>
                <BoxMessage userMessages={userMessages}/>
                <MessageNav
                    setContacts={setContacts}
                    contacts={contacts}
                    user={currentUser}
                    userChat={userMessages}
                    setMessage={setMessage}
                    />
            </div>

        );
    }

}

export default RightSide;
