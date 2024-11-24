import { useAuthContext } from '../../contexts/AuthContext';
import React from 'react';
import { useNavigate } from 'react-router-dom';

const LogoutButton = () => {
  const { logout } = useAuthContext();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <span
      onClick={handleLogout}
      style={{
        cursor: 'pointer',
        textDecoration: 'underline',
        color: 'blue',
        margin: '10px',
      }}
    >
      Logout
    </span>
  );
};

export default LogoutButton;
