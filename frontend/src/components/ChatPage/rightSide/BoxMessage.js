import React, { useEffect, useRef } from "react";
import Receiver from "./Receiver";
import Sender from "./Sender";


function BoxMessage(props) {
    const { userMessages } = props;
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
