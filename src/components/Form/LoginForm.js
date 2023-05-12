
import FormInput from "./FormInput";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";


function LoginForm(props) {

    const { users , setAuthenticated } = props;
    const navigate = useNavigate();

    const formRef = useRef(null);
    

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        // getting the email and password from the form
        
        let email = formRef.current[0].value;
        let password = formRef.current[1].value;

        //checking if the email and password are correct in the userlst
        for (let i = 0; i < users.length; i++) {
            if (users[i].email === email && users[i].password === password) {
                alert("You have successfully logged in.");

                setAuthenticated(true);
                const userData = users[i];
                localStorage.setItem("user", JSON.stringify(userData));
                localStorage.setItem("authenticated", true);
                navigate("/chat");
                return;
            }
        }

        alert("The email or password are incorrect.");
        setAuthenticated(false);
        localStorage.setItem("user", null);
        localStorage.setItem("authenticated", false);
        
    }

    return (
        <form ref={formRef} className="m-4" id="loginForm" onSubmit={handleLoginSubmit}>
            <FormInput type="email" id="loginEmail" placeholder="enter your email" className="form-control" label="Email" required={true} />
            <FormInput type="password" id="loginPassword" placeholder="enter your password" className="form-control" label="Password" required={true} />
            <div className="form-group form-check">
                <div className="row">
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
                </div>
            </div>
            <div className="col-md-3" id="">
                <button type="submit" className="btn btn-primary">
                    Sign in
                </button>
            </div>
        </form>
    );
}

export default LoginForm;