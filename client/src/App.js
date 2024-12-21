import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Todo from "./pages/Todo.js"; // Todo 페이지
import Home from "./pages/Home.js"; // Home 페이지
import NotFound from "./pages/NotFound.js"; // NotFound 페이지

const App = () => {
  return (
    <Router>
      <div className="container"> {/* 최상단 div에 클래스 적용 */}
        <Routes>
          <Route path="/" element={<Home />} /> {/* 홈 페이지 */}
          <Route path="/todo" element={<Todo />} /> {/* 투두 페이지 */}
          <Route path="*" element={<NotFound />} /> {/* 404 페이지 */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
