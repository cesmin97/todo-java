// src/utils/api.js
import axios from "axios";

const api = axios.create({
  baseURL: process.env.REACT_APP_BASE_URL,
});

export const getTodos = async () => {
  console.log(process.env.REACT_APP_BASE_URL);
  const response = await api.get("/todo/list");
  return response.data;
};

export const createTodo = async (todo) => {
  const response = await api.post("/todos", todo);
  return response.data;
};
