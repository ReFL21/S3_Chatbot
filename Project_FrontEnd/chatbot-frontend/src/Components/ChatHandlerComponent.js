import BotReplyComponent from "./BotReplyComponent";
import UserReplyComponent from "./UserReplyComponent";
import { useEffect } from "react";

const reg = /[a-zA-Z]+:\/\/[A-Za-z0-9\.]+.[a-zA-Z\-]+\/[A-Za-z0-9\/.-]+/ig;

const ChatHandlerComponent = (props) => {
    //console.log(props.messages);
    useEffect(() => {
        console.log(props.messages);
    });
    const synth = window.speechSynthesis;
    const voices = synth.getVoices();

    function restrucureMessage(msg) {
        let newMsg = "";
        newMsg = msg.replaceAll(reg, "hyperlink");
        return newMsg;
    }



    function speak(message) {
        var structuredMsg = restrucureMessage(message);
        var toSpeak = new SpeechSynthesisUtterance(structuredMsg);

        if (document.getElementById("voiceVolume").children.item(2).children.item(0).value != null) {
            var selectedVolume = document.getElementById("voiceVolume").children.item(2).children.item(0).value;
            toSpeak.volume = selectedVolume / 100;
        }

        if (document.getElementById("voiceList").selectedOptions[0] != null) {
            var selectedVoiceName = document.getElementById("voiceList").selectedOptions[0].getAttribute('data-name');
            voices.forEach((voice) => {
                if (voice.name === selectedVoiceName) {
                    toSpeak.voice = voice;
                }
            });
        }
        synth.speak(toSpeak);
    };

    return (
        <div >
            {console.log(props)}
            {props.messages.map((message, index) => {
                if (props.messages.indexOf(message) % 2 === 0) {
                    if (index === props.messages.length - 2)
                        speak(message);
                    return <UserReplyComponent message={message} />;
                }

                if (index === props.messages.length - 1) {
                    //for the last message send by the bot
                    speak(message);
                }

                //For every other message by the bot
                return <BotReplyComponent message={message} />;

            }
            )}
        </div>
    );
}
export default ChatHandlerComponent;