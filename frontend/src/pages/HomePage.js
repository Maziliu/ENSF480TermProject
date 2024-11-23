// import React from 'react';
// import Header from './Header';
// import SearchBar from './SearchBar';
// import TheatreList from './TheatreList';
// import MovieGrid from './MovieGrid';
// import Footer from './Footer';

import Footer from '../components/Footer';
import Header from '../components/Header';

// const HomePage = ({ selectedTheatre, handleSelectTheatre, handleSelectMovie }) => {
//   const handleSearch = (query) => {
//     // Handle search logic
//   };

//   return (
//     <div>
//       <Header />
//       <SearchBar onSearch={handleSearch} />
//       <TheatreList onSelectTheatre={handleSelectTheatre} />
//       <MovieGrid selectedTheatre={selectedTheatre} onSelectMovie={handleSelectMovie} />
//       <Footer />
//     </div>
//   );
// };

// export default HomePage;

const HomePage = () => {
  return (
    <div>
      <Header />
      Home
      <Footer />
    </div>
  );
};

export default HomePage;
