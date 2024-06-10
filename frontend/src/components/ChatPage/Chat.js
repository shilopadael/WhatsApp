import MainBlock from "./MainBlock";
import { useEffect, useState, useMemo } from "react";
import auth from "../../services/auth-service";
import defaultImg from '../../assets/registerImgs/profile.png'
import { io } from "socket.io-client";
import { SERVER_API_WS } from "../../services/api";

const defaultUser = {
  username: "",
  displayName: "",
  profilePic: defaultImg,
}

function Chat(props) {

  const { authenticated, setAuthenticated } = props;
  const [user, setUser] = useState(null);
  const [socket, setSocket] = useState(null); // messages
  const [online, setOnline] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      if (authenticated) {
        // trying to get user information from the database
        var userData = await auth.getCurrentUser();
        if (userData !== null) {
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


  useEffect(() => {
    if (socket || online) {
      // nothing
    }
    else if (user) {
      const socket = io.connect(SERVER_API_WS, { query: { username: user.username } });
      setSocket(socket);
      setOnline(true);

      socket.on('alert', (data) => {
        alert(`${data.data.sender.username} sent you: ${data.data.content}`);
      });

    }
    return () => {
      if (socket) {
        socket.close();
      }
    }
  }, [user]);



  if (!authenticated) {
    return (
      <div className="container border shadow container-lg container-md container-sm defaultChatMessageContainer">
        <div className="row no-gutters h-100 black">
          <h2 className="defaultChatMessage">This zone is for users only! please log in to access.</h2>
        </div>
      </div>
    );
  } else if (user === null || socket === null) {
    <>
      <div className="topScreen"></div>
      <div className="lowerScreen"></div>
    </>
  }
  else {
    return (
      <>
        <div className="topScreen"></div>
        <div className="lowerScreen"></div>
        <MainBlock user={user}
          setAuthenticated={setAuthenticated}
          socket={socket} />
      </>
    );
  }
}

export default Chat;
