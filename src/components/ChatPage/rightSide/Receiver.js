


function Receiver({ message , time}) {
    return (
        <div className="chat-bubble-left">
            {message}
            <div className="chat-bubble-left-time">{time}</div>
        </div>
    )
}
export default Receiver;