

import TopBarLeftSide from './TopBarLeftSide';
import Users from './Users';
// import Data from '../../data';



function LeftSide(props) {

    const { user } = props; // userinformation
    const { contacts, setContacts, setCurrentChatId, setContactFullPage } = props; // contacts

    function showContacts() {
        if (contacts.length > 0) {
            return contacts.map((contact, index) => {
                return <Users {...contact} setCurrentChatId={setCurrentChatId} setContactFullPage={setContactFullPage}/>
            });
        }
        else {
            return <h3 className="NoContactsMessage">No Contacts Yet</h3>
        }
    }


    return (
        <div className="col-4 left-slide justify-content-between border-right left-chat">
            <TopBarLeftSide contacts={contacts} setContacts={setContacts} user={user} />
            <ul className="list-group contact-list" >
                {showContacts()}
            </ul>

        </div>
    )

}
export default LeftSide;