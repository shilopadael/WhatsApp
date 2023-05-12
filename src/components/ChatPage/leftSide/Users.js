

function Users(props) {
    // const { setCurrentChatId } = useContacts();
    // console.log(chat);
    const { id , name , image , lastSeen , unRead  , setCurrentChatId } = props;

    function goToChat(){
        localStorage.setItem("currentChatId", id);
        console.log("chaning id to " + id);
        setCurrentChatId(id);
    }

    return (
        <> 
            <li class="list-group-item d-flex chatList" onClick={goToChat}>
                <div class="col-10 m-0">
                    <img src={image} class="rounded-circle profile-image"
                        alt="profile img"></img>
                    <span class="p-2">{name}</span>
                </div>
                <div class="col-2 text-end">
                    <div class="col-12 last-seen opacity">{lastSeen}</div>
                    <div>
                    {/*if lastSeen is 0, then don't show the badge*/}
                    {unRead ? <span class="badge bg-primary rounded-pill">{unRead}</span> : null}
                    </div>   
             </div>
            </li>

        </>

    );
}

export default Users;
