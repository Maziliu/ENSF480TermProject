import React, { useState } from 'react';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Footer from '../components/Footer';
import CancellationNotification from '../components/CancellationNotification';
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
        if (data.success) {
          setMessage('Ticket cancelled successfully.');
          if (data.refundAmount) {
            const data = {
              refundAmount:data.refundAmount,
              discountCode:data.discountCode,
            }
            setRefundData(data);
            setShowRefundPopup(true);
          }
        } else {
          setMessage('Failed to cancel ticket: ' + data.error);
        }
      })
      .catch(error => {
        console.error('Cancel ticket error:', error);
        setMessage('Failed to cancel ticket.');
      });
  };

  return (
    <div className="cancel-ticket-page">
    <Header />
    <Navigation />
    {showRefundPopup && <CancellationNotification refundData={refundData}/>}
      <h1>Cancel Ticket</h1>
      <div>
        <label>
          Email:
          <input
            type="text"
            value={userEmail}
            onChange={(e)=>setUserEmail(e.target.value)}
          />
        </label> <br/>
        <label>
          Booking ID:
          <input
            type="text"
            value={transactionId}
            onChange={(e)=>setTransactionId(e.target.value)}
          />
        </label>
      </div>
      <button onClick={handleCancelTicket}>Cancel Ticket</button>
      {message && <div>{message}</div>}
    <Footer />
    </div>
  );
};

export default CancelTicketPage;
