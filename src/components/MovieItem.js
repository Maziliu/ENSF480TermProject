import React, {useState, useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import {useAuthContext} from '../App.js';

const MovieItem = ({ movie }) => {
    return (
      <div className="movie-item">
        <div className="movie-poster">
          <img src={movie.posterUrl} alt={movie.title} />
        </div>
        <div className="movie-title">{movie.title}</div>
      </div>
    );
  };
  
  export default MovieItem;
  