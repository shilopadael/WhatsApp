import SERVER_API from "./api";
import authHeader from "./auth-headers";

// all the function in the program for get methods

const Chats = async () => {

    let header = authHeader();
    let serverReq = await fetch(`${SERVER_API}/api/Chats`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": header.Authorization,
        },
    })

    if (serverReq.ok) {
        let chats = await serverReq.json();
        // got the usernames contact list, now need to get the contact information
        let userInformation = await Promise.all(chats.map(async (chat) => {
            return await chat;
        }));
        return userInformation;
    } else {
        localStorage.setItem("error" , serverReq.text());
    }

    return null;
}

// const ContactInformation = async (username) => {
//     // getting data from the server
//     let header = authHeader();
//     const serverReq = await fetch(`${SERVER_API}/api/Users/${username}`, {
//         method: "GET",
//         headers: {
//             "Content-Type": "application/json",
//             "Authorization": header.Authorization,
//         },
//     });

//     if (serverReq.ok) {
//         let contactInfo = await serverReq.json();
//         return contactInfo;
//     }

//     return null;

// }

const Messages = async (id) => { 
    let header = authHeader();
    const serverReq = await fetch(`${SERVER_API}/api/Chats/${id}/Messages`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": header.Authorization,
        },
    });

    if (serverReq.ok) {
        let messages = await Promise.all(serverReq.json());
        return messages;
    }

    return null;
}

const LastMessageDate = async (id) => {
    let header = authHeader();
    const serverReq = await fetch(`${SERVER_API}/api/Chats/${id}/Messages`, {
        method: "GET",
        headers: {
            "Content-Type": "text/plain",
            "Authorization": header.Authorization,
        },
        
    });
    if (serverReq.ok) {
        let messages = await serverReq.json();
        if (messages.length > 0) {
            return messages[messages.length - 1].created;
        } else {
            return null;
        }
    } else {
        localStorage.setItem("error", await serverReq.text());
        return null;
    }

}


const get = {
    Chats,
    LastMessageDate,
}

export default get;