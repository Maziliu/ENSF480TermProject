import React from 'react';
import Header from './Header';
import SearchBar from './SearchBar';
import TheatreList from './TheatreList';
import MovieGrid from './MovieGrid';
import Footer from './Footer';

const HomePage = ({ selectedTheatre, handleSelectTheatre, handleSelectMovie }) => {
  const handleSearch = (query) => {
    // Handle search logic
  };

  return (
    <div>
      <Header />
      <SearchBar onSearch={handleSearch} />
      <TheatreList onSelectTheatre={handleSelectTheatre} />
      <MovieGrid selectedTheatre={selectedTheatre} onSelectMovie={handleSelectMovie} />
      <Footer />
    </div>
  );
};

export default HomePage;
