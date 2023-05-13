

function Users(props) {
    // const { setCurrentChatId } = useContacts();
    // console.log(chat);
    const { id , name , image , lastSeen , unRead  , setCurrentChatId , setContactFullPage } = props;

    function goToChat(){
        setCurrentChatId(id);
        setContactFullPage(false);
    }

    return (
        <> 
            <li className="list-group-item d-flex contact-list-item contact-list-hover" onClick={goToChat}>
                <div className="col-10 m-0">
                    <img src={image} class="rounded-circle profile-image"
                        alt="profile img"></img>
                    <span className="p-2">{name}</span>
                </div>
                <div className="col-2 text-end">
                    <div className="col-12 last-seen opacity">{lastSeen}</div>
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
