import React, { useState, useEffect } from 'react';
import '../../styles/ManageMovies.css';
import '../../styles/AdminPage.css';

//NOT FULLY IMPLEMENTED
const ManageMovies = () => {
  const [movies, setMovies] = useState([]);
  const [newMovie, setNewMovie] = useState({ movieName: '', genre: '', durationInSeconds: '', description: '', ratingOutOfTen: '' });
  const [selectedMovie, setSelectedMovie] = useState(null);
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const moviesResponse = await fetch('http://localhost:8080/browse/movies');
        const moviesData = await moviesResponse.json();
        setMovies(moviesData);
      } catch (error) {
        console.error('Error fetching data:', error);
        setMessage('Failed to load data.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []); // run only once(twice, really)

  useEffect(() => {
    // prefill form logic
    if (selectedMovie) {
      setNewMovie({
        movieName: selectedMovie.movieName,
        genre: selectedMovie.genre,
        durationInSeconds: selectedMovie.durationInSeconds,
        description: selectedMovie.description,
        ratingOutOfTen: selectedMovie.ratingOutOfTen,
      });
    } else {
      setNewMovie({ movieName: '', genre: '', durationInSeconds: '', description: '', ratingOutOfTen: '' });
    }
  }, [selectedMovie]); // effect when selection changes

  const handleAddOrUpdateMovie = () => {
    const newMovieData = {
      movieName: newMovie.movieName,
      genre: newMovie.genre,
      durationInSeconds: newMovie.durationInSeconds,
      description: newMovie.description,
      ratingOutOfTen: newMovie.ratingOutOfTen,
    };
    console.log('sending: ', newMovieData);
    const method = selectedMovie ? 'PUT' : 'POST';
    const url = selectedMovie ? `http://localhost:8080/movies/update/${selectedMovie.movieId}` : 'http://localhost:8080/movies/add';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newMovieData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.message) {
          if (selectedMovie) {
            setMovies(movies.map((movie) => (movie.movieId === selectedMovie.movieId ? { ...movie, ...newMovieData } : movie)));
            setMessage('Movie updated successfully.');
          } else {
            setMovies([...movies, { movieId: Date.now(), ...newMovieData }]);
            setMessage('New movie added successfully.');
          }
          setSelectedMovie(null);
          setNewMovie({ movieName: '', genre: '', durationInSeconds: '', description: '', ratingOutOfTen: '' });
        } else {
          setMessage('Failed to add or update movie.');
        }
      })
      .catch((error) => {
        console.error('Error:', error);
        setMessage('Failed to add or update movie.');
      });
  };

  const handleRemoveMovie = (movieId) => {
    fetch(`http://localhost:8080/movies/delete/${movieId}`, { method: 'DELETE' })
      .then((response) => response.json())
      .then((data) => {
        if (data.message) {
          setMovies(movies.filter((movie) => movie.movieId !== movieId));
          setMessage('Movie removed successfully.');
        } else {
          setMessage('Failed to remove movie.');
        }
      })
      .catch((error) => {
        console.error('Error:', error);
        setMessage('Failed to remove movie.');
      });
  };

  if (isLoading) {
    return <div className="loading">Loading...</div>;
  }

  return (
    <div className="manage-movies">
      <h2>Manage Movies</h2>
      <div>{message}</div>
      <br />
      <input type="text" placeholder="Movie Title" value={newMovie.movieName} onChange={(e) => setNewMovie({ ...newMovie, movieName: e.target.value })} />
      <input type="text" placeholder="Genre" value={newMovie.genre} onChange={(e) => setNewMovie({ ...newMovie, genre: e.target.value })} />
      <input type="text" placeholder="Duration" value={newMovie.durationInSeconds} onChange={(e) => setNewMovie({ ...newMovie, durationInSeconds: e.target.value })} />
      <textarea placeholder="Description" value={newMovie.description} onChange={(e) => setNewMovie({ ...newMovie, description: e.target.value })} />
      <input type="text" placeholder="Rating" value={newMovie.ratingOutOfTen} onChange={(e) => setNewMovie({ ...newMovie, ratingOutOfTen: e.target.value })} />
      <button
        className="manage-movies-buttons"
        onClick={handleAddOrUpdateMovie}
        disabled={!newMovie.movieName || !newMovie.genre || !newMovie.durationInSeconds || !newMovie.description || !newMovie.ratingOutOfTen}
      >
        {selectedMovie ? 'Update Movie' : 'Add Movie'}
      </button>
      <ul>
        {movies.map((movie) =>  (
          <li key={movie.movieId}>
            {movie.movieName} - {movie.genre}
            <button className="manage-movies-buttons" onClick={() => setSelectedMovie(movie)}>
              Edit
            </button>
            <button className="manage-movies-buttons" onClick={() => handleRemoveMovie(movie.movieId)}>
              Remove
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageMovies;
