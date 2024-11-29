import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import LogoutButton from './buttons/LogoutButton';
import '../styles/Navigation.css';

const Header = () => {
  const { role } = useAuthContext();

  return (
      <nav className='navbar'>
        {(role === "user") && (
            <Link to='/account' className='nav-link-user'>User Account</Link>
        )}
        {(role === "admin") && (
            <Link to='/admin' className='nav-link'>Admin Dashboard</Link>
        )}
        {role !== 'guest' ? <LogoutButton /> : <Link to="/login" className='nav-link'>Login</Link>}
      </nav>
  );
};

export default Header;
