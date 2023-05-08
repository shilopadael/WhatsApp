import './App.css';
import LoginPage from './components/loginPage/LoginPage';
import RegisterPage from './components/registerPage/RegisterPage';
import { BrowserRouter, Routes, Route } from "react-router-dom";

const userLst = new Array();

const navigate(url) => {

}

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<LoginPage />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
