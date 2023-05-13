


function Sender({ message, time }) {
    return (
        <div className="chat-bubble-right">
            {message}
            <div className="chat-bubble-right-time">{time}</div>
        </div>

    )
}
export default Sender;