import logo from './logo.svg';
import './App.css';
import ChatBotButton from './Components/ChatBotButton';
import AdminPage from './Pages/AdminPage/AdminPage';
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import UserPage from './Pages/UserPage/UserPage';
function App() {
  return (
    <div>
      <Router>
        <nav className="navbar navbar-custom">
          <a className="navbar-brand" href="/">Fontys IT Chatbot</a>
          <div>
            <a className="navbar-brand" href="/admin">Admin Panel</a>
            <a className="navbar-brand" href="/">User Page</a>
          </div>

        </nav>

        <Routes>
          <Route path="*" element={<UserPage />} />
          <Route path="/admin" element={<AdminPage />} />
        </Routes>
      </Router>

    </div>
  );
}

export default App;
