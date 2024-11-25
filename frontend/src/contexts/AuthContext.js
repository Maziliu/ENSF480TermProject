import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const useAuthContext = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const savedRole = sessionStorage.getItem('role');
  const savedUserId = sessionStorage.getItem('userId');

  const [role, setRole] = useState(savedRole ? savedRole : 'guest');
  const [userId, setUserId] = useState(savedUserId ? savedUserId : '');

  const logout = () => {
    sessionStorage.removeItem('role');
    sessionStorage.removeItem('userId');
    setRole('guest');
    setUserId('guest');
  };

  return <AuthContext.Provider value={{ role, setRole, userId, setUserId, logout }}>{children}</AuthContext.Provider>;
};
