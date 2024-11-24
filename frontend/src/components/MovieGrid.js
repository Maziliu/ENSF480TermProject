import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelectionContext } from '../contexts/SelectionContext';
import MovieItem from './MovieItem';
import '../styles/MovieGrid.css';

const MovieGrid = () => {
  const { selectedTheatre, handleSelectMovie } = useSelectionContext();
  const [movies, setMovies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = selectedTheatre ? `http://localhost:8080/browse/theatres/search?theatreName=${selectedTheatre}` : 'http://localhost:8080/browse/movies';

    fetch(url, {
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
      .then((data) => setMovies(data))
      .catch((error) => console.error('Error fetching movies:', error));
  }, [selectedTheatre]);

  const handleMovieClick = (id) => {
    handleSelectMovie(id);
    navigate(`/movies/${id}`);
  };

  return (
    <div className="movie-grid">
      {movies.map((movie) => (
        <div key={movie.movieId} onClick={() => handleMovieClick(movie.movieId)}>
          <MovieItem movie={movie} />
        </div>
      ))}
    </div>
  );
};

export default MovieGrid;
