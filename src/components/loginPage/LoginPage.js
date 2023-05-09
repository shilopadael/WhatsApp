import "../style.css";
import ChatSystemLogo from "../ChatSystemLogo/ChatSystemLogo";
import HeaderText from "../HeaderText/HeaderText";
import LoginForm from "../Form/LoginForm";
import LoginImg from "./loginImg/LoginImg";
import { Link } from "react-router-dom";


function LoginPage(props) {

    const { users, setUsers } = props;

    return (
        <div className="topScreen">
            <div className="container-lg">
                <ChatSystemLogo />
                <div className="row">
                    {/* big white container in the middle of the screen */}
                    <div className="col-xl-10  mx-auto bg-white rounded shadow">
                        <div className="row">
                            <div className="col-md-6">
                                {/* the login form header */}
                                <HeaderText content="Welcome To Chat System" />
                                {/* login form */}
                                <LoginForm users={users}/>
                                <div>
                                    <p className="text-center">
                                        Don't have an account?{" "}
                                        <Link to="/register" className="text-decoration-none">
                                            Sign up
                                        </Link>
                                    </p>
                                </div>
                            </div>
                            {/* the login image, fother improvemnt: changing image every few seconds with fade effect. */}
                            <LoginImg></LoginImg>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;