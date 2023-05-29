import React from 'react';

const UserModal = ({ isOpen, children }) => {
  return (
    isOpen && (
      <div className="modal-overlay">
        <div className="modal-content">
          {children}
        </div>
      </div>
    )
  );
};

export default UserModal;
