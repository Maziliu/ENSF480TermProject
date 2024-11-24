import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useFetchData } from '../hooks/useFetchData';
import { fetchMovieById, fetchShowtimes, fetchTheatres } from '../services/api';
import { useSelectionContext } from '../contexts/SelectionContext';
import Header from '../components/Header';
import Footer from '../components/Footer';

const MoviePage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data: movie, error: movieError } = useFetchData(() => fetchMovieById(id), [id]);
  const { data: showtimes } = useFetchData(fetchShowtimes);
  const { data: theatres } = useFetchData(fetchTheatres);

  const { selectedTheatre, handleSelectTheatre, selectedShowtime, handleSelectShowtime } = useSelectionContext();

  const handleTheatreChange = (event) => {
    const theatreId = event.target.value;
    handleSelectTheatre(theatreId);
    handleSelectShowtime('');
  };

  const handleShowtimeChange = (event) => {
    const showtimeId = event.target.value;
    handleSelectShowtime(showtimeId);
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
    <div className="movie-page">
      <Header />
      <div className="movie-poster">
        <img src={movie.posterUrl} alt={movie.movieName} />
      </div>
      <div className="movie-details">
        <h1>{movie.movieName}</h1>
        <p>Duration: {Math.floor(movie.durationInSeconds / 60)} minutes</p>
        <p>{movie.description}</p>
        <p>Genre: {movie.genre}</p>
        <p>Rating: {movie.ratingOutOfTen}/10</p>
      </div>
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
            ?.filter((showtime) => showtime.theatre_id === parseInt(selectedTheatre))
            .map((showtime) => (
              <option key={showtime.id} value={showtime.id}>
                {new Date(showtime.time).toLocaleString()}
              </option>
            ))}
        </select>
      </div>
      <button onClick={handleGetTicketsClick} disabled={!selectedTheatre || !selectedShowtime}>
        Get Tickets
      </button>
      <Footer />
    </div>
  );
};

export default MoviePage;
