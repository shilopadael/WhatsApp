

function authHeader() {
    // setting the token in the current user that logged in
    // getting the token from the cookie
    let token = sessionStorage.getItem("token");
    const userToken = JSON.parse(token);

    if(userToken) {
        return { Authorization: `bearer ${userToken}` };
    } else {
        return {};
    }
}

export default authHeader;