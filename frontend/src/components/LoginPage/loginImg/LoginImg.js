// importing the img files
import img1 from "../../../assets/loginImgs/loginimg1.svg";
import img2 from "../../../assets/loginImgs/loginimg2.svg";
import img3 from "../../../assets/loginImgs/loginimg3.svg";
import { useState, useEffect } from "react";

function LoginImg() {

    const [img, setImg] = useState(img1);
    const [fade, setFade] = useState(false);

    // function to change the img
    function changeImg() {
        setFade(true)
        setTimeout(() => {
            if (img === img1) {
                setImg(img2);
            } else if (img === img2) {
                setImg(img3);
            } else {
                setImg(img1);
            }
            setFade(false);
        }, 250);
    }

    // calling the function after 3 seconds
    useEffect(() => {
        const intervalId = setInterval(changeImg, 6000);
        return () => clearInterval(intervalId);
    }, [img]);


    return (
        <div className="col-md-6 p-4" id="loginImg">
            <div className={`img-container ${fade ? "fade" : ""}`}>
                <img
                    src={img}
                    alt="login"
                    className="img-fluid"
                />
            </div>
        </div>
    );
}

export default LoginImg;