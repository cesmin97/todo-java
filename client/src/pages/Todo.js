import React, { useEffect, useState } from "react";
import { getTodos } from "../utils/api";
import TodoItem from "../components/TodoItem";

const Todo = () => {
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchTodos = async () => {
    try {
      const response = await getTodos();
      console.log(response);
      setTodos(response.data);
    } catch (error) {
      console.error("Failed to fetch todos:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Todo List</h1>
      {todos.length > 0 ? (
        todos.map((todo) => <TodoItem key={todo.todoId} todo={todo} />)
      ) : (
        <p>No todos available.</p>
      )}
    </div>
  );
};

export default Todo;
