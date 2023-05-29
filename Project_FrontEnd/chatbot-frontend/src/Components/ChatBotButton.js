import React, { Component, useEffect, useState } from 'react';
import style from "./ChatBotButton.css";
import SendIcon from '@mui/icons-material/Send';
import BotReplyComponent from './BotReplyComponent';
import CategoryComponent from './CategoryComponent';
import QuestionComponent from './QuestionComponent';
import UserReplyComponent from './UserReplyComponent';
import ChatHandlerComponent from './ChatHandlerComponent';
import SpeakBoxComponent from './SpeakComponents/SpeakBox';
import LanguageBoxComponent from './LanguageComponent/LanguageBox';
import LanguageComponent from './LanguageComponent/LanguageComponent';
import { getNodeByTopic } from '../Services/CategoryService';
import { getNodeByKeywordAndLang } from '../Services/CategoryService';
import CloseIcon from '@mui/icons-material/Close';

const ChatBotButton = () => {

    const [nodes, setNodes] = useState([]);
    const [messages, setMessages] = useState([]);
    const [question, setQuestion] = useState("");
    const [page, setPage] = useState(window.location.href);
    const msg = document.getElementById('msg');
    const [speechVolume, setSpeechVolume] = useState(1);
    const temp = [];
    const [containerContent, setContainerContent] = useState("messageScreen");

    //Do you need it for the welcome message? yes I do
    const [welcome, setWelcome] = useState("Welcome");
    const synth = window.speechSynthesis;
    const [userLang, setLang] = useState(navigator.language || navigator.userLanguage);
    //console.log(userLang);
    const openForm = () => {
        document.getElementById("myForm").style.display = "flex";
        document.getElementById("chatbutton").style.display = "none";

        getWelcomeMsg();
        console.log(getSpeechVolume());
    };
    const closeForm = () => {
        document.getElementById("myForm").style.display = "none";
        document.getElementById("chatbutton").style.display = "block";
    };
    const msgHandler = () => {
        setQuestion(msg.value);

    };
    const getMessages = (messages) => {
        setMessages(messages);
    }

    function toSpeakTheWelcome(msg) {
        console.log(msg)
        var toSpeak = new SpeechSynthesisUtterance(msg);
        synth.speak(toSpeak);
    }

    function getSpeechVolume(Volume) {
        console.log("Im: " + Volume);
    }

    function getWelcomeMsg() {
        let response;
        getNodeByKeywordAndLang("Wel", userLang)
            .then(r => {
                if (userLang.includes("nl",0)) {
                    response = r.data.antwoord;
                    setWelcome(response);
                }
                else setWelcome(r.data.answer);
            }
            )

        toSpeakTheWelcome(welcome)
    }

    useEffect(() => {
        //console.log(messages)
        //  let welcomeMsg = getWelcomeMsg();
        // setMessages(welcomeMsg);
        closeForm();
    }, []);

    function containerSwitch(string) {
        //console.log(string);
        setContainerContent(string);
    }


    return (
        <div>
            <input className="open-button" id="chatbutton" onClick={() => openForm()} type="image" src="fontyschat.png" alt="logo" width="100" height="100" />

            <div className="chat-popup" id="myForm">
                <div className="topnav">
                    <a ><LanguageBoxComponent func={containerSwitch} /></a>
                    <a><SpeakBoxComponent getSpeechVolume={speechVolume} container={containerSwitch} /></a>
                    <close onClick={closeForm}><CloseIcon /> </close>
                </div>
                <div className="form-container">
                    <div className="content-container">
                        <div className="dialogue-container">
                            {containerContent === "messageScreen" ? <div>
                                {messages.length != 0 ? <ChatHandlerComponent messages={messages} />
                                    : <div className="bot-message">{welcome}</div>
                                }

                                {console.log(messages)}
                            </div>
                                :
                                <div className='langContent'>
                                    <LanguageComponent currentLang={userLang} languageName={"English"} langShort={"en"} setcurrent={setLang} />
                                    <LanguageComponent currentLang={userLang} languageName={"Nederlands"} langShort={"nl"} setcurrent={setLang} />
                                </div>
                            }

                        </div>
                        <CategoryComponent getMessages={getMessages} useLang={userLang} question={question} />
                        <div className="action-container" >
                            {userLang.includes("nl",0) ? <textarea placeholder="Type hier om te filteren" name="msg" id='msg' required />
                                : <textarea placeholder="Type here to use filtering" name="msg" id='msg' required />
                            }

                            <button className="btn" type="submit" onClick={msgHandler} ><SendIcon /></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ChatBotButton;