import React, { useState } from 'react';

const CancelTicketPage = () => {
  const [showtimeId, setShowtimeId] = useState('');
  const [seatName, setSeatName] = useState('');
  const [message, setMessage] = useState('');

  const handleShowtimeIdChange = (event) => {
    setShowtimeId(event.target.value);
  };

  const handleSeatNameChange = (event) => {
    setSeatName(event.target.value);
  };

  const handleCancelTicket = () => {
    fetch(`http://127.0.0.1:5000/CancelTicket`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ showtimeId, seatName })
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          setMessage('Ticket cancelled successfully.');
        } else {
          setMessage('Failed to cancel ticket.');
        }
      })
      .catch(error => {
        console.error('Cancel ticket error:', error);
        setMessage('Failed to cancel ticket.');
      });
  };

  return (
    <div className="cancel-ticket-page">
      <h1>Cancel Ticket</h1>
      <div>
        <label>
          Showtime ID:
          <input type="text" value={showtimeId} onChange={handleShowtimeIdChange} />
        </label>
      </div>
      <div>
        <label>
          Seat Name:
          <input type="text" value={seatName} onChange={handleSeatNameChange} />
        </label>
      </div>
      <button onClick={handleCancelTicket}>Cancel Ticket</button>
      {message && <div>{message}</div>}
    </div>
  );
};

export default CancelTicketPage;
