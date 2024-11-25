import React from 'react';
import { Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import MoviePage from '../pages/MoviePage';
import LoginPage from '../pages/LoginPage';
import SeatMap from '../components/SeatMap';
import PaymentPage from '../pages/PaymentPage';
import AdminPage from '../pages/AdminPage';
import UserAccountPage from '../pages/UserAccountPage';

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<HomePage />} />
    <Route path="/login" element={<LoginPage />} />
    <Route path="/movies/:id" element={<MoviePage />} />
    <Route path="/seats/:showtimeId" element={<SeatMap />} />
    <Route path="/payment/:showtimeId/:seatName" element={<PaymentPage />} />
    <Route path="/account" element={<UserAccountPage />} />
    <Route path="/admin" element={<AdminPage />} />
  </Routes>
);

export default AppRoutes;
