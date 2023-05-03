

import { useFormContext } from "react-hook-form";
import { AnimatePresence, motion } from "framer-motion";
import { MdError } from "react-icons/md";

const InputError = () => {
    return <div>error</div>
}

const framer_error = {
    initial: { opacity: 0, y: 10 },
    animate: { opacity: 1, y: 0 },
    exit: { opacity: 0, y: 10 },
    transition: { duration: 0.2 }
};

function Input(info) {
    return (
        <div className="form-group input-effect">
                <label htmlFor={info.id} className="font-semibold capitalize">
                    {info.label}
                </label>
            <input {...info}/>
        </div>
    );
}

export default Input;