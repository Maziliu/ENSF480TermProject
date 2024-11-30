import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../../contexts/AuthContext';
import '../../styles/Navigation.css';

const Navigation = () => {
  const { role } = useAuthContext();

  return (
      <nav className='navbar'>
        {(role === "user") && (
            <Link to='/account' className='nav-link-user'>User Account</Link>
        )}
        {(role === "admin") && (
            <Link to='/admin' className='nav-link-admin'>Admin Dashboard</Link>
        )}
        <Link to='/cancel' className='nav-link-cancel'>Cancel Ticket</Link>
      </nav>
  );
};

export default Navigation;