import Footer from '../components/Footer';
import Header from '../components/Header';
import MovieGrid from '../components/MovieGrid';
import SearchBar from '../components/SearchBar';
import TheatreList from '../components/TheatreList';
import Navigation from '../components/Navigation';
import NewReleaseNotification from '../components/NewReleaseNotification';
import { useAuthContext } from '../contexts/AuthContext';
import { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';

const HomePage = () => {
  const [movies, setMovies] = useState([]);
  const [newReleases, setNewReleases] = useState([]);
  const [queriedMovies, setQueriedMovies] = useState([]);
  const [showNewReleases, setShowNewReleases] = useState(false);
  const [query, setQuery] = useState('');
  const { role, userId } = useAuthContext();

  useEffect(()=>{
    console.log("newre:", newReleases);
    if (newReleases && newReleases.length > 0 && role === 'user'){
      setShowNewReleases(true);
    }
  },[userId])

  return (
    <div>
      <Header />
      <Navigation />
      {showNewReleases && <NewReleaseNotification movies={newReleases}/>}
      <SearchBar handleSetMovieList={setQueriedMovies} setQuery={setQuery} query={query}/>
      {<TheatreList />}
      <MovieGrid handleSetMovieList={setMovies} movies={movies} queriedMovies={queriedMovies} query={query} setNewReleases={setNewReleases} />
      <Footer />
    </div>
  );
};

export default HomePage;
