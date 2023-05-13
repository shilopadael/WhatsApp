import Receiver from "./Receiver";
import Sender from "./sender";


function BoxMessage(props) {
    const { userMessages } = props;

    return (
        <div className="text-chat BoxMessage right-chat">
            {userMessages.map((mail, index) => {
                if (mail.sender === "me") {
                    return <Sender key={index} message={mail.message} />;
                }
                else if (mail.sender === "them") {
                    return <Receiver key={index} message={mail.message} />;
                }
            })}

        </div>
    );
}
export default BoxMessage;
