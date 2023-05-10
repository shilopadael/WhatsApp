

import imageJoe from '../../../assests/images/joe.jpeg';
import imageOmer from '../../../assests/images/Omer.png';
import Data from '../../data';
import { useContacts } from '../ContactsContext';




function Users(chat) {
    const { setCurrentChatId } = useContacts();

    function goToChat(){
        setCurrentChatId(chat.id);
    }

    return (
        <> 
            <li class="list-group-item d-flex chatList" onClick={goToChat}>
                <div class="col-10 m-0">
                    <img src={chat.image} class="rounded-circle pic"
                        alt="Image"></img>
                        <span class="p-2">{chat.name}</span>
                </div>
                <div class="col-2 text-end">
                    <div class="col-12 last-seen opacity">{chat.lastSeen}</div>
                    <div>
                    {/*if lastSeen is 0, then don't show the badge*/}
                    {chat.unRead ? <span class="badge bg-primary rounded-pill">{chat.unRead}</span> : null}
                    </div>   
             </div>
            </li>

        </>

    );
}

export default Users;
