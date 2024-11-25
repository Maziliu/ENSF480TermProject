import React, { useState, useEffect } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
//NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
//ALSO NEED TO FIX SHOWTIME ADD/UPDATE/DELETE and add delete option for all thingies
const AdminPage = () => {
  const [users, setUsers] = useState([]);
  const [theatres, setTheatres] = useState([]);
  const [movies, setMovies] = useState([]);
  const [showtimes, setShowtimes] = useState([]);
  const [newTheatre, setNewTheatre] = useState({ name: '', location: '' });
  const [newMovie, setNewMovie] = useState({ title: '', genre: '', duration: '', description: '', rating: '' });
  const [newShowtime, setNewShowtime] = useState({ theatreId: '', movieId: '', date: '', time: '' });
  const [selectedMovie, setSelectedMovie] = useState(null);
  const [selectedTheatre, setSelectedTheatre] = useState(null);
  const [selectedShowtime, setSelectedShowtime] = useState(null);
  const [selectedTab, setSelectedTab] = useState('');
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const usersResponse = await fetch('http://localhost:8080/users');
        const theatresResponse = await fetch('http://locahost:8080/theatres');
        const moviesResponse = await fetch('http://localhost:5000/movies');
        const showtimesResponse = await fetch('http://localhost:5000/showtimes');

        const usersData = await usersResponse.json();
        const theatresData = await theatresResponse.json();
        const moviesData = await moviesResponse.json();
        const showtimesData = await showtimesResponse.json();

        setUsers(usersData);
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
    if (selectedTheatre) {
      setNewTheatre({ name: selectedTheatre.name, location: selectedTheatre.location });
    } else {
      setNewTheatre({ name: '', location: '' });
    }

    if (selectedMovie) {
      setNewMovie({
        title: selectedMovie.title,
        genre: selectedMovie.genre,
        duration: selectedMovie.duration,
        description: selectedMovie.description,
        rating: selectedMovie.rating,
      });
    } else {
      setNewMovie({ title: '', genre: '', duration: '', description: '', rating: '' });
    }

    if (selectedShowtime) {
      setNewShowtime({
        theatreId: selectedShowtime.theatreId,
        movieId: selectedShowtime.movieId,
        date: selectedShowtime.date,
        time: selectedShowtime.time,
      });
    } else {
      setNewShowtime({ theatreId: '', movieId: '', date: '', time: '' });
    }
  }, [selectedTheatre, selectedMovie, selectedShowtime]); // effect when selection changes

  const handleAddOrUpdateTheatre = () => {
    const newTheatreData = { name: newTheatre.name, location: newTheatre.location };
    const method = selectedTheatre ? 'PUT' : 'POST';
    const url = selectedTheatre ? `http://localhost:8080/theatres/update/${selectedTheatre.id}` : 'http://localhost:8080/theatres/add';

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

  const handleAddOrUpdateMovie = () => {
    const newMovieData = {
      title: newMovie.title,
      genre: newMovie.genre,
      duration: newMovie.duration,
      description: newMovie.description,
      rating: newMovie.rating,
    };
    const method = selectedMovie ? 'PUT' : 'POST';
    const url = selectedMovie ? `http://localhost:8080/movies/update/${selectedMovie.id}` : 'http://localhost:8080/movies/add';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newMovieData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          if (selectedMovie) {
            setMovies(
              movies.map(movie => (movie.id === selectedMovie.id ? { ...movie, ...newMovieData } : movie))
            );
            setMessage('Movie updated successfully.');
          } else {
            setMovies([...movies, { id: Date.now(), ...newMovieData }]);
            setMessage('New movie added successfully.');
          }
          setSelectedMovie(null);
          setNewMovie({ title: '', genre: '', duration: '', description: '', rating: '' });
        } else {
          setMessage('Failed to add or update movie.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to add or update movie.');
      });
  };

  const handleAddOrUpdateShowtime = () => {
    const newShowtimeData = {
      theatreId: newShowtime.theatreId,
      movieId: newShowtime.movieId,
      date: newShowtime.date,
      time: newShowtime.time,
    };
    const method = selectedShowtime ? 'PUT' : 'POST';
    const url = selectedShowtime ? `http://localhost:8080/showtimes/update/${selectedShowtime.id}` : 'http://localhost:8080/showtimes/add';

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

  const handleUpdateUserRole = (username, newRole) => {
    fetch(`http://localhost:8080/user/update/${username}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ role: newRole }),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setUsers(
            users.map(user => (user.username === username ? { ...user, role: newRole } : user))
          );
          setMessage(`User role updated to ${newRole}`);
        } else {
          setMessage('Failed to update user role.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to update user role.');
      });
  };

  const handleRemoveUser = (username) => {
    fetch(`http://localhost:8080/user/${username}/unregister`, { method: 'DELETE' })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setUsers(users.filter(user => user.username !== username));
          setMessage('User removed successfully.');
        } else {
          setMessage('Failed to remove user.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to remove user.');
      });
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Header />
      <h1>Admin Dashboard</h1>
      <button onClick={() => setSelectedTab('manageUsers')}>Manage Users</button>
      <button onClick={() => setSelectedTab('theatres')}>Manage Theatres</button>
      <button onClick={() => setSelectedTab('movies')}>Manage Movies</button>
      <button onClick={() => setSelectedTab('showtimes')}>Manage Showtimes</button>

      {selectedTab === 'manageUsers' && (
        <div>
          <h2>Manage Users</h2>
          <ul>
            {users.map(user => (
              <li key={user.username}>
                {user.username} ({user.role})
                <button onClick={() => handleUpdateUserRole(user.username, user.role === 'admin' ? 'user' : 'admin')}>Toggle Role</button>
                <button onClick={() => handleRemoveUser(user.username)}>Remove</button>
              </li>
            ))}
          </ul>
        </div>
      )}

      {selectedTab === 'theatres' && (
        <div>
          <h2>Manage Theatres</h2>
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
          <button onClick={handleAddOrUpdateTheatre}>{selectedTheatre ? 'Update Theatre' : 'Add Theatre'}</button>
          <ul>
            {theatres.map((theatre) => (
              <li key={theatre.id}>
                {theatre.name} - {theatre.location}
                <button onClick={() => setSelectedTheatre(theatre)}>Edit</button>
              </li>
            ))}
          </ul>
        </div>
      )}

      {selectedTab === 'movies' && (
        <div>
          <h2>Manage Movies</h2>
          <input
            type="text"
            placeholder="Movie Title"
            value={newMovie.title}
            onChange={(e) => setNewMovie({ ...newMovie, title: e.target.value })}
          />
          <input
            type="text"
            placeholder="Genre"
            value={newMovie.genre}
            onChange={(e) => setNewMovie({ ...newMovie, genre: e.target.value })}
          />
          <input
            type="text"
            placeholder="Duration"
            value={newMovie.duration}
            onChange={(e) => setNewMovie({ ...newMovie, duration: e.target.value })}
          />
          <textarea
            placeholder="Description"
            value={newMovie.description}
            onChange={(e) => setNewMovie({ ...newMovie, description: e.target.value })}
          />
          <input
            type="text"
            placeholder="Rating"
            value={newMovie.rating}
            onChange={(e) => setNewMovie({ ...newMovie, rating: e.target.value })}
          />
          <button onClick={handleAddOrUpdateMovie}>{selectedMovie ? 'Update Movie' : 'Add Movie'}</button>
          <ul>
            {movies.map((movie) => (
              <li key={movie.id}>
                {movie.title} - {movie.genre}
                <button onClick={() => setSelectedMovie(movie)}>Edit</button>
              </li>
            ))}
          </ul>
        </div>
      )}

      {selectedTab === 'showtimes' && (
        <div>
          <h2>Manage Showtimes</h2>
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
          <select
            value={newShowtime.movieId}
            onChange={(e) => setNewShowtime({ ...newShowtime, movieId: e.target.value })}
          >
            <option value="">Select Movie</option>
            {movies.map((movie) => (
              <option key={movie.id} value={movie.id}>
                {movie.title}
              </option>
            ))}
          </select>
          <input
            type="date"
            value={newShowtime.date}
            onChange={(e) => setNewShowtime({ ...newShowtime, date: e.target.value })}
          />
          <input
            type="time"
            value={newShowtime.time}
            onChange={(e) => setNewShowtime({ ...newShowtime, time: e.target.value })}
          />
          <button onClick={handleAddOrUpdateShowtime}>{selectedShowtime ? 'Update Showtime' : 'Add Showtime'}</button>
          <ul>
            {showtimes.map((showtime) => (
              <li key={showtime.id}>
                {showtime.theatreId} - {showtime.movieId} - {showtime.date} {showtime.time}
                <button onClick={() => setSelectedShowtime(showtime)}>Edit</button>
              </li>
            ))}
          </ul>
        </div>
      )}

      <div>{message}</div>
      <Footer />
    </div>
  );
};

export default AdminPage;
