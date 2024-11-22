import "./Styles.css";
import Header from './Header';
import Footer from './Footer';
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const SeatMap = () => {
  const { showtimeId } = useParams();
  const navigate = useNavigate();
  const [seatData, setSeatData] = useState(null);
  const [selectedSeat, setSelectedSeat] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`http://127.0.0.1:5000/Seats/${showtimeId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => setSeatData(data))
      .catch(error => {
        console.error('Fetch error:', error);
        setError(error.message);
      });
  }, [showtimeId]);

  const handleSeatClick = (seatName) => {
    setSelectedSeat(seatName);
  };

  const handleProceedToPayment = () => {
    if (selectedSeat) {
      navigate(`/payment/${showtimeId}/${selectedSeat}`);
    } else {
      alert('Please select a seat.');
    }
  };

  if (error) {
    return <div>Error: {error}</div>;
  }

  if (!seatData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
    <Header />
    <div className="seat-map">
      <h1>Select Your Seat</h1>
      <div className="seat-grid" style={{ gridTemplateColumns: `repeat(${seatData.num_seats}, 1fr)` }}>
        {seatData.seats.map((seat, index) => (
          <div
            key={index}
            className={`seat ${selectedSeat === seat.name ? 'selected' : ''}`}
            onClick={() => handleSeatClick(seat.name)}
          >
            {seat.name}
          </div>
        ))}
      </div>
      {selectedSeat && <div>Selected Seat: {selectedSeat}</div>}
      <button onClick={handleProceedToPayment}>Proceed to Payment</button>
    </div>
    <Footer />
    </div>
  );
};

export default SeatMap;
