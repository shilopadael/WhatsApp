
import logo from "../../assets/logo.svg"
function ChatSystemLogo() {
    return (
        <div className="row">
            <div className="col-xl-10 p-4 mx-auto" id="topLogo">
                <img src={ logo } id="logo" />
                <strong>Chat System</strong>
            </div>
        </div>
    );
}

export default ChatSystemLogo;