import React, { useState, useEffect } from 'react';
import '../../styles/ManageShowtimes.css';
import '../../styles/AdminPage.css';

//NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
const ManageShowtimes = () => {
  const [theatres, setTheatres] = useState([]);
  const [movies, setMovies] = useState([]);
  const [showtimes, setShowtimes] = useState([]);
  const [newShowtime, setNewShowtime] = useState({ theatreId: '', movieId: '', date: '', time: '' });
  const [selectedShowtime, setSelectedShowtime] = useState(null);
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  const formatShowtime =(date, time)=> {
    return `${date}T${time}`;
  }

  const splitDatetime = (datetime)=> {
    const [date, time] = datetime.split('T');
    
    return {
        date: date,// 'YYYY-MM-DD'
        time: time// 'HH:MM:SS'
    };
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const theatresResponse = await fetch('http://localhost:5000/Theatres');
        const moviesResponse = await fetch('http://localhost:5000/Movies');
        const showtimesResponse = await fetch('http://localhost:5000/Showtimes');
        const theatresData = await theatresResponse.json();
        const moviesData = await moviesResponse.json();
        const showtimesData = await showtimesResponse.json();

        setTheatres(theatresData);
        setMovies(moviesData);
        setShowtimes(showtimesData);
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
    if (selectedShowtime) {
      setNewShowtime({
        theatreId: selectedShowtime.theatreId,
        movieId: selectedShowtime.movieId,
        date: splitDatetime(selectedShowtime.time).date,
        time: splitDatetime(selectedShowtime.time).time,
      });
    } else {
      setNewShowtime({ theatreId: '', movieId: '', date: '', time: '' });
    }
  }, [selectedShowtime]); // effect when selection changes

  const handleAddOrUpdateShowtime = () => {
    const newShowtimeData = {
      theatreId: parseInt(newShowtime.theatreId, 10),
      movieId: parseInt(newShowtime.movieId, 10),
      time: formatShowtime(newShowtime.date, newShowtime.time)
    };
    console.log("sending: ", newShowtimeData);

    const method = selectedShowtime ? 'PUT' : 'POST';
    const url = selectedShowtime ? `http://localhost:5000/Showtimes/Update/${selectedShowtime.id}` : 'http://localhost:5000/Showtimes/Add';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newShowtimeData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          if (selectedShowtime) {
            setShowtimes(
              showtimes.map(showtime => (showtime.id === selectedShowtime.id ? { ...showtime, ...newShowtimeData } : showtime))
            );
            setMessage('Showtime updated successfully.');
          } else {
            setShowtimes([...showtimes, { id: Date.now(), ...newShowtimeData }]);
            setMessage('New showtime added successfully.');
          }
          setSelectedShowtime(null);
          setNewShowtime({ theatreId: '', movieId: '', date: '', time: '' });
        } else {
          setMessage('Failed to add or update showtime.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to add or update showtime.');
      });
  };

  const handleRemoveShowtime = (showtimeId) =>{
    fetch(`http://localhost:5000/Showtimes/Delete/${showtimeId}`, { method: 'DELETE' })
    .then(response => response.json())
    .then(data => {
      if (data.message) {
        setShowtimes(showtimes.filter(showtime => showtime.id !== showtimeId));
        setMessage('Showtime removed successfully.');
      } else {
        setMessage('Failed to remove showtime.');
      }
    })
    .catch(error => {
      console.error('Error:', error);
      setMessage('Failed to remove showtime.');
    })
  }

  if (isLoading) {
    return <div className='loading'>Loading...</div>;
  }

  return (
    <div className='manage-showtimes'>
        <h2>Manage Showtimes</h2>
        <div>{message}</div><br/>
        <select
        value={newShowtime.theatreId}
        onChange={(e) => setNewShowtime({ ...newShowtime, theatreId: e.target.value })}
        >
        <option value="">Select Theatre</option>
        {theatres.map((theatre) => (
            <option key={theatre.id} value={theatre.id}>
            {theatre.name}
            </option>
        ))}
        </select>

        <select value={newShowtime.movieId} onChange={(e) => setNewShowtime({ ...newShowtime, movieId: e.target.value })}>
        <option value="">Select Movie</option>
        {movies.map((movie) => (
            <option key={movie.id} value={movie.id}>
            {movie.title}
            </option>
        ))}
        </select>

        <input type="date" value={newShowtime.date} onChange={(e) => setNewShowtime({ ...newShowtime, date: e.target.value })} />
        <input type="time" value={newShowtime.time} onChange={(e) => setNewShowtime({ ...newShowtime, time: e.target.value })} />
        
        <button className='manage-showtimes-buttons' onClick={handleAddOrUpdateShowtime} disabled={!newShowtime.theatreId || !newShowtime.movieId || !newShowtime.date || !newShowtime.time}>
            {selectedShowtime ? 'Update Showtime' : 'Add Showtime'}
        </button>
        
        <ul>
        {showtimes.map((showtime) => (
            <li key={showtime.id}>
            {theatres.find(theatre => theatre.id === showtime.theatreId).name} - {movies.find(movie => movie.id === showtime.movieId).title} - {new Date(showtime.time).toLocaleString()}
            <button className='manage-showtimes-buttons' onClick={() => setSelectedShowtime(showtime)}>Edit</button>
            <button className='manage-showtimes-buttons' onClick={() => handleRemoveShowtime(showtime.id)}>Remove</button>
            </li>
        ))}
        </ul>
    </div>
  );
};

export default ManageShowtimes;
