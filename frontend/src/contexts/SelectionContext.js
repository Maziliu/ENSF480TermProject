import React, { createContext, useState, useContext } from 'react';

const SelectionContext = createContext();

export const useSelectionContext = () => useContext(SelectionContext);

export const SelectionProvider = ({ children }) => {
  const [selectedTheatre, setSelectedTheatre] = useState('');
  const [selectedMovie, setSelectedMovie] = useState('');
  const [selectedShowtime, setSelectedShowtime] = useState('');

  const handleSelectTheatre = (theatreId) => {
    setSelectedTheatre(theatreId);
    setSelectedShowtime('');
  };

  const handleSelectMovie = (movieId) => {
    setSelectedMovie(movieId);
    setSelectedShowtime('');
  };

  const handleSelectShowtime = (showtimeId) => {
    setSelectedShowtime(showtimeId);
  };

  return (
    <SelectionContext.Provider
      value={{
        selectedTheatre,
        handleSelectTheatre,
        selectedMovie,
        handleSelectMovie,
        selectedShowtime,
        handleSelectShowtime,
      }}
    >
      {children}
    </SelectionContext.Provider>
  );
};
