


function Sender({ message, time }) {

    // converting the time to hh:mm
    let createdDate = new Date(time);
    let hours = createdDate.getHours();
    let minutes = createdDate.getMinutes();
    time = hours + ":" + minutes;

    return (
        <div className="chat-bubble-left">
            {message}
            <div className="chat-bubble-left-time">{time}</div>
        </div>

    )
}
export default Sender;