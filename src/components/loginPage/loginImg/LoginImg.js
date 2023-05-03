// importing the img files
import img1 from "../../../assets/loginImg/loginimg1.svg";
import img2 from "../../../assets/loginImg/loginimg2.svg";
import img3 from "../../../assets/loginImg/loginimg3.svg";
import { useState , useEffect} from "react";

function LoginImg() {

    // const [img, setImg] = useState(img1);

    // // function to change the img
    // function changeImg() {
    //     if (img === img1) {
    //         setImg(img2);
    //     } else if (img === img2) {
    //         setImg(img3);
    //     } else {
    //         setImg(img1);
    //     }
    // }

    // // calling the function after 3 seconds
    // useEffect(() => {
    //     const intervalId = setInterval(changeImg, 5000);
    //     return () => clearInterval(intervalId);
    // }, []);

    // // calling the function
    // changeImg();

    return (
        <div className="col-md-6 p-4" id="loginImg">
            <div>
                <img
                    src={img1}
                    alt="login"
                    className="img-fluid"
                />
            </div>
        </div>
    );
}

export default LoginImg;