import Input from "./Input";

function PasswordForm(info) {
    return (
        <div className="form-group input-effect">
            <Input
                type="password"
                className="form-control"
                id="registerPassword"
                placeholder="enter your password"
                aria-describedby="passwordNotice"
                label="Password"
                required=""
            />
            <div id="passwordNotice" className="form-text">
                Your password must be 8-20 characters long
            </div>
            <Input
                type="password"
                className="form-control"
                id="confirmPassword"
                placeholder="enter your password"
                aria-describedby="passwordNotice"
                label="enter your password again"
                required=""
            />
            <div className="invalid-feedback">
                Make sure your passwords match.
            </div>
        </div>

    );
}


export default PasswordForm;