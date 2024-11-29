import React from 'react';
import '../styles/Header.css';
import logo from '../images/Acmeplex_1.gif';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  return (
    <header className='header'>
      <a href="/" className="logo-link">
        <img src={logo} className="logoimage" alt="AcmePlex logo" />
      </a>
      <div className= "title-container">
          <h1>AcmePlex</h1>
      </div>
    </header>
  );
};

export default Header;
