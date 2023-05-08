
import FormInput from "./FormInput";

function LoginForm() {
    return (
        <form className="m-4" id="loginForm">
            <FormInput type="email" id="loginEmail" placeholder="enter your email" className="form-control" label="Email" />
            <FormInput type="password" id="loginPassword" placeholder="enter your password" className="form-control" label="Password" />
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