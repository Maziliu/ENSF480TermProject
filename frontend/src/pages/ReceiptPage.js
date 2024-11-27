import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Footer from '../components/Footer';
import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';

const ReceiptPage = () => {
  const { showtimeId, seatName } = useParams();
  const [receiptData, setReceiptData] = useState(null);
  const { state } = useLocation();
  console.log("reciept:", state.paymentData);

  useEffect(() => {
    if (state?.paymentData) {
      setReceiptData(state.paymentData);
    }
  }, [state]);

  if (!receiptData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
    <Header />
    <Navigation />
    <div className="receipt-page">
      <h1>Receipt</h1>
      <div>Movie: {receiptData.movie || 'N/A'}</div>
      <div>Theatre: {receiptData.theatre || 'N/A'}</div>
      <div>Seat: {receiptData.seatName || 'N/A'}</div>
      <div>Showtime: {receiptData.showtime.time ? new Date(receiptData.showtime.time).toLocaleString() : 'N/A'}</div>
      <div>Ticket Price: ${receiptData.ticketPrice ? receiptData.ticketPrice.toFixed(2) : 'N/A'}</div>
      <div>Savings: ${receiptData.discount ? receiptData.discount.toFixed(2) : '0.00'}</div>
      <div>GST (5%): ${receiptData.gst ? receiptData.gst.toFixed(2) : 'N/A'}</div>
      <div>Total Price: ${receiptData.totalPrice ? receiptData.totalPrice.toFixed(2) : 'N/A'}</div>
      <div>Payment Method: {receiptData.paymentCard.paymentMethod || 'N/A'}</div>
      <div>Cardholder Name: {receiptData.paymentCard.cardholderName || 'N/A'}</div>
      <div>Card Number: {receiptData.paymentCard.cardNumber ? `**** **** **** ${receiptData.paymentCard.cardNumber.slice(-4)}` : 'N/A'}</div>
      <div>Expiration Date: {receiptData.paymentCard.expiryDate || 'N/A'}</div>
    </div>
    <Footer />
    </div>
  );
};

export default ReceiptPage;
