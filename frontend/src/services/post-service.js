import SERVER_API from "./api";
import authHeader from "./auth-headers";

// all the function in the program that user the post service
const Contact = async (contact) => {

    // posting the contact to the server
    let header = authHeader();
    let serverReq = await fetch(`${SERVER_API}/api/Chats`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": header.Authorization,
        },
        body: JSON.stringify(contact),
    });

    if (serverReq.ok) {
        return true;
    }
    else {
        return false;
    }
 }


const post = {
    Contact,
};

export default post;