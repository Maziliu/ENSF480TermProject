import React, { useState, useEffect } from 'react';
import '../../styles/ManageMovies.css';
import '../../styles/AdminPage.css';

//NOT ADJUSTED TO THIS FRONTEND ROUTING
const ManageMovies = () => {
  const [movies, setMovies] = useState([]);
  const [newMovie, setNewMovie] = useState({ title: '', genre: '', duration: '', description: '', rating: '' });
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
        title: selectedMovie.title,
        genre: selectedMovie.genre,
        duration: selectedMovie.duration,
        description: selectedMovie.description,
        rating: selectedMovie.rating,
      });
    } else {
      setNewMovie({ title: '', genre: '', duration: '', description: '', rating: '' });
    }
  }, [selectedMovie]); // effect when selection changes

  const handleAddOrUpdateMovie = () => {
    const newMovieData = {
      title: newMovie.title,
      genre: newMovie.genre,
      duration: newMovie.duration,
      description: newMovie.description,
      rating: newMovie.rating,
    };
    console.log('sending: ', newMovieData);
    const method = selectedMovie ? 'PUT' : 'POST';
    const url = selectedMovie ? `http://localhost:5000/Movies/Update/${selectedMovie.id}` : 'http://localhost:5000/Movies/Add';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newMovieData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.message) {
          if (selectedMovie) {
            setMovies(movies.map((movie) => (movie.id === selectedMovie.id ? { ...movie, ...newMovieData } : movie)));
            setMessage('Movie updated successfully.');
          } else {
            setMovies([...movies, { id: Date.now(), ...newMovieData }]);
            setMessage('New movie added successfully.');
          }
          setSelectedMovie(null);
          setNewMovie({ title: '', genre: '', duration: '', description: '', rating: '' });
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
    fetch(`http://localhost:5000/Movies/Delete/${movieId}`, { method: 'DELETE' })
      .then((response) => response.json())
      .then((data) => {
        if (data.message) {
          setMovies(movies.filter((movie) => movie.id !== movieId));
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
      <input type="text" placeholder="Movie Title" value={newMovie.title} onChange={(e) => setNewMovie({ ...newMovie, title: e.target.value })} />
      <input type="text" placeholder="Genre" value={newMovie.genre} onChange={(e) => setNewMovie({ ...newMovie, genre: e.target.value })} />
      <input type="text" placeholder="Duration" value={newMovie.duration} onChange={(e) => setNewMovie({ ...newMovie, duration: e.target.value })} />
      <textarea placeholder="Description" value={newMovie.description} onChange={(e) => setNewMovie({ ...newMovie, description: e.target.value })} />
      <input type="text" placeholder="Rating" value={newMovie.rating} onChange={(e) => setNewMovie({ ...newMovie, rating: e.target.value })} />
      <button
        className="manage-movies-buttons"
        onClick={handleAddOrUpdateMovie}
        disabled={!newMovie.title || !newMovie.genre || !newMovie.duration || !newMovie.description || !newMovie.rating}
      >
        {selectedMovie ? 'Update Movie' : 'Add Movie'}
      </button>
      <ul>
        {movies.map((movie) => (
          <li key={movie.id}>
            {movie.title} - {movie.genre}
            <button className="manage-movies-buttons" onClick={() => setSelectedMovie(movie)}>
              Edit
            </button>
            <button className="manage-movies-buttons" onClick={() => handleRemoveMovie(movie.id)}>
              Remove
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageMovies;
