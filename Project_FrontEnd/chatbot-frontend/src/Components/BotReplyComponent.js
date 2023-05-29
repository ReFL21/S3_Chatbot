import React, { Component } from 'react';
import styles from './BotReplyComponent.css';

const regex = /\/[A-Za-z0-9\.]+\/[a-zA-Z0-9]+\//ig;
const regexWord = /\.[A-Za-z0-9]{7,}\.[A-Za-z0-9\.]{5,}/ig;

function ReStructureMessage(message) {
    let newMsg = message;
    var result;

    while (result = regex.exec(newMsg)) {
        newMsg = newMsg.replace(result, result + "\n");
    }

    while (result = regexWord.exec(newMsg)) {
        newMsg = newMsg.replace(result, "\n" + result + "\n");
    }

    console.log("New msg: " + newMsg)

    return newMsg;
}


const BotReplyComponent = ({ message }) => {

    return (
        <div className="bot-message">
            {ReStructureMessage(message)}
        </div>
    );
}

export default BotReplyComponent;