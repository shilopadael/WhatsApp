import FormInput from "./FormInput";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import auth from "../../services/auth-service";


function LoginForm(props) {

    const { setAuthenticated } = props;
    const navigate = useNavigate();

    const formRef = useRef(null);

    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        // getting username
        let username = formRef.current[0].value;

        // getting password
        let password = formRef.current[1].value;

        // setting the token from the server
        let req = await auth.login(username, password);
        if(req === true) {
            // retrievied the toekn from the server
            // navigate to the chat page
            setAuthenticated(true);
            navigate("/chat");
        } else {
            // alert the user that the username or password are incorrect
            alert("The email or password are incorrect.");
        }
     
    }

    return (
        <form ref={formRef} className="m-4" id="loginForm" onSubmit={handleLoginSubmit}>
            <FormInput type="text" id="loginUsername" placeholder="enter your username" className="form-control" label="Username" required={true} pattern="^.*$" />
            <FormInput type="password" id="loginPassword" placeholder="enter your password" className="form-control" label="Password" required={true} pattern="^.*$" />
            <div className="form-group form-check">
                {/* <div className="row">
                    <div className="col-md-6">
                        <input type="checkbox" className="form-check-input" id="loginRemmemberMeCheckBox" ></input>
                        <label
                            className="form-check-label"
                            htmlFor="loginRemmemberMeCheckBox"
                        >
                            Remmember me!
                        </label>
                    </div>
                    <div className="col-md-6">
                        <a href="#" className="text-decoration-none">
                            Forgot Password?
                        </a>
                    </div>
                </div> */}
            </div>
            <div className="col-md-3" id="">
                <button type="submit" className="btn btn-primary btnLogInSignUp">
                    Sign in
                </button>
            </div>
        </form>
    );
}

export default LoginForm;

// getting the email and password from the form

        // let username = formRef.current[0].value;
        // let password = formRef.current[1].value;
        // var token = "";
        // let user = {
        //     "username": username,
        //     "password": password
        // }

        // //checking if the email and password are correct in the userlst
        // for (let i = 0; i < users.length; i++) {
        //     if (users[i].username === username && users[i].password === password) {
        //         alert("You have successfully logged in.");

        //         setAuthenticated(true);
        //         const userData = users[i];
        //         localStorage.setItem("authenticated", true);
        //         navigate("/chat", {state: userData});
        //         return;
        //     }
        // }
        // alert("The email or password are incorrect.");
        // setAuthenticated(false);
        // localStorage.setItem("user", null);
        // localStorage.setItem("authenticated", false);

        // fetching the token from the server
        // let serverReq = await fetch(`${server}/api/Tokens`, {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json",
        //     },
        //     body: JSON.stringify(user),
        // });

        // if (serverReq.ok) {
        //     // retreiving the token from the server
        //     token = await serverReq.text();
        // } else {
        //     // alert the user that the username or password are incorrect
        //     alert("The email or password are incorrect.");
        // }

        // console.log("token: " + token);