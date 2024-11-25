import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import LogoutButton from './buttons/LogoutButton';

const Header = () => {
  const { role } = useAuthContext();

  return (
    <header>
      <div>
      <img style={{ height: '60px' }} src='/images/logo.png' className="logo" alt="acmeplex logo" />
        <h1>AcmePlex</h1>
      </div>
      <nav>
        <Link to="/">Home</Link>
        {role !== 'guest' ? <LogoutButton /> : <Link to="/login">Login</Link>}
      </nav>
    </header>
  );
};

export default Header;
