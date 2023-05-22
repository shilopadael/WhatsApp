
import { useState } from 'react';

function Users(props) {

    // const newContact = {
    //     id: newId,
    //     name: name,
    //     image: defaultProfile,
    //     lastMessageTime: "",
    //     lastMessageDate: "",
    //     unRead: 0,
    //     lastMessage: "",
    //     messages: [],
    //     status: data[random],
    //     currentChat: false,
    //   };
    const { id,
        displayName,
        profilePic,
        lastMessage,
        lastMessageTime,
        unRead,
        setCurrentChatId,
        setContactFullPage,
        contactFullPage,
        setContacts,
        contacts,
        setContactToShow,
        setSelectedContact,
        selectedContact} = props;


    const [showContextMenu, setShowContextMenu] = useState(false);
    const [contextMenuPosition, setContextMenuPosition] = useState({ x: 0, y: 0 });

    function goToChat() {
        setSelectedContact(id);
        console.log("in user id: " + id + " changing the current chat id")
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
                        <span className="p-0 text-muted">{lastMessage}</span>
                    </div>
                </div>
                <div className="col-2 text-end">
                    <span className="col-12 last-seen opacity">{lastMessageTime}</span>
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
