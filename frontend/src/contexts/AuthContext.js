import React, { createContext, useContext, useEffect, useState } from 'react';

const AuthContext = createContext();

export const useAuthContext = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const savedRole = sessionStorage.getItem('role');
  const savedUserId = sessionStorage.getItem('userId');
  const savedUserEmail = sessionStorage.getItem('userEmail');

  const [role, setRole] = useState(savedRole ? savedRole : 'guest');
  const [userId, setUserId] = useState(savedUserId ? savedUserId : '');
  const [userEmail, setUserEmail] = useState(savedUserEmail ? savedUserEmail : '');

  const logout = () => {
    sessionStorage.removeItem('role');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('userEmail');
    setRole('guest');
    setUserId('');
    setUserEmail('');
  };

  return <AuthContext.Provider value={{ role, setRole, userId, setUserId, setUserEmail, userEmail, logout }}>{children}</AuthContext.Provider>;
};
