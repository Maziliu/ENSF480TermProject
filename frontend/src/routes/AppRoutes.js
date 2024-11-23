import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LoginPage from '../pages/LoginPage';
import HomePage from '../pages/HomePage';

const AppRoutes = ({}) => (
  <Routes>
    <Route path="/Login" element={<LoginPage />} />
    <Route path="/" element={<HomePage />} />
  </Routes>
);

export default AppRoutes;
