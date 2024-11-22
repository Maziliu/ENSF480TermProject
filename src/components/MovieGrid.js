import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import MovieItem from './MovieItem';
import "./Styles.css";

const MovieGrid = ({ selectedTheatre, onSelectMovie}) => {
  const [movies, setMovies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = selectedTheatre
      ? `http://127.0.0.1:5000/Movies/Theatre/${selectedTheatre}`
      : 'http://127.0.0.1:5000/Movies';

    fetch(url, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('API call failed');
        }
      })
      .then(data => setMovies(data))
      .catch(error => console.log(error));
  }, [selectedTheatre]);

  const handleMovieClick = (id) => {
    onSelectMovie(id);
    navigate(`/movie/${id}`);
  };

  return (
    <div className="movie-grid">
      {movies.map((movie) => (
        <div key={movie.id} onClick={() => handleMovieClick(movie.id)}>
          <MovieItem movie={movie} />
        </div>
      ))}
    </div>
  );
};

export default MovieGrid;


