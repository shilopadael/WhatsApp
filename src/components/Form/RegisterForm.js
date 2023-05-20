import defaultImg from "../../assets/registerImg/profile.png";
import auth from "../../services/auth-service";
import validate from "./FormValidation";
import FormInput from "./FormInput";
import UserImg from "./UserImg";
import "./RegisterForm.css"
import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';



function RegisterForm(props) {

    const navigate = useNavigate();

    const { users } = props;

    const [username, setUsername] = useState("");
    const [userTaken, setUserTaken] = useState("");
    const [userErrorMessage, setUserErrorMessage] = useState("");
    const [userPattern, setUserPattern] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirm] = useState("");
    const [displayName, setDisplayName] = useState("");
    const [img, setImg] = useState(defaultImg);

    useEffect(() => {
    }, [userTaken, userPattern]);

    const handleUserNameChange = (e) => {
        const newUsername = e.target.value;
        let errorMessage = ""; // Initialize the error message
        let isUsernameTaken = false; // Flag to track if username is taken

        // Checking if the username already exists

        for (let i = 0; i < users.length; i++) {
            if (users[i].username === newUsername) {
                setUserTaken(newUsername);
                setUserPattern(`^(?!.*\\b(${userTaken}))\\w{3,12}$`);
                isUsernameTaken = true;
                break;
            }
        }

        if (isUsernameTaken) {
            errorMessage = `Username "${newUsername}" already exists`;
        } else if (newUsername.length < 3 || newUsername.length > 12) {
            setUserTaken("");
            setUserPattern(`^[a-zA-Z0-9]{3,12}$`);
            errorMessage = "Username must be between 3 and 12 characters long";
        } else {
            setUserTaken("");
            setUserPattern(`^[a-zA-Z0-9]{3,12}$`);
            setUsername(newUsername);
        }
        setUserErrorMessage(errorMessage);

    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    }
    const handleConfirmPasswordChange = (e) => {
        setConfirm(e.target.value);
    }
    const handleDisplayNameChange = (e) => {
        setDisplayName(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        // validation
        const val = validate(username, password, confirmPassword, displayName);
        let flag = false;
        if (!val) {
            // validation failed
            alert("Failed to register please try again. (all fields are required)");
            return;
        } else {
            // trying to add the new user to the server
            flag = await auth.register(username, password, displayName, img);
            if(flag) {
                // registered successfully
                alert("You have successfully registered. retuning to the login page.");
                navigate("/");
            } else {
                // failed to register
                alert("Failed to register please try again. (all fields are required)");
            }
        }
    }

    return (
        <form className="form-horizontal needs-validation" onSubmit={handleSubmit}>
            {/* forther improvment: adding validation to the field, including password valid (both the same, 8-20 char long) */}
            <div className="row">
                <div className="col-md">
                    <FormInput
                        type="text"
                        className="form-control"
                        id="registerUsername"
                        placeholder="enter your username"
                        label="Username"
                        onChange={handleUserNameChange}
                        errormessage={userErrorMessage}
                        pattern={userPattern}
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

