import React, { useState, useEffect } from 'react';

const TheatreList = ({ onSelectTheatre }) => {
  const [theatres, setTheatres] = useState([]);
  const [selectedTheatre, setSelectedTheatre] = useState('');

  useEffect(() => {
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
      .then(data => setTheatres(data))
      .catch(error => console.log(error));
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
        {theatres.map((theatre) => (
          <option key={theatre.id} value={theatre.id}>
            {theatre.name}
          </option>
        ))}
      </select>
    </div>
  );
};

export default TheatreList;

