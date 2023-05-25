
import { useState } from 'react';
import post from '../../../services/post-service';

function MessageNav(props) {
    const { setUserMessages, user , setNewMsg , newMsg , userMessages} = props;
    // const { setChats,chats, currentChatId , currentUser} = useContacts();
    const [newItem, setNewItem] = useState("");


    async function sendMessage(e) {
        e.preventDefault();

        let serverReq = await post.Message(user.id, newItem);
        if(serverReq !== null) {
            // success
            setUserMessages([...userMessages , serverReq]);
            setNewItem("");
        } else {
            // error
            alert(localStorage.getItem("error"));
            return;
        }

        // let timestamp = current.toLocaleTimeString("en-US", {
        //     hour: "2-digit",
        //     minute: "2-digit",
        //     hour12: false
        // })
        // userChat.push({ sender: "me", message: newItem, time: timestamp });
        // setMessage({ e });
        // if(newItem.length > 20) {
        //     user.lastMessage = newItem.slice(0, 20) + "...";
        // } else {
        //     user.lastMessage = newItem;
        // }
        // user.lastMessageTime = timestamp;
        // user.lastMessageDate = current;
        // // print all contacts
        // setContacts([...contacts]);
        // setNewItem("");
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-light linerLine-color-right bottom-chat">
            <div className="container-fluid ">
                <div className="col-1 text-center">
                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" className="bi bi-emoji-smile" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                        <path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z" />
                    </svg>
                </div>
                <div className="col-1 text-left">
                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" className="bi bi-paperclip" viewBox="0 0 16 16">
                        <path d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z" />
                    </svg>
                </div>

                <div className="col-10 text-left">
                    <form
                        onSubmit={sendMessage}
                        className="d-flex">
                        <input
                            onChange={(e) => setNewItem(e.target.value)}
                            className="form-control me-2"
                            type="search"
                            placeholder="Type your message"
                            value={newItem}
                            aria-label="Search"></input>
                        <button id="record-button"
                            className="btn"
                            type="submit"
                            disabled={!newItem}>
                            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" className="bi bi-record" viewBox="0 0 16 16">
                                <path d="M8 12a4 4 0 1 1 0-8 4 4 0 0 1 0 8zm0 1A5 5 0 1 0 8 3a5 5 0 0 0 0 10z" />
                            </svg>
                        </button>
                    </form>

                </div>
            </div>
        </nav>
    );
}

export default MessageNav;