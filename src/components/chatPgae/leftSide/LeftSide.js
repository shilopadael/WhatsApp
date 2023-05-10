

import TopBarLeftSide from './TopBarLeftSide';
import Users from './Users';
// import Data from '../../data';
import { useContacts } from '../ContactsContext';



function LeftSide() {
    const { chats,contacts,currentUser } = useContacts();
    const user = chats.find(chat => chat.id === currentUser.id);
    const chat = user.chats;
    // console.log(user);

    // console.log(contacts);

    function showContacts() {

        return user.chats.map((chat) => {
            console.log(chat);
            return <Users {...chat}/>
    
        });

    }
    return (
        <>
                     <div class="col-4 left-slide justify-content-between border-right">
                            <TopBarLeftSide />
                            <ul class="list-group chats">
                                {showContacts()}
                            </ul>
                            
                    </div>
        </>
    )
}
export default LeftSide;