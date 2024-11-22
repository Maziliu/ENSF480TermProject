import Header from './Header';
import Footer from './Footer';
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const ReceiptPage = () => {
  const { showtimeId, seatName } = useParams();
  const [receiptData, setReceiptData] = useState(null);

  useEffect(() => {
    fetch(`http://127.0.0.1:5000/Receipt/${showtimeId}/${seatName}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
      .then(response => response.json())
      .then(data => setReceiptData(data))
      .catch(error => console.error('Fetch error:', error));
  }, [showtimeId, seatName]);

  if (!receiptData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
    <Header />
    <div className="receipt-page">
      <h1>Receipt</h1>
      <div>Movie: {receiptData.movie || 'N/A'}</div>
      <div>Theatre: {receiptData.theatre || 'N/A'}</div>
      <div>Seat: {receiptData.seatName || 'N/A'}</div>
      <div>Showtime: {receiptData.showtime ? new Date(receiptData.showtime.time).toLocaleString() : 'N/A'}</div>
      <div>Ticket Price: ${receiptData.ticketPrice ? receiptData.ticketPrice.toFixed(2) : 'N/A'}</div>
      <div>Savings: ${receiptData.discount ? receiptData.discount.toFixed(2) : 'N/A'}</div>
      <div>GST (5%): ${receiptData.gst ? receiptData.gst.toFixed(2) : 'N/A'}</div>
      <div>Total Price: ${receiptData.totalPrice ? receiptData.totalPrice.toFixed(2) : 'N/A'}</div>
      <div>Payment Method: {receiptData.paymentMethod || 'N/A'}</div>
      <div>Cardholder Name: {receiptData.cardholderName || 'N/A'}</div>
      <div>Card Number: {receiptData.cardNumber ? `**** **** **** ${receiptData.cardNumber.slice(-4)}` : 'N/A'}</div>
      <div>Expiration Date: {receiptData.expirationDate || 'N/A'}</div>
    </div>
    <Footer />
    </div>
  );
};

export default ReceiptPage;
