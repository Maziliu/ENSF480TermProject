import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LoginPage from '../pages/LoginPage';
import HomePage from '../pages/HomePage';

const AppRoutes = ({ selectedTheatre, handleSelectTheatre, handleSelectMovie }) => (
  <Routes>
    <Route path="/" element={<HomePage selectedTheatre={selectedTheatre} handleSelectTheatre={handleSelectTheatre} handleSelectMovie={handleSelectMovie} />} />
    <Route path="/Login" element={<LoginPage />} />
  </Routes>
);

export default AppRoutes;
