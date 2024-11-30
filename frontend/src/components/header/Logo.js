import React from 'react';
import '../../styles/Header.css';
import '../../styles/Logo.css';
import { useNavigate } from 'react-router-dom';

const Logo = () => {
  return (
    <div className='logo'>
      <a href="/" className="logo-link">
        <img src="/images/Acmeplex_1.gif" className="logo-image" alt="AcmePlex logo" />
      </a>
      <a href="/" className= "title-container">
          <h1>AcmePlex</h1>
      </a>
    </div>
  );
};

export default Logo;