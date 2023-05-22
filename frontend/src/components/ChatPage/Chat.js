import MainBlock from "./MainBlock";
import { useEffect, useState } from "react";
import auth from "../../services/auth-service";
import defaultImg from '../../assets/registerImgs/profile.png'

const defaultUser ={
  username: "",
  displayName: "",
  profilePic: defaultImg,
}

function Chat(props) {

  const { authenticated, setAuthenticated } = props;
  const [user, setUser] = useState(defaultUser);

  useEffect(() => {
    const fetchData = async () => {
      if (authenticated) {
        // trying to get user information from the database
        var userData = await auth.getCurrentUser();
        if (userData !== null) {
          setUser(userData);
        } else {
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
    return (
      <>
        <div className="topScreen"></div>
        <div className="lowerScreen"></div>
        <MainBlock user={user} setAuthenticated={setAuthenticated} />
      </>
    );
  }
}

export default Chat;
