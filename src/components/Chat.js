
import { ContactsProvider } from "./chatPgae/ContactsContext";
import MainBlock from "./chatPgae/MainBlock";
import {useContacts} from "./chatPgae/ContactsContext";
import { useParams } from 'react-router-dom';

function Chat(props) {
    // const { users, setUsers } = props;
    let { id } = useParams();
    id = 1;
    const {users, setUsers} = props;
    const contactsProps = useContacts();
    return (
        <>
            <ContactsProvider>
                <div class="topScreen"></div>
                <div class="lowerScreen"></div>
                <MainBlock {...contactsProps} users={users} setUsers={setUsers} id={id}/>
            </ContactsProvider>

        </>
    );
}

export default Chat;