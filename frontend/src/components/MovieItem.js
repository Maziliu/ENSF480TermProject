import React from 'react';

const MovieItem = ({ movie }) => {
  const posterPath = `/images/posters/${movie.movieName}.jpg`;
  const defaultPoster = '/images/posters/default-poster.jpg';

  return (
    <div className="movie-item">
      <div className="movie-poster">
        <img 
          src={posterPath} 
          alt={movie.movieName} 
          onError={(e) => e.target.src = defaultPoster}
        />
      </div>
      <div className='movie-text'>
      <h3 className="movie-title"><b>{movie.movieName}</b></h3>
      <p>{movie.genre}</p></div>
    </div>
  );
};

export default MovieItem;
