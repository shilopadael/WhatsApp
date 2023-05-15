import defaultImg from "../../assets/registerImg/profile.png";
import validate from "./FormValidation";
import FormInput from "./FormInput";
import UserImg from "./UserImg";
import { useState } from "react";
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';



function RegisterForm(props) {

    const navigate = useNavigate();

    const { users } = props;

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirm] = useState("");
    const [displayName, setDisplayName] = useState("");
    const [img, setImg] = useState(defaultImg);

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }
    const handleConfirmPasswordChange = (e) => {
        setConfirm(e.target.value);
    }
    const handleDisplayNameChange = (e) => {
        setDisplayName(e.target.value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();

        // validation
        const val = validate(email, password, confirmPassword, displayName);
        if (!val) {
            return;
        } else {
            // checking if the email already exist
            for (let i = 0; i < users.length; i++) {
                if (users[i].email === email) {
                    alert("This email already exist.");
                    return;
                }
            }
            // if got here adding the new username:

            let data = {
                "email": email, 
                "password": password,
                "displayName": displayName,
                "img": img
            };

            // print the data:
            users.push(data);
            alert("You have successfully registered. retuning to the login page.");
            navigate("/");
            return;
        }
    }

    return (
        <form action="#" className="form-horizontal needs-validation" onSubmit={handleSubmit}>
            {/* forther improvment: adding validation to the field, including password valid (both the same, 8-20 char long) */}
            <div className="row">
                <div className="col-md">
                    <FormInput
                        type="text"
                        className="form-control"
                        id="registerEmail"
                        placeholder="enter your username"
                        label="Email"
                        onChange={handleEmailChange}
                        errormessage="Please enter a valid email address."
                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                        required={true}
                    />
                    <div className="form-group input-effect">
                        <FormInput
                            type="password"
                            className="form-control"
                            id="registerPassword"
                            placeholder="enter your password"
                            aria-describedby="passwordNotice"
                            label="Password"
                            onChange={handlePasswordChange}
                            errormessage="Password must be between 8 and 20 characters long."
                            pattern="^.{8,20}$"
                            required={true}
                        />
                        <FormInput
                            type="password"
                            className="form-control"
                            id="confirmPassword"
                            placeholder="enter your password"
                            aria-describedby="passwordNotice"
                            label="Confirm Password"
                            onChange={handleConfirmPasswordChange}
                            errormessage="Make sure your passwords match."
                            pattern={password}
                            required={true}
                        />
                    </div>
                    <div className="form-group input-effect">
                        <FormInput
                            type="text"
                            className="form-control"
                            id="displayName"
                            placeholder="enter your nickname"
                            required={true}
                            label="Display name"
                            onChange={handleDisplayNameChange}
                            errormessage="You must have a nickname."
                        />
                    </div>
                </div>
                <div className="col-md">
                    <UserImg setImg={setImg} img={img} />
                </div>
            </div>
            <div className="col-md-6">
                <div>
                    <p className="text-center">
                        Already have an account?{" "}
                        <Link to="/" className="text-decoration-none">
                            Sign in
                        </Link>
                    </p>
                </div>
                <button
                    type="submit"
                    className="btn btn-primary mt-4 btn-lg center btnLogInSignUp">
                    Sign up
                </button>
            </div>
        </form>
    );
}

export default RegisterForm;