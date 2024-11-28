import React from 'react';

const MovieItem = ({ movie }) => (
  <div className="movie-item">
    {movie.posterUrl ? <img src={movie.posterUrl} alt={movie.movieName} /> : <div className="movie-poster-placeholder1" />}
    <h3 className='movie-title'><b>{movie.movieName}</b></h3>
    <p>{movie.genre}</p>
  </div>
);

export default MovieItem;
