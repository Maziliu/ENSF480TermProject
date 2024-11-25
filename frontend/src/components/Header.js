import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import LogoutButton from './buttons/LogoutButton';
import '../styles/Header.css';

const Header = () => {
  const { role } = useAuthContext();

  return (
    <header className='header'>
      <a href="/" className="logo-link">
        <img src='/images/logo.png' className="logoimage" alt="AcmePlex logo" />
      </a>
      <div className= "title-container">
          <h1>AcmePlex</h1>
      </div>
      <nav className='navbar'>
        {(role === "user") && (
            <Link to='/account'>User Account</Link>
        )}
        {(role === "admin") && (
            <Link to='/admin'>Admin Dashboard</Link>
        )}
        {role !== 'guest' ? <LogoutButton /> : <Link to="/login">Login</Link>}
      </nav>
    </header>
  );
};

export default Header;
