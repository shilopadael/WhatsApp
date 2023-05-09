import './App.css';
import LoginPage from './components/loginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";




function App() {

  const [users, setUsers] = useState([]);
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage users={users} setUsers={setUsers} />} />
        <Route path="/register" element={<RegisterPage users={users} setUsers={setUsers} />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
