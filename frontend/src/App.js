import React, { useState } from 'react';
import { AuthProvider } from './contexts/AuthContext';
import AppRoutes from './routes/AppRoutes';

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

  return (
    <AuthProvider>
      <AppRoutes selectedTheatre={selectedTheatre} handleSelectTheatre={handleSelectTheatre} handleSelectMovie={handleSelectMovie} />
    </AuthProvider>
  );
};

export default App;
