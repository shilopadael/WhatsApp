
import { useState } from 'react';

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
        selectedContact } = props;
    
    const { displayName, profilePic } = user;
    let unRead = null;


    const [showContextMenu, setShowContextMenu] = useState(false);
    const [contextMenuPosition, setContextMenuPosition] = useState({ x: 0, y: 0 });

    function goToChat() {
        setSelectedContact(id);
        setCurrentChatId(id);
        setContactFullPage(false);
    }

    function handleRightClick(e) {
        e.preventDefault();
        if (showContextMenu == true) {
            setShowContextMenu(false);
            setContextMenuPosition({ x: 0, y: 0 })
        } else {
            setShowContextMenu(true); // Show the custom context menu
            setContextMenuPosition({ x: e.clientX, y: e.clientY }); // S
        }
    }

    function handleDeleteUser(e) {
        e.preventDefault();
        let updatedLst = contacts.filter((contact) => contact.id !== id);
        setContacts(updatedLst);
        setContactToShow(updatedLst)
        setShowContextMenu(false);
        setContextMenuPosition({ x: 0, y: 0 })
    }

    const displayTime = (lastTime) => {
        let createdDate = new Date(lastTime);
        // if the current data is the same date today then display hourse and minutes
        if()
        let hours = createdDate.getHours();
        let minutes = createdDate.getMinutes();
        let time = hours + ":" + minutes;
        return time;
    }

    const displayMessage = (message) => {
        if (message === null) {
            return "";
        } else {
            let msg;
            if(message.content.length > 20) {
                msg = message.content.substring(0, 20) + "...";
            } else {
                msg = message.content;
            }
            return msg;
        }
    }

    return (
        <>
            <li className={`d-flex contact-list-item ${selectedContact === id && !contactFullPage ? 'selected' : ''}` } onClick={goToChat} onContextMenu={handleRightClick}>
                {showContextMenu && (
                    <div
                        className="col-4 pt-1"
                        style={{ top: contextMenuPosition.y, left: contextMenuPosition.x }}
                    >
                        <button className='btn btn-danger' onClick={handleDeleteUser}>Delete</button>
                    </div>
                )}
                <div className="col-3 m-0">
                    <img src={profilePic} className="rounded-circle profile-image"
                        alt="profile img"></img>
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
