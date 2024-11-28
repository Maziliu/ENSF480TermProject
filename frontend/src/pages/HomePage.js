import Footer from '../components/Footer';
import Header from '../components/Header';
import MovieGrid from '../components/MovieGrid';
import SearchBar from '../components/SearchBar';
import TheatreList from '../components/TheatreList';
import Navigation from '../components/Navigation';
import Notification from '../components/Notification';
import { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';

const HomePage = () => {
  const [movies, setMovies] = useState([]);
  const [newReleases, setNewReleases] = useState([]);
  const [queriedMovies, setQueriedMovies] = useState([]);
  const [query, setQuery] = useState('');

  return (
    <div>
      <Header />
      <Navigation />
      {newReleases && <Notification movies={movies}/>}
      <SearchBar handleSetMovieList={setQueriedMovies} setQuery={setQuery} query={query}/>
      {<TheatreList />}
      <MovieGrid handleSetMovieList={setMovies} movies={movies} queriedMovies={queriedMovies} query={query} setNewReleases={setNewReleases}/>
      <Footer />
    </div>
  );
};

export default HomePage;
