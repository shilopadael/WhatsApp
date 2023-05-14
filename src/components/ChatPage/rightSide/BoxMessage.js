import Receiver from "./Receiver";
import Sender from "./Sender";


function BoxMessage(props) {
    const { userMessages } = props;

    return (
        <div className="text-chat BoxMessage right-chat">
            {[...userMessages].reverse().map((mail, index) => {
                if (mail.sender === "me") {
                    return <Sender key={index} message={mail.message} time={mail.time}/>;
                }
                else if (mail.sender === "them") {
                    return <Receiver key={index} message={mail.message} time={mail.time}/>;
                }
            })}
        </div>
    );
}
export default BoxMessage;
