import './App.css';
import React, { createContext, useContext, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import MoviePage from './components/MoviePage';
import PaymentPage from './components/PaymentPage';
import SeatMap from './components/SeatMap';
import ReceiptPage from './components/ReceiptPage';
import CancelTicketPage from './components/CancelTicketPage';

const AuthContext = createContext();

export const useAuthContext = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const isAuthenticated = sessionStorage.getItem('authenticated');
  const [authenticated, setAuthenticated] = useState(isAuthenticated ? isAuthenticated : false);

  return (
    <AuthContext.Provider value={{ authenticated, setAuthenticated }}>
      {children}
    </AuthContext.Provider>
  );
};

const App = () => {
  const [selectedTheatre, setSelectedTheatre] = useState('');
  const [selectedMovie, setSelectedMovie] = useState('');
  const [selectedShowtime, setSelectedShowtime] = useState('');

  const handleSelectTheatre = (theatreId) => {
    setSelectedTheatre(theatreId);
    setSelectedShowtime('');
    console.log('Selected theatre:', theatreId);
  };

  const handleSelectMovie = (movieId) => {
    setSelectedMovie(movieId);
    setSelectedShowtime('');
  };

  const handleSelectShowtime = (showtimeId) => {
    setSelectedShowtime(showtimeId);
  };

  const handleGetTickets = (theatreId, showtimeId) => {
    if (theatreId && selectedMovie && showtimeId) {
      console.log('Get Tickets for:', { theatreId, selectedMovie, showtimeId });
      // Navigate to the SeatMap component
      window.location.href = `/seats/${showtimeId}`;
    } else {
      alert('Please select a theatre, movie, and showtime.');
    }
  };

  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route
            path="/"
            element={
              <HomePage
                selectedTheatre={selectedTheatre}
                handleSelectTheatre={handleSelectTheatre}
                handleSelectMovie={handleSelectMovie}
              />
            }
          />
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/movie/:id"
            element={
              <MoviePage
                selectedTheatre={selectedTheatre}
                selectedMovie={selectedMovie}
                selectedShowtime={selectedShowtime}
                handleSelectShowtime={handleSelectShowtime}
                handleGetTickets={handleGetTickets}
                handleSelectTheatre={handleSelectTheatre}
              />
            }
          />
          <Route path="/seats/:showtimeId" element={<SeatMap />} />
          <Route path="/payment/:showtimeId/:seatName" element={<PaymentPage />} />
          <Route path="/receipt/:showtimeId/:seatName" element={<ReceiptPage />} />
          <Route path="/cancel" element={<CancelTicketPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
