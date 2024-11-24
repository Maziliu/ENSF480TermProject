import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import LogoutButton from './buttons/LogoutButton';

const Header = () => {
  const { authenticated } = useAuthContext();

  return (
    <header>
      <nav>
        <Link to="/">Home</Link>
        {authenticated ? <LogoutButton /> : <Link to="/login">Login</Link>}
      </nav>
    </header>
  );
};

export default Header;
