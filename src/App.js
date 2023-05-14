import './App.css';
import LoginPage from './components/LoginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Chat from './components/Chat';
import { useState } from 'react';

function App(props) {

  const { usersInformation } = props;
  const [authenticated, setAuthenticated] = useState(localStorage.getItem("authenticated") === "true");
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage userInfo={usersInformation} setAuthenticated={setAuthenticated} />} />
        <Route path="/register" element={<RegisterPage userInfo={usersInformation} />} />
        <Route path="/chat" element={<Chat authenticated={authenticated} setAuthenticated={setAuthenticated}/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
