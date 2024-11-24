import React, { useState, useEffect } from 'react';
import { fetchTheatres } from '../services/api';

const TheatreList = ({ onSelectTheatre }) => {
  const [theatres, setTheatres] = useState([]);
  const [selectedTheatre, setSelectedTheatre] = useState('');

  useEffect(() => {
    fetchTheatres()
      .then((data) => {
        console.log('Fetched theatres:', data);
        setTheatres(data);
      })
      .catch((error) => console.error('Error fetching theatres:', error));
  }, []);

  const handleChange = (event) => {
    const theatreId = event.target.value;
    setSelectedTheatre(theatreId);
    onSelectTheatre(theatreId);
  };

  return (
    <div className="theatre-list">
      <select value={selectedTheatre} onChange={handleChange}>
        <option value="">Select a Theatre</option>
        {theatres.map((theatre, index) => (
          <option key={theatre.theatre_id || `theatre-${index}`} value={theatre.theatre_id}>
            {theatre.theatre_name}
          </option>
        ))}
      </select>
    </div>
  );
};

export default TheatreList;
