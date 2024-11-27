import React, { useState } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
//not sure completely how we want to implement this either
const CancelTicketPage = () => {
  const [cancellationCode, setCancellationCode] = useState('');
  const [message, setMessage] = useState('');
  const [refundAmount, setRefundAmount] = useState(null);
  const [discountCode, setDiscountCode] = useState('');

  const handleCancellationCodeChange = (event) => {
    setCancellationCode(event.target.value);
  };

  const getTransaction = () => {
    fetch(`http://localhost:8080/transaction/${transactionId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          setMessage('Ticket cancelled successfully.');
          if (data.refundAmount) {
            setRefundAmount(data.refundAmount);
            setDiscountCode(data.discountCode);
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
      <h1>Cancel Ticket</h1>
      <div>
        <label>
          Cancellation Code:
          <input
            type="text"
            value={cancellationCode}
            onChange={handleCancellationCodeChange}
          />
        </label>
      </div>
      <button onClick={handleCancelTicket}>Cancel Ticket</button>
      {message && <div>{message}</div>}
      {refundAmount !== null && (
        <div>
          <p>Refund Amount: ${refundAmount}</p>
          {discountCode && <p>Discount Code for Future Purchase: {discountCode}</p>}
        </div>
      )}
    <Footer />
    </div>
  );
};

export default CancelTicketPage;
