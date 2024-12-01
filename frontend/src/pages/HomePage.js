import Footer from '../components/Footer'; 
import Header from '../components/Header';
import MovieGrid from '../components/MovieGrid';
import SearchBar from '../components/SearchBar';
import TheatreList from '../components/TheatreList';
import NewReleaseNotification from '../components/notifications/NewReleaseNotification';
import { useAuthContext } from '../contexts/AuthContext';
import { useState, useEffect } from 'react';
import { fetchMovies } from '../services/api';

const HomePage = () => {
  const [movies, setMovies] = useState([]);
  const [newReleases, setNewReleases] = useState([]);
  const [queriedMovies, setQueriedMovies] = useState([]);
  const [showNewReleases, setShowNewReleases] = useState(false);
  const [query, setQuery] = useState('');
  const { role, userId } = useAuthContext();

  useEffect(() => {
    fetch('http://localhost:8080/browse/movies', {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('API call failed');
        }
      })
      .then((data) => {
        const newMovies = data.filter(movie => !movie.isReleased);
        setNewReleases(newMovies);
        if (newMovies.length.toString() !== localStorage.getItem('newReleases').toString()) {
        }
      })
      .catch((error) => console.error('Error fetching new releases:', error));
  }, []);

  useEffect(() => {
    if (newReleases.length > 0 && newReleases.length.toString() !== localStorage.getItem('newReleases')) {
      console.log("reset notif");
      localStorage.setItem('seenNotification', 'false');
    }
  }, [newReleases.length, userId]);

  useEffect(() => {
    const seenNotification = localStorage.getItem('seenNotification');
    if (seenNotification === 'false' && role === 'user' && newReleases.length > 0) {
      setShowNewReleases(true);
      localStorage.setItem('seenNotification', 'true');
    }
  }, [newReleases.length, userId, role]);

  return (
    <div>
      <Header />
      {showNewReleases && <NewReleaseNotification movies={newReleases} />}
      <SearchBar handleSetMovieList={setQueriedMovies} setQuery={setQuery} query={query} />
      <TheatreList />
      <br></br>
      <MovieGrid handleSetMovieList={setMovies} movies={movies} queriedMovies={queriedMovies} query={query} setNewReleases={setNewReleases} />
      <Footer />
    </div>
  );
};

export default HomePage;
