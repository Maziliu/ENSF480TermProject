import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const useAuthContext = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const isAuthenticated = sessionStorage.getItem('authenticated');
  const [authenticated, setAuthenticated] = useState(isAuthenticated === 'true');

  const logout = () => {
    sessionStorage.removeItem('authenticated');
    setAuthenticated(false);
  };

  return <AuthContext.Provider value={{ authenticated, setAuthenticated, logout }}>{children}</AuthContext.Provider>;
};
