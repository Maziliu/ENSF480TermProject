import React, { useState, useEffect } from 'react';
import { fetchTheatres } from '../services/api';
import { useSelectionContext } from '../contexts/SelectionContext';
import '../styles/TheaterList.css';

const TheatreList = () => {
  const [theatres, setTheatres] = useState([]);
  const { selectedTheatre, handleSelectTheatre } = useSelectionContext();

  useEffect(() => {
    fetchTheatres()
      .then((data) => setTheatres(data))
      .catch((error) => console.error('Error fetching theatres:', error));
  }, []);

  const handleChange = (event) => {
    const selectedId = event.target.key;
    const selectedName = event.target.value;
    handleSelectTheatre(selectedId, selectedName);
  };

  return (
    <div className="theatre-list">
      <select value={selectedTheatre} onChange={handleChange}>
        <option value="">Select a Theatre</option>
        {theatres.map((theatre) => (
          <option key={theatre.theatreId} value={theatre.theatreName}>
            {theatre.theatreName}
          </option>
        ))}
      </select>
    </div>
  );
};

export default TheatreList;
