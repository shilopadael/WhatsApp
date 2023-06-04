
import "./FormInput.css";
import { useState } from "react";


function FormInput(props) {
    const [focused, setFocused] = useState(false);

    const handleFocus = () => {
        setFocused(true);
    }

    const { label, errormessage } = props;
    return (
        <div className="form-group input-effect register">
            <label htmlFor={props.id} className="font-semibold capitalize">
                {label}
            </label>
            <input {...props}
                onBlur={handleFocus}
                focused={focused.toString()}
                onFocus={() => { props.id === "confirmPassword" && setFocused(true) }}
            />
            <span className="errorMessage">{errormessage}</span>
        </div>
    );
}

export default FormInput;