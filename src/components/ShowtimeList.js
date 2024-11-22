import React, { useState, useEffect } from 'react';

const ShowtimeList = ({ selectedMovie, onSelectShowtime }) => {
  const [showtimes, setShowtimes] = useState([]);
  const [selectedShowtime, setSelectedShowtime] = useState('');

  useEffect(() => {
    if (selectedMovie) {
      fetch(`http://127.0.0.1:5000/Showtimes`, {
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
          const movieShowtimes = data.filter(showtime => showtime.movie_id === parseInt(selectedMovie));
          setShowtimes(movieShowtimes);
        })
        .catch(error => console.log(error));
    }
  }, [selectedMovie]);

  const handleChange = (event) => {
    const showtimeId = event.target.value;
    setSelectedShowtime(showtimeId);
    onSelectShowtime(showtimeId);
  };

  return (
    <div className="showtime-list">
      <select value={selectedShowtime} onChange={handleChange}>
        <option value="">Select a Showtime</option>
        {showtimes.map((showtime) => (
          <option key={showtime.id} value={showtime.id}>
            {new Date(showtime.time).toLocaleString()}
          </option>
        ))}
      </select>
    </div>
  );
};

export default ShowtimeList;
