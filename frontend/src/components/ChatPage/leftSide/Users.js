
import { useState } from 'react';
import post from '../../../services/post-service';

function Users(props) {

    // let data = {
    //     id: user.id,
    //     user: user,
    //     lastMessage: null,
    //   }

    const { id,
        user,
        lastMessage,
        setCurrentChatId,
        setContactFullPage,
        contactFullPage,
        setContacts,
        contacts,
        setContactToShow,
        setSelectedContact,
        selectedContact,
        currentChatId,
        socket } = props;

    const { displayName, profilePic } = user;
    let unRead = null;


    const [showContextMenu, setShowContextMenu] = useState(false);
    const [contextMenuPosition, setContextMenuPosition] = useState({ x: 0, y: 0 });

    function goToChat() {
        if (showContextMenu) {
            return;
        }
        if (selectedContact !== id && !contactFullPage) {
            setSelectedContact(id);
            setCurrentChatId(id);
        }
        else if (contactFullPage) {
            setSelectedContact(id);
            setCurrentChatId(id);
            setContactFullPage(false);
        }
    }

    function handleRightClick(e) {
        e.preventDefault();
        if (showContextMenu === true) {
            setShowContextMenu(false);
            setContextMenuPosition({ x: 0, y: 0 })
        } else {
            setShowContextMenu(true); // Show the custom context menu
            setContextMenuPosition({ x: e.clientX, y: e.clientY }); // S
        }
    }

    async function handleDeleteUser(e) {
        e.preventDefault();
        console.log(selectedContact == id);
        // deleting the user from the server
        let deletedUser = await post.DeleteContact(id);
        if (deletedUser === true) {
            let updatedLst = contacts.filter((contact) => contact.id !== id);
            setContacts(updatedLst);
            setContactToShow(updatedLst)
            setShowContextMenu(false);
            setContextMenuPosition({ x: 0, y: 0 })
            socket.emit('remove-contact', { username: user.username, id: id});
        }
        else {
            alert(localStorage.getItem("error"));
        }
        if (currentChatId == id) {
            setContactFullPage(true);
        }
    }

    const displayTime = (lastTime) => {
        let createdDate = new Date(lastTime);
        let todayDate = new Date();
        let todayDateFormat = todayDate.getDate() + "." + (todayDate.getMonth() + 1) + "." + todayDate.getFullYear();
        let createdDateFormat = createdDate.getDate() + "." + (createdDate.getMonth() + 1) + "." + createdDate.getFullYear();
        if (todayDateFormat === createdDateFormat) {
            let hours = createdDate.getHours();
            let minutes = createdDate.getMinutes();
            let time = hours + ":" + minutes;
            return time;
        } else {
            // displaying the date
            return todayDateFormat;
        }
    }

    const displayMessage = (message) => {
        if (message === null) {
            return "";
        } else {
            let msg;
            if (message.content.length > 20) {
                msg = message.content.substring(0, 20) + "...";
            } else {
                msg = message.content;
            }
            return msg;
        }
    }

    return (
        <>
            <li className={`d-flex contact-list-item ${selectedContact === id && !contactFullPage ? 'selected' : ''}`}
                onClick={goToChat}
                onContextMenu={handleRightClick}
                data-bs-toggle="tooltip" // Add the data-bs-toggle attribute for tooltip
                data-bs-custom-class="custom-tooltip"
                data-bs-placement="top"
                title="Right Click To Delete" // Specify the tooltip text
            >
                {showContextMenu && (
                    <div
                        className="col-4 pt-1"
                        style={{ top: contextMenuPosition.y, left: contextMenuPosition.x }}
                    >
                        <button className='btn btn-danger deleteUserBtn' onClick={handleDeleteUser}>Delete</button>
                    </div>
                )}
                <div className="col-3 m-0">
                    <img src={profilePic} className="rounded-circle profile-image"
                        alt=""></img>
                </div>
                <div className="col-6">
                    <div className="row">
                        <span className="p-0 m-0">{displayName}</span>
                        <span className="p-0 text-muted">{displayMessage(lastMessage)}</span>
                    </div>
                </div>
                <div className="col-2 text-end">
                    <span className="col-12 last-seen opacity">{lastMessage ? displayTime(lastMessage.created) : ''}</span>
                    <div>
                        {/*if lastSeen is 0, then don't show the badge*/}
                        {unRead ? <span className="badge bg-primary rounded-pill">{unRead}</span> : null}
                    </div>
                </div>
            </li>
        </>

    );
}


export default Users;
