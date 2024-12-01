import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useFetchData } from '../hooks/useFetchData';
import { fetchMovieById, fetchShowtimes, fetchTheatres } from '../services/api';
import { useSelectionContext } from '../contexts/SelectionContext';
import Header from '../components/Header';
import Footer from '../components/Footer';
import '../styles/MoviePage.css';
import '../styles/Global.css';

const MoviePage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data: movie, error: movieError } = useFetchData(() => fetchMovieById(id), [id]);
  const { data: showtimes } = useFetchData(fetchShowtimes);
  const { data: theatres } = useFetchData(fetchTheatres);

  const { selectedTheatre, handleSelectTheatre, selectedShowtime, handleSelectShowtime } = useSelectionContext();

  const [posterUrl, setPosterUrl] = useState('/images/posters/default-poster.png');

  useEffect(() => {
    if (movie?.movieName) {
      const posterPath = `/images/posters/${movie.movieName}.jpg`;
      const img = new Image();

      img.onload = () => setPosterUrl(posterPath);
      img.onerror = () => setPosterUrl('/images/posters/default-poster.png');

      img.src = posterPath;
    } else {
      setPosterUrl('/images/posters/default-poster.png');
    }
  }, [movie]);

  const handleTheatreChange = (event) => {
    const theatreId = event.target.value;
    const theatreName = event.target.options[event.target.selectedIndex].text;
    handleSelectTheatre(theatreId, theatreName);
    handleSelectShowtime('');
  };

  const handleShowtimeChange = (event) => {
    const showtimeId = event.target.value;
    const showtimeTime = event.target.options[event.target.selectedIndex].text;
    handleSelectShowtime(showtimeId, showtimeTime);
  };

  const handleGetTicketsClick = () => {
    if (selectedTheatre && selectedShowtime) {
      navigate(`/seats/${selectedShowtime}`);
    } else {
      alert('Please select a theatre and showtime.');
    }
  };

  if (movieError) {
    return <div>Error loading movie: {movieError.message}</div>;
  }

  if (!movie) {
    return <div>Loading movie details...</div>;
  }

  return (
    <div>
      <Header />
      <div className='page-body'>
      <div className="movie-page">
        <div className='movie-poster'>
          <img 
            src={posterUrl} 
            alt={movie.movieName || 'Movie Poster'} 
            onError={() => setPosterUrl('/images/posters/default-poster.png')}
            />
        </div>
        <div className="movie-details">
          <h1 className='movie-header'> {movie.movieName}</h1>
          <p><b>Duration</b>&emsp;{Math.floor(movie.durationInSeconds / 3600)}h {Math.floor((movie.durationInSeconds % 3600)) / 60}m</p>
          <p><b>Genre</b>&emsp;{movie.genre}</p>
          <p><b>Rating</b>&emsp;{movie.ratingOutOfTen}/10</p>
          <p>{movie.description}</p>
          <div className="movie-page-selectors">
            <div className="selection-lists">
            <select value={selectedTheatre} onChange={handleTheatreChange}>
              <option value="">Select a Theatre</option>
              {theatres?.map((theatre) => (
                <option key={theatre.theatreId} value={theatre.theatreId}>
                  {theatre.theatreName}
                </option>
              ))}
            </select>
            <select value={selectedShowtime} onChange={handleShowtimeChange} disabled={!selectedTheatre}>
              <option value="">Select a Showtime</option>
              {showtimes
                ?.filter((showtime) =>
                  theatres
                    ?.find((theatre) => theatre.theatreId === parseInt(selectedTheatre))
                    ?.theatreRooms.some((room) => room.showtimes.some((st) => st.showtimeId === showtime.showtimeId))
                )
                .map((showtime) => (
                  <option key={showtime.showtimeId} value={showtime.showtimeId}>
                    {new Date(showtime.airTime).toLocaleString()}
                  </option>
                ))}
            </select>
            </div><div class="button-container">
            <button className='select-movie-button' onClick={handleGetTicketsClick} disabled={!selectedTheatre || !selectedShowtime}>
              Get Tickets
            </button></div>
          </div>
        </div>
      </div>
      </div>
      <Footer />
    </div>
  );
};

export default MoviePage;