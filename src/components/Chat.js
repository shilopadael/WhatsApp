
import { ContactsProvider } from "./chatPgae/ContactsContext";
import MainBlock from "./chatPgae/MainBlock";

function Chat() {
    return (
        <>
            <ContactsProvider>
                <div class="topScreen"></div>
                <div class="lowerScreen"></div>
                <MainBlock />
            </ContactsProvider>

        </>
    );
}

export default Chat;