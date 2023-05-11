
// import profilePic from '../../../assests/images/shilo.jpg';
import Data from '../../data';
import AddContact from './addContact';

function TopBarLeftSide(props) {
    // const { currentUser } = Data();
    
    const { id, user, setUsers, users } = props;
    console.log("hola");
    console.log(user);
    console.log("hola");
    return (<>
                         <nav class="navbar navbar-expand-lg navbar-expand-md navbar-expand-sm navbar-light line-up p-1">
                            <div class="container-fluid dark-blue1 rounded-pill">
                                <div class="col-10">
                                    <a class="navbar-brand" href="#">
                                        <img src={user.image} class="rounded-circle" alt="Logo"></img>
                                    </a>

                                </div>
                                <div class="col-2">
                                    <AddContact {...props} />

                                </div>
                            </div>
                        </nav>
                        <div>
                            <nav class="navbar navbar-light left-slide rounded-circle">
                                <div class="container-fluid rounded-pill left-slide">
                                <form class="d-flex">
                                    <input class="form-control me-2 rounded-pill left-slide border-none" type="search" placeholder="Search" aria-label="Search"></input>
                                    <button class="btn" type="submit">
                                        <svg xmlns="http://www.w3.org/2000/svg"  width="22" height="22" fill="currentColor" class="bi bi-search mx-4"  viewBox="0 0 16 16">
                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                        </svg>
                                    </button>
                                </form>
                                </div>
                            </nav>
                        </div>
            </>
    );
}
export default TopBarLeftSide;