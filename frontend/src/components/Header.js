import React from 'react';
import '../styles/Header.css';
import logo from '../images/Acmeplex_1.gif';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const navigate = useNavigate();
  const handleHomePressed =()=>{
    navigate('/')
  }

  return (
    <header className='header'>
      <div className="logo-link">
        <img src={logo} className="logoimage" alt="AcmePlex logo" onClick={handleHomePressed}/>
      </div>
      <div className= "title-container">
          <h1>AcmePlex</h1>
      </div>
    </header>
  );
};

export default Header;
