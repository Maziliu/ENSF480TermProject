import React, { useState, useEffect } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ManageUsers from '../components/ManageUsers'
import ManageMovies from '../components/ManageMovies';
import ManageTheatres from '../components/ManageTheatres';
import ManageShowtimes from '../components/ManageShowtimes';
//NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
//ALSO NEED TO FIX SHOWTIME ADD/UPDATE/DELETE and add delete option for all thingies
const AdminPage = () => {
  const [selectedTab, setSelectedTab] = useState('');

  return (
    <div>
      <Header />
      <h1>Admin Dashboard</h1>
      <button onClick={() => setSelectedTab('manageUsers')}>Manage Users</button>
      <button onClick={() => setSelectedTab('theatres')}>Manage Theatres</button>
      <button onClick={() => setSelectedTab('movies')}>Manage Movies</button>
      <button onClick={() => setSelectedTab('showtimes')}>Manage Showtimes</button>

      {selectedTab === 'manageUsers' && (
        <ManageUsers />
      )}

      {selectedTab === 'theatres' && (
        <ManageTheatres />
      )}

      {selectedTab === 'movies' && (
        <div>
          <ManageMovies />
        </div>
      )}

      {selectedTab === 'showtimes' && (
        <ManageShowtimes />
      )}
      <Footer />
    </div>
  );
};

export default AdminPage;
