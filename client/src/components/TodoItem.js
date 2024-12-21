import React from "react";
import "./TodoItem.css";

const TodoItem = ({ todo }) => {
  return (
    <div className="todo-item">
      <h3>{todo.title}</h3>
      <p>{todo.contents}</p>
    </div>
  );
};

export default TodoItem;
