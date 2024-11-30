import { useAuthContext } from '../../contexts/AuthContext';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import'../../styles/LogoutButton.css';


const LogoutButton = () => {
  const { logout } = useAuthContext();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <button className='logout-button' onClick={handleLogout}>
      Logout
    </button>
  );
};

export default LogoutButton;
