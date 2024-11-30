import React from 'react';
import { Link } from 'react-router-dom';
import { useAuthContext } from '../../contexts/AuthContext';
import LogoutButton from '../buttons/LogoutButton';
import '../../styles/CTA.css';
import '../../styles/Header.css';

const CTA = () => {
  const { role } = useAuthContext();

  return (
      <nav className='ctabar'>
        {role !== 'guest' ? <LogoutButton /> : <Link to="/login" className='logout-button'>Login</Link>}
      </nav>
  );
};

export default CTA;