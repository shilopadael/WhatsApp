

import TopBarLeftSide from './TopBarLeftSide';
import SearchContacts from './SearchContacts.js';
import Users from './Users';
import auth from '../../../services/auth-service';
import { useEffect, useState } from 'react';
import './LeftSide.css';
import get from '../../../services/get-service'



function LeftSide(props) {

    const { user, contacts, setContacts, setCurrentChatId, setAuthenticated, contactFullPage, setContactFullPage } = props; // contacts
    const [contactToShow, setContactToShow] = useState(contacts);
    const [selectedContact, setSelectedContact] = useState(null);
    const [addContact, setAddContact] = useState(false);


    useEffect(() => {
        const getChatsData = async () => {
            // getting the contacts from the server
            let contactsData = await get.Chats();
            if (contactsData !== null) {
                setContacts(contactsData);
                setContactToShow(contactsData);
            } else {
                setAuthenticated(false);
                auth.logout();
            }
        };
        // Invoking the asynchronous function
        getChatsData();
    }, [addContact]);

    useEffect(() => {
        
    }, [contactToShow]);
    
    function showContacts() {
        if (contactToShow.length > 0) {
            contactToShow.sort((c1, c2) => {
                if (c1.lastMessage === null && c2.lastMessage === null) {
                    return 0;
                }
                if (c1.lastMessage === null) {
                    return 1;
                }
                if (c2.lastMessage === null) {
                    return -1;
                }
                return c2.lastMessage.created - c1.lastMessage.created;
            });

            return contactToShow.map((contact, index) => {

                // getting contactInformation

                return <Users key={index}
                    {...contact}
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
                setAddContact={setAddContact}
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