import React, { useState, useEffect } from 'react';
import { useSelectionContext } from '../../contexts/SelectionContext';
import { useNavigate } from 'react-router-dom';
import '../../styles/Notification.css';
import '../../styles/ReceiptNotification.css';

const ReceiptNotification = ({ receiptData }) => {
  const { handleSelectMovie } = useSelectionContext();
  const [showPopup, setShowPopup] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (receiptData && receiptData.movie) {
      setShowPopup(true);
    }
  }, [receiptData]);

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
        <div className="notification-popup" id='receipt'>
          <div className="popup-content">
            <h4>Thank you for your purchase!</h4>
            <button className="close-btn" onClick={handleClose}>
              <span className="close-icon">×</span>
            </button>
            <div className="ticket">
              <h4 className="receipt-header">Ticket</h4>
              <div className="receipt-line">
                <b>Movie:</b>
                <span>{receiptData.movie || 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Theatre:</b>
                <span>{receiptData.theatre || 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Seat:</b>
                <span>{`${receiptData.seatPosition.row+1}-${receiptData.seatPosition.column+1}` || 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Showtime:</b>
                <span>{receiptData.showtime.time ? new Date(receiptData.showtime.time).toLocaleString() : 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Booking ID:</b>
                <span className="booking-id" onClick={() => {navigator.clipboard.writeText(receiptData.bookingId)}}>{receiptData.bookingId ? receiptData.bookingId : 'N/A'}</span>
              </div>
              <div className='barcode'>{receiptData.bookingId.substring(0,23)}</div>
            </div>
            <div className="receipt">
              <h4 className="receipt-header">Receipt</h4>
              <div className="receipt-line">
                <b>Ticket Price:</b>
                <span>${receiptData.ticketPrice ? receiptData.ticketPrice.toFixed(2) : 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Savings:</b>
                <span>${receiptData.discount ? receiptData.discount.toFixed(2) : '0.00'}</span>
              </div>
              <div className="receipt-line">
                <b>GST (5%):</b>
                <span>${receiptData.gst ? receiptData.gst.toFixed(2) : 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Total Price:</b>
                <span>${receiptData.totalPrice ? receiptData.totalPrice.toFixed(2) : 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Cardholder Name:</b>
                <span>{receiptData.paymentCard.cardHolderName || 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Card Number:</b>
                <span>{receiptData.paymentCard.cardNumber ? `**** **** **** ${receiptData.paymentCard.cardNumber.slice(-4)}` : 'N/A'}</span>
              </div>
            </div>
            <button className="print-btn" onClick={handlePrint}>
              Print Receipt
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default ReceiptNotification;


