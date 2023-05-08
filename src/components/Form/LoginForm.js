
import FormInput from "./FormInput";
import { useRef } from "react";

function LoginForm(props) {

    const { userLst } = props;

    const formRef = useRef(null);

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        // getting the email and password from the form
        let email = formRef.current[0].value;
        let password = formRef.current[1].value;

        //checking if the email and password are correct in the userlst
        for (let i = 0; i < userLst.length; i++) {
            if (userLst[i].email === email && userLst[i].password === password) {
                alert("You have successfully logged in.");
                // login success, redirecting to the chat page with the user information
                
                return;
            }
        }
        alert("The email or password are incorrect.");
        return;
    }

    return (
        <form ref={formRef} className="m-4" id="loginForm" onSubmit={handleLoginSubmit}>
            <FormInput type="email" id="loginEmail" placeholder="enter your email" className="form-control" label="Email" required={true}/>
            <FormInput type="password" id="loginPassword" placeholder="enter your password" className="form-control" label="Password" required={true}/>
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