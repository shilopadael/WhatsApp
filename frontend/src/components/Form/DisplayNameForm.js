import Input from "./FormInput";

function DisplayNameForm(info) {
    return (
        <div className="form-group input-effect">
            <Input
                type="text"
                className="form-control"
                id="displayName"
                placeholder="enter your nickname"
                required=""
                label="Display name"
            />
        </div>
    );
}

export default DisplayNameForm;