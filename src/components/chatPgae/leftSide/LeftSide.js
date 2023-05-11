

import TopBarLeftSide from './TopBarLeftSide';
import Users from './Users';
// import Data from '../../data';
import { useContacts } from '../ContactsContext';



function LeftSide(promp) {
    const {users, id , setUsers} = promp;
    const user = users.find(chat => chat.id === id);
    const chat = user.chats;
    // console.log(chat);


    // const { chats,currentUser } = useContacts();
    // const user = chats.find(chat => chat.id === currentUser.id);
    // const chat = user.chats;
    // console.log(user.chats);

    console.log(user);

    function showContacts() {

        return user.chats.map((chat) => {
            // console.log(chat);
            return <Users {...chat}/>
    
        });

    }
    return (
        <>
                     <div class="col-4 left-slide justify-content-between border-right">
                            <TopBarLeftSide user={user} id={id} setUsers={setUsers} users={users}/>
                            <ul class="list-group chats">
                                {showContacts()}
                            </ul>
                            
                    </div>
        </>
    )
}
export default LeftSide;