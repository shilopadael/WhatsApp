import Receiver from "./Receiver";
import Sender from "./sender";


function BoxMessage(props) {
    const { userMessages } = props;

    return (
        <>
            <div class="text-chat BoxMessage">
                {userMessages.map((mail, index) => {
                    if (mail.sender === "me") {
                        return <Sender key={index} message={mail.message} />;
                    } 
                    if (mail.sender === "them") {
                        return <Receiver key={index} message={mail.message} />;
                    } else {
                        return;
                    }
                })}

            </div>
        </>
    );
}
export default BoxMessage;
