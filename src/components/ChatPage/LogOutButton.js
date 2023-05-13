import { useNavigate } from "react-router-dom";
import { useState } from "react";
import './LogOutButton.css';

function LogOut(props) {
    const navigate = useNavigate();
    const { setAuthenticated } = props;
    const [logOutFade, setLogOutFade] = useState(false);

    const handleLogOut = () => {
        setAuthenticated(false);
        localStorage.setItem("authenticated", false);
        navigate('/', () => {
            console.log("Logging Out...");
        });
        setLogOutFade(true); // Update the state variable to show the fade
    };

    const handleModalClose = () => {
        setLogOutFade(false); // Update the state variable to hide the fade
    };

    return (
        <div>
            <button
                type="button"
                id=""
                className="btn btn-danger"
                data-bs-toggle="modal"
                data-bs-target="#logOutModal"
            > logOut</button>
            <div className={`modal fade ${logOutFade ? 'show' : ''}`} id="logOutModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header bg-dark">
                            <h5 className="modal-title logOutModalTitle">
                                Are You Sure You Want To Log Out?
                            </h5>
                            <button
                                type="button"
                                className="btn-close buttonClose"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                                onClick={handleModalClose} // Handle the modal close event
                            ></button>
                        </div>
                        <div className="modal-body danger-btn">
                            <form>
                                <button
                                    type="submit"
                                    className="btn btn-danger text-light"
                                    data-bs-dismiss="modal"
                                    onClick={handleLogOut}>
                                    Yes, I'm Sure
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            {logOutFade && <div className="modal-backdrop fade show"></div>}
        </div>
    )
}

export default LogOut;
