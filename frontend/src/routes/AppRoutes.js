import React from 'react';
import { Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import MoviePage from '../pages/MoviePage';
import LoginPage from '../pages/LoginPage';
import SeatMap from '../components/SeatMap';

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<HomePage />} />
    <Route path="/login" element={<LoginPage />} />
    <Route path="/movies/:id" element={<MoviePage />} />
    <Route path="/seats/:showtimeId" element={<SeatMap />} />
  </Routes>
);

export default AppRoutes;
