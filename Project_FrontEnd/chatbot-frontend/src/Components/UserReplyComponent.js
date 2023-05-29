import React, { Component, useEffect } from 'react';

const UserReplyComponent = ({ message }) => {
    useEffect(() => {
        console.log(message);
    });
    return (
        <div className="user-message">
            {message}
        </div>
    );
}

export default UserReplyComponent;