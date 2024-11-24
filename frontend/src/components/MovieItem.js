import React from 'react';

const MovieItem = ({ movie }) => (
  <div className="movie-item">
    <img src={movie.posterUrl} alt={movie.movieName} />
    <h3>{movie.movieName}</h3>
    <p>{movie.genre}</p>
  </div>
);

export default MovieItem;
