import React, { Component } from 'react';
import style from "./CategoryComponent.css";
import UserReplyComponent from './UserReplyComponent';
import { useState, useEffect } from "react";
import { getCategories, getNodeById, updateRating } from '../Services/CategoryService';
import { getCategoryById } from '../Services/CategoryService';
import ChatHandlerComponent from './ChatHandlerComponent';
import ChatBotButton from './ChatBotButton';
import { getNodeByTopic } from '../Services/CategoryService';
import UndoIcon from '@mui/icons-material/Undo';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import UserModal from './UserModal';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';

let messages = [];
let first = true;
const CategoryComponent = ({ getMessages, useLang, question }) => {
    let newMessages = [];

    const [categories, setCategories] = useState([]);
    const [category, setCategory] = useState();
    const [currentCategory, setCurrentCategory] = useState();
    const [node, setNode] = useState();
    const [page, setPage] = useState(window.location.href);

    messages.forEach(message => {
        newMessages.push(message);
    });

    const getAllData = () => {
        getCategories().then((response) => {
            const x = response.data.nodes.splice(3, 1);
            console.log(x);

            setCategories(response.data.nodes);
            setCategory(response.data.nodes[0]);
        }).catch((error) => {
            console.log(error);
        });
    };

    const setCurrent = () => {
        if (categories.length == 0) {
            setCategory(categories[0])
        }
    };
    useEffect(() => {
        //console.log(question);
        const pageArray = page.split(/[/.-]+/)
        pageArray.splice(0, 2);
        //console.log(pageArray);
        getNodeByTopic(pageArray).then((response) => {
            //console.log(response.data);
            if (response.data.nodes.length > 10) {
                getAllData();
            }
            else {
                getCategoryById(response.data.nodes[0].id).then((response) => {
                    //console.log(response.data);
                    setCategories(response.data.nodes);
                    setCategory(response.data.nodes[0]);
                }).catch((error) => {
                    console.log(error);
                });
            }
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        // console.log(question)
        const messageArray = question.split(/[/., -?]+/)
        if (isModalOpen) {
            setIsModalOpen(!isModalOpen);
        }
        getNodeByTopic(messageArray).then((response) => {
            //console.log(response.data.nodes);
            if (response.data.nodes.length == 0 || response.data.nodes.length > 10) {
                getAllData();
                if (!first) {
                    messages.push(question);
                    newMessages.push(question);
                    if (useLang.includes("nl",0)) {
                        messages.push("Ik begrijp het niet, probeer opnieuw of gebruik de opties.");
                        newMessages.push("Ik begrijp het niet, probeer opnieuw of gebruik de opties.");
                    }
                    else {
                        messages.push("I do no understand, please try again or use the options.");
                        newMessages.push("I do no understand, please try again or use the options.");
                    }
                    getMessages(newMessages);
                }
                first = false;
            }
            else {
                getCategoryById(response.data.nodes[0].id).then((response) => {
                    //console.log(response.data.nodes);
                    setCategories(response.data.nodes);
                    if (response.data.nodes.length == 0) {
                        setIsModalOpen(!isModalOpen);
                    }

                }).catch((error) => {
                    console.log(error);
                });
                setCategory(response.data.nodes[0]);
                messages.push(question);
                newMessages.push(question);
                if (useLang.includes("nl",0)) {
                    messages.push(response.data.nodes[0].antwoord);
                    newMessages.push(response.data.nodes[0].antwoord);
                }
                else {
                    messages.push(response.data.nodes[0].answer);
                    newMessages.push(response.data.nodes[0].answer);
                }
                getMessages(newMessages);
            }


        });
    }, [question]);

    const eventClick = (id, category) => {
        updateRating(id, null);
        setCurrentCategory(category);
        getCategoryById(id).then((response) => {
            console.log(response.data);
            setCategories(response.data.nodes);
            if (response.data.nodes.length != 0) {
                setCategory(response.data.nodes[0]);
            }
            else {
                setIsModalOpen(!isModalOpen);
            }
        }).catch((error) => {
            console.log(error);
        });
        if (useLang.includes("nl",0)) {
            console.log("succesfull nl");
            console.log(category.vraag);
            messages.push(category.vraag);
            messages.push(category.antwoord);
            newMessages.push(category.vraag);
            newMessages.push(category.antwoord);
        }
        else {
            console.log("unsuccesfull nl");
            console.log(category.nodeText);
            messages.push(category.nodeText);
            messages.push(category.answer);
            newMessages.push(category.nodeText);
            newMessages.push(category.answer);
        }
        getMessages(newMessages);
        console.log(messages);

    };

    //function to get the right language for now
    function getRightLang(category) {
        if (useLang.includes("nl",0)) {
            return category.vraag;
        }
        else return category.nodeText;
    }

    const backClick = (id) => {
        console.log(id);
        if (categories.length == 0) {
            id = category.id;
            if (isModalOpen) {
                setIsModalOpen(!isModalOpen);
            }
        }
        getNodeById(id).then((response) => {
            setNode(response.data);
            //console.log(response.data);
            if (response.data.parentId == null) {
                getAllData();
            }
            else {
                getCategoryById(response.data.parentId).then((response) => {
                    //console.log(response.data);
                    setCategories(response.data.nodes);
                    setCategory(response.data.nodes[0]);
                }).catch((error) => {
                    console.log(error);
                });
            }
        }).catch((error) => {
            console.log(error);
        });
        console.log(node);

    };
    const [showEditSubTopic, setShowEditSubTopic] = useState(false);
    const handleCloseEditSubTopic = () => setShowEditSubTopic(false);
    const handleShowEditSubTopic = () => {
        console.log("GHello");
        setShowEditSubTopic(true);
    }
    const [isModalOpen, setIsModalOpen] = useState(false);

    const thumbsUp = () => {
        setIsModalOpen(!isModalOpen);
        updateRating(currentCategory.id, true);
    };
    const thumbsDown = () => {
        setIsModalOpen(!isModalOpen);
        updateRating(currentCategory.id, false);
    };
    const updateValidation = () => {
        updateValidation()
    }

    return (
        <div className='option-box'>

            <UserModal isOpen={isModalOpen}>
                <div className="modal-user">
                    {useLang.includes("nl",0) ? <h7>Geholpen?</h7>
                        : <h7>Helpful?</h7>
                    }
                    <div className='modal-buttons'>
                        <button className="positive" onClick={thumbsUp}><ThumbUpAltIcon /></button>
                        <button className="negative" onClick={thumbsDown}><ThumbDownAltIcon /></button>
                    </div>
                </div>
            </UserModal>
            {console.log(categories)}
            {categories.map((category) => <button type="submit" onClick={() => { eventClick(category.id, category) }} id={category.nodeText} className='option-message'>
                {getRightLang(category)}</button>
            )}
            <button type="submit" onClick={() => { backClick(category.parentId) }} className='back-button'><UndoIcon /></button>

            {/* <button className="modal-button" onClick={handleModalToggle}><h7>End</h7></button> */}

        </div >
    );
}

export default CategoryComponent;