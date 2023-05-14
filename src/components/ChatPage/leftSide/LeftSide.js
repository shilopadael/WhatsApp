

import TopBarLeftSide from './TopBarLeftSide';
import SearchContacts from './SearchContacts.js';
import Users from './Users';
import { useState } from 'react';



function LeftSide(props) {

    const { user , contacts, setContacts, setCurrentChatId, setContactFullPage } = props; // contacts
    const [ contactToShow, setContactToShow ] = useState(contacts);


    function showContacts() {
        if (contactToShow.length > 0) {
            contactToShow.sort((c1, c2) => {
                return c2.lastMessageDate - c1.lastMessageDate;
            });
            return contactToShow.map((contact, index) => {
                return <Users key={index}
                    {...contact}
                    setCurrentChatId={setCurrentChatId}
                    setContactFullPage={setContactFullPage}
                    contacts={contacts}
                    setContacts={setContacts}
                    setContactToShow={setContactToShow}
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