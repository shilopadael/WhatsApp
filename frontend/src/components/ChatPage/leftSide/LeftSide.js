

import TopBarLeftSide from './TopBarLeftSide';
import SearchContacts from './SearchContacts.js';
import Users from './Users';
import { useEffect, useState } from 'react';
import './LeftSide.css';
import get from '../../../services/get-service'
import auth from '../../../services/auth-service'



function LeftSide(props) {

    const { user, contacts, setContacts, setCurrentChatId, setAuthenticated, contactFullPage, setContactFullPage } = props; // contacts
    const [contactToShow, setContactToShow] = useState(contacts);
    const [selectedContact, setSelectedContact] = useState(null);

    useEffect(() => {
        const getChatsData = async () => {
            // getting the contacts from the server
            let contactsData = await get.Chats();
            if (contactsData !== null) {
                // setting the contacts states
                setContacts(contactsData);
                setContactToShow(contactsData);
            } else {
                setContacts([]);
                setContactToShow([]);
            }
        };
        // Invoking the asynchronous function
        getChatsData();

    }, []);


    function showContacts() {
        if (contactToShow.length > 0) {
            contactToShow.sort((c1, c2) => {
                return c2.lastMessageDate - c1.lastMessageDate;
            });
            return contactToShow.map((contact, index) => {

                // getting contactInformation
                let lastMessage = contact.lastMessage;
                if (lastMessage && lastMessage.length > 20) {
                    lastMessage = lastMessage.substring(0, 20) + "...";
                }
                let userInfo = contact.user;
                console.log(userInfo)

                console.log(contact);
                return <Users key={index}
                    {...userInfo}
                    id={contact.id}
                    lastMessage={lastMessage}
                    setCurrentChatId={setCurrentChatId}
                    setContactFullPage={setContactFullPage}
                    contacts={contacts}
                    setContacts={setContacts}
                    setContactToShow={setContactToShow}
                    contactToShow={contactToShow}
                    contactFullPage={contactFullPage}
                    setSelectedContact={setSelectedContact}
                    selectedContact={selectedContact}
                />
            });
        }
        else if (contacts.length == 0) {
            return <h5 className="NoContactsMessage">No Contacts Yet</h5>
        }
        else {
            return <h5 className="NoContactsFoundMessage">No Contact Found</h5>
        }
    }

    return (
        <div className="col-4 left-slide justify-content-between border-right left-chat">
            <TopBarLeftSide
                setAuthenticated={setAuthenticated}
                contacts={contacts}
                setContacts={setContacts}
                user={user}
                contactToShow={contactToShow}
                setContactToShow={setContactToShow} />
            <SearchContacts contacts={contacts} setContactToShow={setContactToShow} />
            <ul className="list-group contact-list" >
                {showContacts()}
            </ul>
        </div>
    )

}
export default LeftSide;