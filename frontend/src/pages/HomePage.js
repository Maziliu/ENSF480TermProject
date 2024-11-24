import Footer from '../components/Footer';
import Header from '../components/Header';
import MovieGrid from '../components/MovieGrid';
import SearchBar from '../components/SearchBar';
import TheatreList from '../components/TheatreList';

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
