import React, { useState, useEffect } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import '../styles/ManageTheatres.css';

//NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
//ALSO NEED TO FIX SHOWTIME ADD/UPDATE/DELETE and add delete option for all thingies
const ManageTheatres = () => {
  const [theatres, setTheatres] = useState([]);
  const [newTheatre, setNewTheatre] = useState({ name: '', location: '' });
  const [selectedTheatre, setSelectedTheatre] = useState(null);
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const theatresResponse = await fetch('http://localhost:5000/Theatres');
        const theatresData = await theatresResponse.json();
        setTheatres(theatresData);

      } catch (error) {
        console.error('Error fetching data:', error);
        setMessage('Failed to load data.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []); // run only once(twice, really)

  useEffect(() => {
    // prefill form logic
    if (selectedTheatre) {
      setNewTheatre({ name: selectedTheatre.name, location: selectedTheatre.location });
    } else {
      setNewTheatre({ name: '', location: '' });
    }
  }, [selectedTheatre]); // effect when selection changes

  const handleAddOrUpdateTheatre = () => {
    const newTheatreData = { name: newTheatre.name, location: newTheatre.location };
    console.log("sending: ", newTheatreData);
    const method = selectedTheatre ? 'PUT' : 'POST';
    const url = selectedTheatre ? `http://localhost:5000/Theatres/Update/${selectedTheatre.id}` : 'http://localhost:5000/Theatres/Add';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newTheatreData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          if (selectedTheatre) {
            setTheatres(
              theatres.map(theatre => (theatre.id === selectedTheatre.id ? { ...theatre, ...newTheatreData } : theatre))
            );
            setMessage('Theatre updated successfully.');
          } else {
            setTheatres([...theatres, { id: Date.now(), ...newTheatreData }]);
            setMessage('New theatre added successfully.');
          }
          setSelectedTheatre(null);
          setNewTheatre({ name: '', location: '' });
        } else {
          setMessage('Failed to add or update theatre.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to add or update theatre.');
      });
  };

  const handleRemoveTheatre = (theatreId) =>{
    fetch(`http://localhost:5000/Theatres/Delete/${theatreId}`, { method: 'DELETE' })
    .then(response => response.json())
    .then(data => {
      if (data.message) {
        setTheatres(theatres.filter(theatre => theatre.id !== theatreId));
        setMessage('Theatre removed successfully.');
      } else {
        setMessage('Failed to remove theatre.');
      }
    })
    .catch(error => {
      console.error('Error:', error);
      setMessage('Failed to remove theatre.');
    })
  }

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div className='manage-theatres'>
        <h2>Manage Theatres</h2>
        <div>{message}</div><br/>
        <input
        type="text"
        placeholder="Theatre Name"
        value={newTheatre.name}
        onChange={(e) => setNewTheatre({ ...newTheatre, name: e.target.value })}
        />
        <input
        type="text"
        placeholder="Location"
        value={newTheatre.location}
        onChange={(e) => setNewTheatre({ ...newTheatre, location: e.target.value })}
        />
        <button className='manage-theatres-buttons' onClick={handleAddOrUpdateTheatre} disabled={!newTheatre.name || !newTheatre.location}>{selectedTheatre ? 'Update Theatre' : 'Add Theatre'}</button>
        <ul>
        {theatres.map((theatre) => (
            <li key={theatre.id}>
            {theatre.name} - {theatre.location}
            <button className='manage-theatres-buttons' onClick={() => setSelectedTheatre(theatre)}>Edit</button>
            <button className='manage-theatres-buttons' onClick={() => handleRemoveTheatre(theatre.id)}>Remove</button>
            </li>
        ))}
        </ul>
    </div>
  );
};

export default ManageTheatres;
