
import Data from "../data";
import { createContext, useContext } from "react";

const ContactsContext = createContext();

export function ContactsProvider({ children }) {
  const {
    currentUser,
    contacts,
    chats,
    currentChatId,
    setContacts,
    setChats,
    setCurrentChatId,
    setCurrentUser,
  } = Data();

  return (
    <ContactsContext.Provider
      value={{
        currentUser,
        contacts,
        chats,
        currentChatId,
        setContacts,
        setChats,
        setCurrentChatId,
        setCurrentUser,
      }}
    >
      {children}
    </ContactsContext.Provider>
  );
}

export function useContacts() {
  return useContext(ContactsContext);
}
