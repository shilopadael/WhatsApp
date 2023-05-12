import defaultImg from '../../../assets/registerImg/profile.png';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useContacts } from '../ContactsContext';
import { useState } from 'react';

function RightSide(props) {

    const [message, setMessage] = useState("");
    const { contacts, setContacts, currentChatId } = props;
    // getting currentUserId = 1

    const currentUserId = localStorage.getItem("currentUserId");
    const currentUser = contacts.find((contact) => contact.id === currentUserId);
    if (currentUser === undefined) {
        // template when no use is selected
        let nonMessage = [];
        let data = {
            id: 0,
            name: "No User Selected",
            image: defaultImg,
            lastSeen: "today",
            unRead: 0,
            messages: nonMessage,
        };
        return (
            <div className="col-8 right-slide line-up parent-div right-chat">
                <TopBarRightSlide currentUser={data} />
                    <BoxMessage userMessages={nonMessage} />
                        <MessageNav
                            user={data}
                            userChat={nonMessage}
                            setMessage={setMessage} />
            </div>

        );
    } else {
        const userMessages = currentUser.messages;
        // sending the userMessages
        console.log("changed to id" + currentUserId);
        return (
            <div className="col-8 right-slide line-up p-0 parent-div right-chat">
                <TopBarRightSlide currentUser={currentUser} />
                <BoxMessage userMessages={userMessages} />
                <MessageNav
                    user={currentUser}
                    userChat={userMessages}
                    setMessage={setMessage} />
            </div>

        );
    }

}

export default RightSide;
