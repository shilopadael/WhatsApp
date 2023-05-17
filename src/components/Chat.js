import MainBlock from "./ChatPage/MainBlock";
import { useEffect, useState } from "react";
import LogOut from "./ChatPage/LogOutButton";
import { useLocation } from "react-router-dom";

function Chat(props) {
  const {state} = useLocation();
  const { authenticated, setAuthenticated } = props;
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (authenticated) {
      // Retrieve user data from local storage
      // const userData = JSON.parse(localStorage.getItem("user")); // connect database here
      const userData = state;
      setUser(userData);
    }
  }, [authenticated]);

  if (!authenticated) {
    return (
      <div className="container border shadow container-lg container-md container-sm defaultChatMessageContainer">
        <div className="row no-gutters h-100 black">
          <h2 className="defaultChatMessage">This zone is for users only! please log in to access.</h2>
        </div>
      </div>
    );
  } else {
    if (user === null) {
      return <div>Loading user data...</div>;
    }
    return (
      <>
        <div className="topScreen"></div>
        {/* <LogOut setAuthenticated={setAuthenticated} /> */}
        <div className="lowerScreen"></div>
        <MainBlock user={user}  setAuthenticated={setAuthenticated} />
      </>
    );
  }
}

export default Chat;
