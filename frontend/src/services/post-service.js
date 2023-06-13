import { calculateNewValue } from "@testing-library/user-event/dist/utils";
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

 const DeleteContact = async (id) => {
    let header = authHeader();
    try{
        let serverReq = await fetch(`${SERVER_API}/api/Chats/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": header.Authorization,
            },
        });
        // if the server return ok
        if (serverReq.ok) {
            return true;
        } else {
            let error = await serverReq.text();
            localStorage.setItem("error", error);
            return false;
        }
    } catch(e) {
        localStorage.setItem("error", e.message);
        return false;
    }   
 }

 const Message = async (id, content) => {
    let header = authHeader();

    let data = {
        "msg" : content
    }
    try{
        let serverReq = await fetch(`${SERVER_API}/api/Chats/${id}/Messages`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": header.Authorization,
            },
            body: JSON.stringify(data),
        });
        // if the server return ok
        if (serverReq.ok) {
            let data = await serverReq.json();
            console.log(data);
            return data;
        } else {
            let error = await serverReq.text();
            localStorage.setItem("error", error);
            return null;
        }
    } catch(e) {
        localStorage.setItem("error", e.message);
        return null;
    }   
 }


const post = {
    Contact,
    DeleteContact,
    Message,
};

export default post;