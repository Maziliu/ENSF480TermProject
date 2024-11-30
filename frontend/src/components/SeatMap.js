import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from './Header';
import Navigation from '../components/Navigation';
import Footer from './Footer';
import { fetchSeatData } from '../services/api';
import '../styles/SeatMap.css';
import { useSelectionContext } from '../contexts/SelectionContext';

const SeatMap = () => {
  const { showtimeId } = useParams();
  const navigate = useNavigate();
  const [seatData, setSeatData] = useState(null);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [error, setError] = useState(null);
  const { selectedMovie} = useSelectionContext();

  useEffect(() => { 
    const loadSeatData = async () => { 
      try { 
        const data = await fetchSeatData(showtimeId); 
        if (!selectedMovie.isReleased){
          const totalSeats = data.reduce((sum, row) => sum + row.length, 0); 
          const reservedSeats = data.reduce((sum, row) => sum + row.filter(isReserved => isReserved).length, 0);
          if (reservedSeats >= 0.1 * totalSeats) { 
            navigate('/');
            alert('More than 10% of seats are already reserved. Returning to Homepage...'); 
          }
        }
        setSeatData(data);
      } catch (error) { 
        console.error('Fetch error:', error); 
        setError(error.message); 
      } }; 
        loadSeatData(); 
      }, [])

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
      <Navigation />
      <div className="seat-map">
        <h1>Select Your Seat</h1>
        <div className='screen'>Screen</div>
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
          <div className='selected-seat-name'>
            <b>Selected Seat:</b> {selectedSeat.row + 1}-{selectedSeat.column + 1}
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
