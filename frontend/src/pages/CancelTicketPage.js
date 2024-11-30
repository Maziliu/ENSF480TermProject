import React, { useState } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import CancellationNotification from '../components/CancellationNotification';
import '../styles/CancelTicketPage.css';

//not sure completely how we want to implement this either
const CancelTicketPage = () => {
  const [transactionId, setTransactionId] = useState('');
  const [message, setMessage] = useState('');
  const [refundData, setRefundData] = useState([]);
  const [userEmail, setUserEmail] = useState('');
  const [showRefundPopup, setShowRefundPopup] = useState(false);

  const handleCancelTicket = () => {
    if (!userEmail || !transactionId){
      setMessage("All fields must be filled.");
      return;
    }
    const refundData = {
      userEmail,
      transactionId
    }

    fetch(`http://localhost:8080/transaction/refund`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(refundData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("refund: ", data);
        if (data.refundStatus === "SUCCESS") {
          setMessage('Ticket cancelled successfully.');
          setRefundData(data);
          setShowRefundPopup(true);
        } else {
          setMessage('Failed to cancel ticket. ', data.refundStatus);
        }
      })
      .catch(error => {
        console.error('Cancel ticket error:', error);
        setMessage('Failed to cancel ticket.');
      });
  };

  return (
    <div>
      <Header />
      {showRefundPopup && <CancellationNotification refundData={refundData}/>}
        <h1 className='cancel-ticket-page-header'>Cancel Ticket</h1>
        {message && <div className='cancel-ticket-page-error'>{message}</div>}
        <div className="cancel-ticket-page">
          <label>Email</label>
          <input
            type="text"
            value={userEmail}
            onChange={(e)=>setUserEmail(e.target.value)}
          /><br/>
          <label>Booking ID</label>
          <input
            type="text"
            value={transactionId}
            onChange={(e)=>setTransactionId(e.target.value)}
          /><br/>
        </div>
        <button className='cancel-ticket-page-buttons' onClick={handleCancelTicket}>Cancel Ticket</button>
      <Footer />
    </div>
  );
};

export default CancelTicketPage;
