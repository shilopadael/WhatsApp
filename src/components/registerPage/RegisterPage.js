import ChatSystemLogo from "../ChatSystemLogo/ChatSystemLogo";
import HeaderText from "../HeaderText/HeaderText";
import RegisterForm from "../Form/RegisterForm";
import "../style.css";

function RegisterPage(props) {
    const { userInfo } = props;
    return(
        <div className="topScreen">
            <section className="mt-0 flex-column min-vh-100 justify-content-center align-item-center">
                <div className="container-md">
                    <ChatSystemLogo />
                    <div className="row">
                        {/* white box in the middle of the screen for the registration*/}
                        <div className="col-xl-8 p-4 mx-auto bg-white rounded shadow">
                            {/* registration form header */}
                            <HeaderText content="Register To Chat System" />
                            {/* registration form */}
                            <RegisterForm users={userInfo} />
                        </div>
                    </div>
                </div>
            </section>
        </div>

    );
}

export default RegisterPage;