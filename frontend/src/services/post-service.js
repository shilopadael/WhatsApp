import SERVER_API from "./api";
import authHeader from "./auth-headers";

// all the function in the program that user the post service
const Contact = async (contact) => {
    // posting the contact to the server

    let header = authHeader();
    let data = {
        "username": contact
    }

    let serverReq = await fetch(`${SERVER_API}/api/Chats`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": header.Authorization,
        },
        body: JSON.stringify(data),
    });

    if (serverReq.ok) {
        return await serverReq.json();
    }
    else {
        localStorage.setItem("error", await serverReq.text());
        return null;
    }
 }


const post = {
    Contact,
};

export default post;