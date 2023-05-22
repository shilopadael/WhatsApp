import React, { useEffect, useRef } from "react";
import Receiver from "./Receiver";
import Sender from "./Sender";


function BoxMessage(props) {
    const { user } = props;
    const [ userMessages, setUserMessages ] = useState([]); 
    const boxMessageRef = useRef(null);
  
    useEffect(() => {
      // Scroll to the bottom when userMessages change
      scrollToBottom();
    }, [userMessages]);
  
    function scrollToBottom() {
      if (boxMessageRef.current) {
        const boxMessageContainer = boxMessageRef.current;
        boxMessageContainer.scrollTop = boxMessageContainer.scrollHeight;
      }
    }

    return (
        <div className="text-chat BoxMessage right-chat" ref={boxMessageRef}>
            {[...userMessages].reverse().map((mail, index) => {
                if (mail.sender.username === user.username) {
                    return <Sender key={index} message={mail.content} time={mail.created}/>;
                }
                else {
                    return <Receiver key={index} message={mail.content} time={mail.created}/>;
                }
            })}
        </div>
    );
}
export default BoxMessage;
