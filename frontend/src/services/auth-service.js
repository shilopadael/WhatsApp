import {SERVER_API} from "./api";
import authHeader from "./auth-headers";

// all the function in the program that needs authentication

const login = async (username, password) => {
    // retreving the user from the server

    let user = {
        "username": username,
        "password": password
    };
    try {
        let serverReq = await fetch(`${SERVER_API}/api/Tokens`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        })

        if (serverReq.ok) {
            // retreiving the token from the server
            let token = await serverReq.text();
            sessionStorage.setItem("token", JSON.stringify(token));
            sessionStorage.setItem("username", username);
            return true;
        } else {
            let error = await serverReq.text();
            localStorage.setItem("error", error);
        }
        return false;
    } catch (err) {
        localStorage.setItem("error", err.message);
        return false;
    }

};

const register = async (username, password, displayName, img) => {

    let data = {
        "username": username,
        "password": password,
        "displayName": displayName,
        "profilePic": img
    };
    try {
        const serverReq = await fetch(`${SERVER_API}/api/Users`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        })

        if (serverReq.ok) {
            return true;
        } else if (serverReq.status >= 400 && serverReq.status < 500) {
            // problem at the user
            let error = await serverReq.text();
            localStorage.setItem("error", error);
        }
        return false;
    } catch (err) {
        localStorage.setItem("error", err.message);
        return false;
    }

};

const logout = () => {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("username");
};

const getCurrentUser = async () => {
    let username = sessionStorage.getItem("username");
    // getting data from the server
    let header = authHeader();
    try {
        const serverReq = await fetch(`${SERVER_API}/api/Users/${username}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": header.Authorization,
            },
        });

        if (serverReq.ok) {
            const user = await serverReq.json();
            return user;
        } else {
            return null;
        }
    } catch (err) {
        return null;
        alert(err.message);
        // failed to fetch user disconnecting
        logout();
    }


};


const auth = {
    login,
    logout,
    getCurrentUser,
    register,
};

export default auth;