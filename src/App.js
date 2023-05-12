import './App.css';

import LoginPage from './components/loginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Chat from './components/Chat';
import imageOmer from './assests/images/Omer.png';
import { useState } from 'react';

function App() {

  const [authenticated, setAuthenticated] = useState(localStorage.getItem("authenticated") === "true");

  let data = {
    email: "omerbar@gmail.com",
    password: "123",
    displayName: "test",
    image: imageOmer
  };

  const usersInformation = [];
  usersInformation.push(data);
  localStorage.setItem(data.email, JSON.stringify(data));

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage userInfo={usersInformation} setAuthenticated={setAuthenticated} />} />
        <Route path="/register" element={<RegisterPage userInfo={usersInformation} />} />
        <Route path="/chat" element={<Chat authenticated={authenticated} />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
