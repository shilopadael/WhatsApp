import Data from "../../data";
import Receiver from "./Receiver";
import Sender from "./sender";
import { useContacts } from '../ContactsContext';


function BoxMessage(props) {
    const { user , userChat} = props;
    const { chats, currentChatId , currentUser} = useContacts();
    // // Assuming the user ID is stored in a variable called 'userId'
    // const user = chats.find(chat => chat.id === currentUser.id);
    // // console.log(user);
    // const userChat = user.chats.find(chat => chat.id === currentChatId);
    // console.log(userChat);

    return (
        <>
            <div class="text-chat BoxMessage">
                {console.log(userChat)}
                {userChat.messages.map((mail, index) => {
                    if (mail.sender === "me") {
                        return <Sender key={index} message={mail.message} />;
                    } 
                    if (mail.sender === "them") {
                        return <Receiver key={index} message={mail.message} />;
                    } else {
                        return;
                    }
                })}

            </div>
        </>
    );
}
export default BoxMessage;
