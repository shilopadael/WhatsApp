
import './components/ChatSystemLogo/ChatSystemLogo.css';
import LoginPage from './components/LoginPage/LoginPage';
import RegisterPage from './components/RegisterPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Chat from './components/ChatPage/Chat';
import { useState , useEffect} from 'react';
import io from 'socket.io-client';

function App() {
  const [authenticated, setAuthenticated] = useState(localStorage.getItem("authenticated") === "true");
  
  // useEffect(() => {
  //   const socket = io("http://localhost:5000");
  //   console.log(socket);
  // }, [] );
  
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
