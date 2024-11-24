import React from 'react';

const MovieItem = ({ movie }) => {
  return (
    <div className="movie-item">
      <div className="movie-poster">
        <img src={movie.posterUrl} alt={movie.title} />
      </div>
      <div className="movie-title">{movie.movieName}</div>
    </div>
  );
};

export default MovieItem;
