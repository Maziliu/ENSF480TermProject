import React, { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';
import { useNavigate } from 'react-router-dom';
import '../styles/Notification.css';
import '../styles/ReceiptNotification.css';

const ReceiptNotification = ({  }) => {
  const { handleSelectMovie } = useSelectionContext();
  const [showPopup, setShowPopup] = useState(true);
  const navigate = useNavigate();

  //to see notif with example data if payment page is not fully implemented, rename to receiptData and get rid of prop and set showreceiptpopup state to true in payment page
  const receiptData = {
    movie: "The Avengers: Endgame",
    theatre: "Downtown Cinema",
    seatName: "A12",
    showtime: {
      time: "2024-11-28T19:30:00Z" // ISO format time string
    },
    ticketPrice: 15.00,
    discount: 2.00,
    gst: 0.65, // 5% GST on the ticket price
    totalPrice: 13.65, // ticketPrice - discount + gst
    bookingId: 342934873486,
    paymentCard: {
      paymentMethod: "Credit Card",
      cardholderName: "John Doe",
      cardNumber: "1234567812345678",
      expiryDate: "12/25"
    }
  };

  useEffect(() => {
    if (receiptData && receiptData.movie) {
      setShowPopup(true);
    }
  }, [receiptData]);

  const handleClose = () => {
    setShowPopup(false);
  };

  const handlePrint = () => {
    window.print();
  };

  return (
    <>
      {showPopup && (
        <div className="notification-popup">
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
                <span>{receiptData.seatName || 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Showtime:</b>
                <span>{receiptData.showtime.time ? new Date(receiptData.showtime.time).toLocaleString() : 'N/A'}</span>
              </div>
              <div className="receipt-line">
                <b>Booking ID:</b>
                <span>{receiptData.bookingId ? receiptData.bookingId : 'N/A'}</span>
              </div>
              <div className='barcode'>{receiptData.bookingId}</div>
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
                <span>{receiptData.paymentCard.cardholderName || 'N/A'}</span>
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


