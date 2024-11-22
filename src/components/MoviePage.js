import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';

const MoviePage = ({ selectedTheatre, selectedMovie, handleSelectShowtime, handleSelectTheatre, handleGetTickets }) => {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [theatres, setTheatres] = useState([]);
  const [showtimes, setShowtimes] = useState([]);
  const [selectedShowtime, setSelectedShowtime] = useState('');
  const [selectedTheatreState, setSelectedTheatreState] = useState(selectedTheatre || '');
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://127.0.0.1:5000/Movies/${id}`, {
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
      .then(data => setMovie(data))
      .catch(error => console.log(error));
  }, [id]);

  useEffect(() => {
    if (movie) {
      fetch('http://127.0.0.1:5000/Showtimes', {
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
        .then(data => {
          const movieShowtimes = data.filter(showtime => showtime.movie_id === parseInt(id));
          setShowtimes(movieShowtimes);

          const theatreIds = new Set(movieShowtimes.map(showtime => showtime.theatre_id));
          fetch('http://127.0.0.1:5000/Theatres', {
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
            .then(data => {
              const filteredTheatres = data.filter(theatre => theatreIds.has(theatre.id));
              setTheatres(filteredTheatres);
            })
            .catch(error => console.log(error));
        })
        .catch(error => console.log(error));
    }
  }, [movie, id]);

  const handleTheatreChange = (event) => {
    const theatreId = event.target.value;
    setSelectedTheatreState(theatreId);
    handleSelectTheatre(theatreId);
    setSelectedShowtime('');
  };

  const handleShowtimeChange = (event) => {
    setSelectedShowtime(event.target.value);
    handleSelectShowtime(event.target.value);
  };

  const handleGetTicketsClick = () => {
    if (selectedTheatreState && selectedShowtime) {
      handleGetTickets(selectedTheatreState, selectedShowtime);
    } else {
      alert('Please select a theatre and showtime.');
    }
    navigate(`/seats/${selectedShowtime}`);
  };

  useEffect(() => {
    if (selectedTheatre) {
      setSelectedTheatreState(selectedTheatre);
    }
  }, [selectedTheatre]);

  if (!movie) {
    return <div>Loading...</div>;
  }

  return (
    <div className="movie-page">
        <Header />
      <div className="movie-poster">
        <img src={movie.posterUrl} alt={movie.title} />
      </div>
      <div className="movie-details">
        <h1>{movie.title}</h1>
        <p>Duration: {movie.duration} minutes</p>
        <p>{movie.description}</p>
        <p>Rating: {movie.rating}</p>
      </div>
      <div className="selection-lists">
        <div className="theatre-list">
          <select value={selectedTheatreState} onChange={handleTheatreChange}>
            <option value="">Select a Theatre</option>
            {theatres.map((theatre) => (
              <option key={theatre.id} value={theatre.id}>
                {theatre.name} - {theatre.location}
              </option>
            ))}
          </select>
        </div>
        <div className="showtime-list">
          <select value={selectedShowtime} onChange={handleShowtimeChange} disabled={!selectedTheatreState}>
            <option value="">Select a Showtime</option>
            {showtimes.map((showtime) => (
              <option key={showtime.id} value={showtime.id}>
                {new Date(showtime.time).toLocaleString()}
              </option>
            ))}
          </select>
        </div>
      </div>
      <button onClick={handleGetTicketsClick} disabled={!selectedTheatreState || !selectedShowtime}>
        Get Tickets
      </button>
      <Footer />
    </div>
  );
};

export default MoviePage;
