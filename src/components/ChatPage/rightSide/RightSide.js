
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState } from 'react';

function RightSide(props) {

    const [message, setMessage] = useState("");
    const { contacts, setContacts, currentChatId , setContactFullPage} = props;

    // const currentUserId = localStorage.getItem("currentUserId");
    const currentUser = contacts.find((contact) => String(contact.id) == currentChatId);
    contacts.forEach((item) => {
        console.log(item);
      });
    if (currentUser === undefined) {
        return (
            setContactFullPage(true)
        );
    } else {
        const userMessages = currentUser.messages;
        return (
            <div className="col-8 right-slide line-up p-0 parent-div">
                <TopBarRightSlide currentUser={currentUser} setContactFullPage={setContactFullPage}/>
                <BoxMessage userMessages={userMessages}/>
                <MessageNav
                    user={currentUser}
                    userChat={userMessages}
                    setMessage={setMessage}
                    />
            </div>

        );
    }

}

export default RightSide;
