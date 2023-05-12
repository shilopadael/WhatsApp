

import TopBarLeftSide from './TopBarLeftSide';
import Users from './Users';
// import Data from '../../data';



function LeftSide(props) {

    const { user } = props; // userinformation
    const { contacts, setContacts , setCurrentChatId } = props; // contacts

    function showContacts() {
        if (contacts.length > 0) {
            return contacts.map((contact, index) => {
                console.log(contact);
                return <Users {...contact} setCurrentChatId={setCurrentChatId} />
            });
        }
        else {
            return <h3>No Contacts</h3>
        }
    }


    return (
        <>
            <div class="col-4 left-slide justify-content-between border-right">
                <TopBarLeftSide contacts={contacts} setContacts={setContacts} user={user} />
                <ul class="list-group chats">
                    {showContacts()}
                </ul>

            </div>
        </>
    )
}
export default LeftSide;