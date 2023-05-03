import DisplayNameForm from "./DisplayNameForm";
import Input from "./Input";
import PasswordForm from "./PasswordForm";
import UserImg from "./UserImg";

function RegisterForm() {
    return (
        <form action="#" className="form-horizontal needs-validation">
            {/* forther improvment: adding validation to the field, including password valid (both the same, 8-20 char long) */}
            <div className="row">
                <div className="col-md">
                        <Input
                            type="text"
                            className="form-control"
                            id="registerEmail"
                            placeholder="enter your username"
                            label="Email"
                            required=""
                        />
                    <PasswordForm />
                    <DisplayNameForm />
                </div>
                <div className="col-md">
                    <UserImg />
                </div>
            </div>
            <div className="col-md-6">
                <div>
                    <p className="text-center">
                        Already have an account?{" "}
                        <a href="/login.html" className="text-decoration-none">
                            Sign in
                        </a>
                    </p>
                </div>
                <button
                    type="submit"
                    className="btn btn-primary mt-4 btn-lg center">
                    Sign up
                </button>
            </div>
        </form>
    );
}

export default RegisterForm;