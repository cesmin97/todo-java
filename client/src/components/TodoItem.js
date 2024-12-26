import React, { useState } from "react";
import "./TodoItem.css";

const TodoItem = ({ todo, onUpdate, onDelete }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedTitle, setEditedTitle] = useState(todo.title);
  const [editedContents, setEditedContents] = useState(todo.contents);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancel = () => {
    setIsEditing(false);
    setEditedTitle(todo.title);
    setEditedContents(todo.contents);
  };

  const handleConfirm = () => {
    onUpdate(todo.id, { title: editedTitle, contents: editedContents });
    setIsEditing(false);
  };

  const handleDelete = () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      onDelete(todo.id);
    }
  };

  return (
    <div className="todo-item">
      {isEditing ? (
        <>
          <input
            type="text"
            value={editedTitle}
            onChange={(e) => setEditedTitle(e.target.value)}
            className="edit-input"
          />
          <textarea
            value={editedContents}
            onChange={(e) => setEditedContents(e.target.value)}
            className="edit-textarea"
          />
          <div className="action-buttons">
            <button className="confirm-button" onClick={handleConfirm}>
              Confirm
            </button>
            <button className="cancel-button" onClick={handleCancel}>
              Cancel
            </button>
          </div>
        </>
      ) : (
        <>
          <h3>{todo.title}</h3>
          <p>{todo.contents}</p>
          <div className="action-buttons">
            <button className="edit-button" onClick={handleEdit}>
              Edit
            </button>
            <button className="delete-button" onClick={handleDelete}>
              Delete
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default TodoItem;
