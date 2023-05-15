
function TopBarRightSlide(props) {
    // const { currentUser } = Data();
    const { currentUser, setContactFullPage } = props;

    // when pressing the back button going back to the chat contacts
    const changeToChatHandle = () => {
        setContactFullPage(true)
    }

    return (
        <nav className="navbar navbar-expand-sm top-chat p-0 m-0 linerLine-color-right">
            <div className="container-fluid small-screen">
                <a className="col-1 back-to-contact-icon" onClick={changeToChatHandle}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-backspace" viewBox="0 0 16 16">
                        <path d="M5.83 5.146a.5.5 0 0 0 0 .708L7.975 8l-2.147 2.146a.5.5 0 0 0 .707.708l2.147-2.147 2.146 2.147a.5.5 0 0 0 .707-.708L9.39 8l2.146-2.146a.5.5 0 0 0-.707-.708L8.683 7.293 6.536 5.146a.5.5 0 0 0-.707 0z" />
                        <path d="M13.683 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7.08a2 2 0 0 1-1.519-.698L.241 8.65a1 1 0 0 1 0-1.302L5.084 1.7A2 2 0 0 1 6.603 1h7.08zm-7.08 1a1 1 0 0 0-.76.35L1 8l4.844 5.65a1 1 0 0 0 .759.35h7.08a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1h-7.08z" />
                    </svg>
                </a>
                <div className="col-1">
                    <img
                        src={currentUser.image}
                        className="rounded-circle topBarRightSlide profile-image"
                        alt="chatUserImg"
                    />
                </div>
                <div className="col-11">
                    <div>
                        <div className="row">
                            <div className="profile-nav-chat-info">
                                <h6 className="profile-text-chat">{currentUser.name}</h6>
                                <p className="profile-text-about">{currentUser.status}</p>
                            </div>
                        </div>
                    </div>
                </div>
                {/* <div className="col-10">

                </div> */}
                {/* <div className="col-10 search-icon">
                    <span>
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width={22}
                            height={22}
                            fill="currentColor"
                            className="bi bi-search mx-4"
                            viewBox="0 0 16 16"
                        >
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                        </svg>
                    </span>
                </div> */}
                {/* <div className="col-1">
                    <span>
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width={22}
                            height={22}
                            fill="currentColor"
                            className="bi bi-three-dots-vertical mx-3"
                            viewBox="0 0 16 16"
                        >
                            <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
                        </svg>
                    </span>
                </div> */}
            </div>
        </nav>

    );
}
export default TopBarRightSlide;
