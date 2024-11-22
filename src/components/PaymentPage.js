import Header from './Header';
import Footer from './Footer';
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const PaymentPage = () => {
  const { showtimeId, seatName } = useParams();
  const navigate = useNavigate();
  const [ticketPrice, setTicketPrice] = useState(10); // Example ticket price
  const [gst, setGst] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const [promoCode, setPromoCode] = useState('');
  const [discount, setDiscount] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState('credit');
  const [cardholderName, setCardholderName] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [ccv, setCcv] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [movie, setMovie] = useState('');
  const [theatre, setTheatre] = useState('');
  const [showtime, setShowtime] = useState(null);

  useEffect(() => {
    const calculatedGst = (ticketPrice - discount) * 0.05;
    setGst(calculatedGst);
    setTotalPrice(ticketPrice + calculatedGst);
  }, [ticketPrice, discount]);

  useEffect(() => {
    // Fetch movie and theatre information based on showtimeId
    fetch(`http://127.0.0.1:5000/Showtimes`)
      .then(response => response.json())
      .then(data => {
        const showtime = data.find(st => st.id === parseInt(showtimeId));
        if (showtime) {
          setShowtime(showtime);
          fetch(`http://127.0.0.1:5000/Movies/${showtime.movie_id}`)
            .then(response => response.json())
            .then(movieData => setMovie(movieData.title));
          fetch(`http://127.0.0.1:5000/Theatres`)
            .then(response => response.json())
            .then(theatreData => {
              const theatre = theatreData.find(th => th.id === showtime.theatre_id);
              setTheatre(theatre.name);
            });
        }
      });
  }, [showtimeId]);

  const handlePromoCodeChange = (event) => {
    setPromoCode(event.target.value);
  };

  const handleApplyPromoCode = () => {
    fetch(`http://127.0.0.1:5000/PromoCode/${promoCode}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Invalid promo code');
        }
        return response.json();
      })
      .then(data => {
        setDiscount(data.discount);
      })
      .catch(error => {
        console.error('Promo code error:', error);
        alert('Invalid promo code');
      });
  };

  const handlePaymentMethodChange = (event) => {
    setPaymentMethod(event.target.value);
  };

  const handleCardholderNameChange = (event) => {
    setCardholderName(event.target.value);
  };

  const handleCardNumberChange = (event) => {
    setCardNumber(event.target.value);
  };

  const handleCcvChange = (event) => {
    setCcv(event.target.value);
  };

  const handleExpirationDateChange = (event) => {
    setExpirationDate(event.target.value);
  };

  const handlePayment = () => {
    // Validate fields
    if (!cardholderName || !cardNumber || !ccv || !expirationDate) {
      alert('Please fill in all required fields.');
      return;
    }

    // Validate CCV
    if (!/^\d{3}$/.test(ccv)) {
      alert('Please enter a valid 3-digit CCV.');
      return;
    }

    // Validate expiration date
    if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(expirationDate)) {
      alert('Please enter a valid expiration date in MM/YY format.');
      return;
    }

    const paymentData = {
      showtime,
      seatName,
      ticketPrice,
      gst,
      totalPrice,
      paymentMethod,
      cardholderName,
      cardNumber,
      ccv,
      expirationDate,
      movie,
      theatre,
      discount
    };

    fetch('http://127.0.0.1:5000/Payment', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(paymentData)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Payment failed');
        }
        return response.json();
      })
      .then(data => {
        navigate(`/receipt/${showtimeId}/${seatName}`);
      })
      .catch(error => {
        console.error('Payment error:', error);
        alert('Payment failed');
      });
  };

  return (
    <div>
    <Header />
    <div className="payment-page">
      <h1>Payment Page</h1>
      <div>Movie: {movie}</div>
      <div>Theatre: {theatre}</div>
      <div>Seat: {seatName}</div>
      <div>Ticket Price: ${ticketPrice.toFixed(2)}</div>
      <div>Savings: ${discount.toFixed(2)}</div>
      <div>GST (5%): ${gst.toFixed(2)}</div>
      <div>Total Price: ${totalPrice.toFixed(2)}</div>
      <div>
        <label>
          Payment Method:
          <select value={paymentMethod} onChange={handlePaymentMethodChange}>
            <option value="credit">Credit</option>
            <option value="debit">Debit</option>
          </select>
        </label>
      </div>
      <div>
        <label>
          Cardholder Name:
          <input type="text" value={cardholderName} onChange={handleCardholderNameChange} />
        </label>
      </div>
      <div>
        <label>
          Card Number:
          <input type="text" value={cardNumber} onChange={handleCardNumberChange} />
        </label>
      </div>
      <div>
        <label>
          CCV:
          <input type="text" value={ccv} onChange={handleCcvChange} />
        </label>
      </div>
      <div>
        <label>
          Expiration Date (MM/YY):
          <input type="text" value={expirationDate} onChange={handleExpirationDateChange} />
        </label>
      </div>
      <div>
        <label>
          Promo Code:
          <input type="text" value={promoCode} onChange={handlePromoCodeChange} />
          <button onClick={handleApplyPromoCode}>Apply</button>
        </label>
      </div>
      <button onClick={handlePayment}>Pay Now</button>
    </div>
    <Footer />
    </div>
  );
};

export default PaymentPage;
