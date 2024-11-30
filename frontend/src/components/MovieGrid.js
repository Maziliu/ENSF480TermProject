import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelectionContext } from '../contexts/SelectionContext';
import MovieItem from './MovieItem';
import '../styles/MovieGrid.css';
import TheatreList from './TheatreList';

const MovieGrid = ({ handleSetMovieList, movies , queriedMovies, query }) => {
  const { selectedTheatreName, handleSelectMovie } = useSelectionContext();
  const [filteredMovies, setFilteredMovies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = selectedTheatreName
      ? `http://localhost:8080/browse/theatres/search?theatreName=${encodeURIComponent(selectedTheatreName)}`
      : 'http://localhost:8080/browse/movies';

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
      .then((data) => {
        if (data) {
          console.log("movies:", data);
          const newMovies = data.filter(movie => movie.isReleased);
          handleSetMovieList(newMovies);
  }})
      .catch((error) => console.error('Error fetching movies:', error));
  }, [selectedTheatreName]);

  useEffect(() =>{
    setFilteredMovies(movies.filter(movie => queriedMovies.some(qMovie => qMovie.movieId === movie.movieId)));
  }, [queriedMovies, movies])

  const handleMovieClick = (id, name) => {
    handleSelectMovie(id, name);
    navigate(`/movies/${id}`);
  };

  if (queriedMovies.length !== 0 && filteredMovies.length !== 0){
    return (
      <div className="movie-grid">{
      filteredMovies.map((movie) => (
        <div key={movie.movieId} onClick={() => handleMovieClick(movie.movieId, movie.movieName)}>
          <MovieItem movie={movie} />
        </div> ))}
      </div>)
  }

  if (query && filteredMovies.length === 0){
    return (
      <div className="movie-grid"></div> )
  }

  return (
    <div className="movie-grid">
      {movies.map((movie) => (
        <div key={movie.movieId} onClick={() => handleMovieClick(movie.movieId, movie.movieName)}>
          <MovieItem movie={movie} />
        </div> ))}
    </div>
  );
};

export default MovieGrid;
