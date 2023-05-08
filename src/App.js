import './App.css';
import LoginPage from './components/loginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";

const userLst = new Array();


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage userLst={userLst}/>} />
        <Route path="/register" element={<RegisterPage userLst={userLst}/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
