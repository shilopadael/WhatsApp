import './RightSide.css';
import MessageNav from './MessageNav';
import BoxMessage from './BoxMessage';
import TopBarRightSlide from './TopBarRightSide';
import { useState , useEffect } from 'react';
import get from '../../../services/get-service';

async function RightSide(props) {

    const [message, setMessage] = useState("");
    const { user ,contacts, setContacts, currentChatId , setContactFullPage} = props;
    // const currentUserId = localStorage.getItem("currentUserId");
    const currentUser = contacts.find((contact) => String(contact.id) == currentChatId);
    let userMessages = [];
    useEffect(() => {
        let isMounted = true;
        async function fetchMessages() {
          userMessages = await get.Messages(currentChatId);
    
          if (isMounted) {
            // Update the state only if the component is still mounted
            // to avoid setting state on an unmounted component
            // (this is to handle cases where the component unmounts before the promise resolves)
            // Set the userMessages state with the fetched messages
            // ... perform any necessary data transformation if needed
            // For example, if userMessages is an array of messages,
            // you might want to sort them or format the data before setting the state.
          }
        }
    
        fetchMessages();
    
        return () => {
          isMounted = false; // Clean up the isMounted variable to prevent setting state on an unmounted component
        };
      }, [currentChatId]); // Re-run the effect whenever the currentChatId changes
    
    // let data = {
    //     id: contact.id,
    //     username: contact.user.username,
    //     displayName: contact.user.displayName,
    //     profilePic: contact.user.profilePic,
    //     lastMessage: contact.lastMessage,
    //     lastMessageDate: lastMessageDate,
    //     lastMessageTime: lastMessageTime,
    // }

    if (currentUser === undefined) {
        return (
            setContactFullPage(true)
        );
    } else {

        return (
            <div className="col-8 right-slide line-up p-0 parent-div">
                <TopBarRightSlide currentUser={currentUser} setContactFullPage={setContactFullPage}/>
                <BoxMessage user={user} userMessages={userMessages}/>
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
