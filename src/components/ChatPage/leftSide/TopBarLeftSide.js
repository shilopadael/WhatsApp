
import AddContact from './addContact';
import defaultProfile from '../../../assets/registerImg/profile.png'

function TopBarLeftSide(props) {

    const { contacts ,user, setContacts , setCurrentChatId} = props;

    const userImage = user?.image || defaultProfile;
    const displayName = user?.displayName || '';

    return (<>
        <nav class="navbar navbar-expand-lg line-up p-0">
            <div class="container-fluid dark-blue1 rounded m-0 p-1">
                <div class="col-10">
                    <a class="navbar-brand" href="#">
                        <img src={userImage} class="rounded-circle profilePic" alt="Logo"></img>
                    </a>
                    <span className="profileName">{displayName}</span>
                </div>
                <div class="col-2">
                    <AddContact setContacts={setContacts} contacts={contacts} setCurrentChatId={setCurrentChatId}/>
                </div>
            </div>
        </nav>
        <div>
            <nav class="navbar navbar-light">
                <div class="container-fluid">
                    <form class="d-flex">
                        <input class="form-control border-none" type="search" placeholder="Search contact" aria-label="Search"></input>
                    </form>
                </div>
            </nav>
        </div>
    </>
    );
}
export default TopBarLeftSide;