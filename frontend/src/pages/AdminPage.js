import React, { useState, useEffect } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ManageUsers from '../components/admincontrol/ManageUsers'
import ManageMovies from '../components/admincontrol/ManageMovies';
import ManageTheatres from '../components/admincontrol/ManageTheatres';
import ManageShowtimes from '../components/admincontrol/ManageShowtimes';
import '../styles/AdminPage.css';

// NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
// ALSO NEED TO FIX SHOWTIME ADD/UPDATE/DELETE and add delete option for all thingies
// ALSO STILL NOT COMPLETLEY SURE HOW WE WILL IMPLEMENT NEW RELEASES FOR RUs
const AdminPage = () => {
  const [selectedTab, setSelectedTab] = useState('');

  return (
    <div>
      <Header />
      <h1 className='admin-header'>Admin Dashboard</h1>
      <div className='admin-page'>
        <button className='admin-button' onClick={() => setSelectedTab('manageUsers')}>Manage Users</button>
        <button className='admin-button' onClick={() => setSelectedTab('theatres')}>Manage Theatres</button>
        <button className='admin-button' onClick={() => setSelectedTab('movies')}>Manage Movies</button>
        <button className='admin-button' onClick={() => setSelectedTab('showtimes')}>Manage Showtimes</button>
      </div>
      
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
