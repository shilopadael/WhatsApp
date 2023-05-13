
import { useNavigate } from "react-router-dom";


function LogOut(props) {
    const navigate = useNavigate();
    const { setAuthenticated } = props;

    const handleLogOut = () =>{
        setAuthenticated(false);
        localStorage.setItem("authenticated", false);
        navigate('/', () => {
            console.log("Loging Out...")
        })
    }

    return (
        <div>
            <button className="btn btn-danger logOutbtn" onClick={handleLogOut}>
                LogOut
            </button>
        </div>
    )

}

export default LogOut;