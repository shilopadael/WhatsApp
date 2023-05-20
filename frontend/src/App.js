import './App.css';
import './components/ChatSystemLogo/ChatSystemLogo.css';
import LoginPage from './components/LoginPage/LoginPage';
import RegisterPage from './components/RegisterPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Chat from './components/ChatPage/Chat';
import { useState } from 'react';

function App() {

  const [authenticated, setAuthenticated] = useState(localStorage.getItem("authenticated") === "true");
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage setAuthenticated={setAuthenticated} />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/chat" element={<Chat authenticated={authenticated} setAuthenticated={setAuthenticated}/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
