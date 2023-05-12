import { ContactsProvider } from "./ChatPage/ContactsContext";
import MainBlock from "./ChatPage/MainBlock";
import { useEffect, useState } from "react";

function Chat(props) {
  const { authenticated } = props;
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (authenticated) {
      // Retrieve user data from local storage
      const userData = JSON.parse(localStorage.getItem("user"));
      setUser(userData);
    }
  }, [authenticated]);

  if (!authenticated) {
    return (
      <div>
        <h1>This zone is for users only! please log in!</h1>
      </div>
    );
  } else {
    if (user === null) {
      return <div>Loading user data...</div>;
    }
    return (
      <>
        <ContactsProvider>
          <div className="topScreen"></div>
          <div className="lowerScreen"></div>
          <MainBlock user={user} />
        </ContactsProvider>
      </>
    );
  }
}

export default Chat;
