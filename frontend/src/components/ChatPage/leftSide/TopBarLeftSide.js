
import AddContact from './AddContact';
import defaultProfile from '../../../assets/registerImgs/profile.png'
import LogOut from './LogOut';

function TopBarLeftSide(props) {

    const { contacts, user, setContacts, setCurrentChatId, contactToShow, setContactToShow ,setAuthenticated, setAddContact} = props;
    const userImage = user?.profilePic || defaultProfile;
    const displayName = user?.displayName || '';

    function changeUserImg(event) {
        event.preventDefault();
        //TODO
    }

    return (
        <nav className="navbar navbar-expand-lg line-up p-0 leftNav linerLine-color">
            <div className="container-fluid rounded m-0 p-1">
                <div className="col-9">
                    <a className="navbar-brand" href="" onContextMenu={changeUserImg} onClick={(e) => e.preventDefault()}>
                        <img src={userImage} className="rounded-circle profilePic" alt="Logo"></img>
                    </a>
                    <span className="profileName">{displayName}</span>
                </div>
                <div className='col-1'>
                    <LogOut setAuthenticated={setAuthenticated}/>

                </div>
                <div className="col-2">
                    <AddContact setContacts={setContacts}
                        contacts={contacts}
                        setCurrentChatId={setCurrentChatId}
                        contactToShow={contactToShow}
                        setContactToShow={setContactToShow} 
                        setAddContact={setAddContact}/>
                </div>
            </div>
        </nav>
    );
}
export default TopBarLeftSide;