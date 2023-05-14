import Input from "./FormInput";

function UserImg(props) {
    const { img ,setImg } = props;

    function changeImg(event) {

        const file = event.target.files[0];
        const reader = new FileReader();
        // checking file type
        if (!file.type.match('image.*')) { 
            alert("Please select image only.");
            return;
        }
        reader.onload = function () {
          const dataURL = reader.result;
          console.log("img url: " + dataURL);
          setImg(dataURL);
        }
        reader.readAsDataURL(file);
      }
    

    return (
        <>
            <Input
                type="file"
                className="form-control"
                id="profilePic"
                accept="image/*"
                label="Profile Picture"
                onChange={changeImg}
            />
            <div id="profileImg" className="circular--square center">
                {/* user image need to be change from the upload files that selected this image is the default image*/}
                <img
                    src={img}
                    id="profileImg"
                    alt="profile image"
                />
            </div>
        </>
    );
}


export default UserImg;