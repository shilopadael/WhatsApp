import Input from "./Input";

function DisplayNameForm() {
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
            <div className="invalid-feedback">
                You must have a nickname.
            </div>
        </div>
    );
}

export default DisplayNameForm;