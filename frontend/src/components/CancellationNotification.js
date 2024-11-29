import React, { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';
import { useNavigate } from 'react-router-dom';
import '../styles/Notification.css';
import '../styles/CancellationNotification.css';

const CancellationNotification = ({ refundData, userIsRegistered = false }) => {
  const { handleSelectMovie } = useSelectionContext();
  const [showPopup, setShowPopup] = useState(true);
  const navigate = useNavigate();

  // Example cancellation data
  const cancellationData = {
    movie: "The Avengers: Endgame",
    theatre: "Downtown Cinema",
    seatName: "A12",
    showtime: {
      time: "2024-11-28T19:30:00Z" // ISO format time string
    },
    bookingId: 342934873486,
    refundedAmount: 13.65, // Amount refunded after cancellation
    discountCode: 'THEATRE25', // Discount code for future use
  };

  useEffect(() => {
    if (cancellationData && cancellationData.movie) {
      setShowPopup(true);
    }
  }, [cancellationData]);

  const handleClose = () => {
    setShowPopup(false);
    navigate('/');
  };

  const handlePrint = () => {
    window.print();
  };

  return (
    <>
      {showPopup && (
        <div className="notification-popup">
          <div className="popup-content">
            <h4>Your booking has been successfully cancelled</h4>
            <button className="close-btn" onClick={handleClose}>
              <span className="close-icon">×</span>
            </button>
            <div className="ticket">
              <h4 className="refund-header">Cancellation Details</h4>
              <div className="refund-line">
                <b>Movie:</b>
                <span>{cancellationData.movie || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Theatre:</b>
                <span>{cancellationData.theatre || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Seat:</b>
                <span>{cancellationData.seatName || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Showtime:</b>
                <span>{cancellationData.showtime.time ? new Date(cancellationData.showtime.time).toLocaleString() : 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Booking ID:</b>
                <span>{cancellationData.bookingId ? cancellationData.bookingId : 'N/A'}</span>
              </div>
            </div>
            <div className="refund">
              <h4 className="refund-header">Refund Details</h4>
              <div className="refund-line">
                <b>Refunded Amount:</b>
                <span>${cancellationData.refundedAmount ? cancellationData.refundedAmount.toFixed(2) : 'N/A'}</span>
              </div>
            </div>
            {userIsRegistered ? (
              <div className="refund-line">
                <b>Thank you for being a valued member!</b>
              </div>
            ) : (
              <div className="unregistered-refund">
                <h4 className="offer-header">Special Offer</h4>
                <div className="refund-line">
                  <b>Use this code for future purchases (expiry date: {new Date(new Date().setFullYear(new Date().getFullYear() + 1)).toLocaleDateString()}):</b>
                  <span>{cancellationData.discountCode}</span>
                </div>
              </div>
            )}
            <button className="print-btn" onClick={handlePrint}>
              Print Cancellation Details
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default CancellationNotification;

