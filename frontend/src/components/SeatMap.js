import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from './Header';
import Footer from './Footer';
import { fetchSeatData } from '../services/api';
import '../styles/SeatMap.css';

const SeatMap = () => {
  const { showtimeId } = useParams();
  const navigate = useNavigate();
  const [seatData, setSeatData] = useState(null);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadSeatData = async () => {
      try {
        const data = await fetchSeatData(showtimeId);
        setSeatData(data);
      } catch (error) {
        console.error('Fetch error:', error);
        setError(error.message);
      }
    };

    loadSeatData();
  }, [showtimeId]);

  const handleSeatClick = (row, column) => {
    if (seatData[row][column]) {
      alert('This seat is already reserved.');
      return;
    }
    setSelectedSeat({ row, column });
  };

  const handleProceedToPayment = () => {
    if (selectedSeat) {
      navigate(`/payment/${showtimeId}/${selectedSeat.row + 1}-${selectedSeat.column + 1}`);
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
        <div className="seat-grid">
          {seatData.map((row, rowIndex) => (
            <div key={rowIndex} className="seat-row">
              {row.map((isReserved, columnIndex) => (
                <div
                  key={`${rowIndex}-${columnIndex}`}
                  className={`seat ${isReserved ? 'reserved' : ''} ${selectedSeat?.row === rowIndex && selectedSeat?.column === columnIndex ? 'selected' : ''}`}
                  onClick={() => handleSeatClick(rowIndex, columnIndex)}
                >
                  {isReserved ? 'X' : `${rowIndex + 1}-${columnIndex + 1}`}
                </div>
              ))}
            </div>
          ))}
        </div>
        {selectedSeat && (
          <div>
            Selected Seat: Row {selectedSeat.row + 1}, Column {selectedSeat.column + 1}
          </div>
        )}
        <button onClick={handleProceedToPayment} disabled={!selectedSeat}>
          Proceed to Payment
        </button>
      </div>
      <Footer />
    </div>
  );
};

export default SeatMap;
