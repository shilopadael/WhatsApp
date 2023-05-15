import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import img from "./assets/registerImg/profile.png"

const usersInformation = [{
  "email": "omerbar@gmail.com", 
  "password": "123",
  "displayName": "Omer",
  "img": img
}];
localStorage.setItem("authenticated", false);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App usersInformation={usersInformation}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
