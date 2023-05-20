import MainBlock from "./ChatPage/MainBlock";
import { useEffect, useState } from "react";
import LogOut from "./ChatPage/LogOutButton";
import { useLocation } from "react-router-dom";
import auth from "../services/auth-service";

function Chat(props) {
  const { state } = useLocation();
  const { authenticated, setAuthenticated } = props;
  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      if (authenticated) {
        // trying to get user information from the database
        var userData = await auth.getCurrentUser();
        if (userData !== null) {
          console.log(userData);
          setUser(userData);
        } else {
          setAuthenticated(false);
          auth.logout();
        }
      }
    };

    // Invoking the asynchronous function
    fetchData();

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
        <MainBlock user={user} setAuthenticated={setAuthenticated} />
      </>
    );
  }
}

export default Chat;
