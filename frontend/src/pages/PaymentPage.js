import Header from '../components/Header.js';
import Footer from '../components/Footer.js';
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext.js';
import { useSelectionContext } from '../contexts/SelectionContext.js';
//STILL HAS SOME ISSUES TO RESOLVE
const PaymentPage = () => {
  const { showtimeId, seatName } = useParams();
  const navigate = useNavigate();
  const { authenticated, role, userId } = useAuthContext();
  const { selectedTheatre, selectedTheatreName, selectedMovie, selectedMovieName, selectedShowtime, selectedShowtimeTime } = useSelectionContext();
  const [ticketPrice, setTicketPrice] = useState(10); //example ticket price
  const [gst, setGst] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const [promoCode, setPromoCode] = useState('');
  const [discount, setDiscount] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState('credit');
  const [cardholderName, setCardholderName] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [cvv, setCvv] = useState('');
  const [expiryDate, setExpiryDate] = useState('');
  const [selectedCard, setSelectedCard] = useState('');
  const [savedCards, setSavedCards] = useState([]); 
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [showCardFields, setShowCardFields] = useState(false);
  const [paymentData, setPaymentData] = useState([]);

  useEffect(() => {
    const calculatedGst = (ticketPrice - discount) * 0.05;
    setGst(calculatedGst);
    setTotalPrice(ticketPrice + calculatedGst);
  }, [ticketPrice, discount]);

  useEffect(() => {
    if (role === 'user') {
      // fetch saved cards and user details if 'user'
      fetch(`http://localhost:8080/user/${userId}/details`)  //fetch user email and address
        .then(response => {
          if (!response.ok) {
            throw new Error('User details not found');
          }
          return response.json();
        })
        .then(data => {
          setEmail(data.email);
          setAddress(data.address);
          setSavedCards(data.savedCards);
        })
        .catch(error => console.error('Error fetching user details:', error));
    }
  }, [authenticated, userId, role]);

  const handleApplyPromoCode = () => {
    fetch(`http://127.0.0.1:8080/promoCode/${promoCode}`, {
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

  const handlePayment = () => {
    //validate email and address for all users
    if (!email || !address) {
      alert('Please enter your email and address.');
      return;
    }

    //validate card fields if 'new' payment method is selected
    if (!selectedCard && (!paymentMethod || !cardholderName || !cardNumber || !cvv || !expiryDate)) {
      alert('Please fill in all required payment fields.');
      return;
    }

    //validate cvv
    if (!selectedCard && !/^\d{3}$/.test(cvv)) {
      alert('Please enter a valid 3-digit cvv.');
      return;
    }

    //validate expiry date
    if (!selectedCard && !/^(0[1-9]|1[0-2])\/\d{2}$/.test(expiryDate)) {
      alert('Please enter a valid expiry date in MM/YY format.');
      return;
    }

    const [row, column] = seatName.split('-').map(num => Number(num) - 1);
    const selectedSeat = {
        row,
        column,
    }

    const paymentCard = {
      paymentMethod,
      cardholderName,
      cardNumber,
      cvv,
      expiryDate,
    }

    const paymentData = {
      selectedShowtime,
      selectedSeat,
      ticketPrice,
      gst,
      totalPrice,
      email,
      address,
      selectedMovie,
      selectedTheatre,
      discount,
      paymentCard,
      //selectedCard, // Add selected card information
    };

    console.log(paymentData);

    fetch('http://localhost:8080/transaction/purchase', {
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
        navigate(`/receipt/${showtimeId}/${selectedSeat.row}-${selectedSeat.column}`, { state: { paymentData: paymentData }});
      })
      .catch(error => {
        console.error('Payment error:', error);
        alert('Payment failed');
      });
  };

  const handleNewCardSelection = () => {
    setShowCardFields(true);
    setSelectedCard(''); 
    setPaymentMethod('');
    setCardholderName('');
    setCardNumber('');
    setCvv('');
    setExpiryDate('');
  };

  const handleSelectedSavedCard = (card) => {
    console.log(card);
    setSelectedCard(card.id);
    setShowCardFields(false);
    setPaymentMethod(card.paymentMethod);
    setCardholderName(card.cardholderName);
    setCardNumber(card.cardNumber);
    setCvv(card.cvv);
    setExpiryDate(card.expiryDate);
  }

  return (
    <div>
      <Header />
      <div className="payment-page">
        <h1>Payment Page</h1>
        <div>Movie: {selectedMovieName}</div>
        <div>Theatre: {selectedTheatreName}</div>
        <div>Showtime: {new Date(selectedShowtimeTime).toLocaleString()}</div>
        <div>Seat: {seatName}</div>
        <div>Ticket Price: ${ticketPrice.toFixed(2)}</div>
        <div>Savings: ${discount.toFixed(2)}</div>
        <div>GST (5%): ${gst.toFixed(2)}</div>
        <div>Total Price: ${totalPrice.toFixed(2)}</div>

        {/* email and address fields */}
        <div>
        <label>Email: </label>
          <input type="text" value={email} onChange={(e)=> setEmail(e.target.value)} placeholder='Enter Email Address'/> <br/>
        <label>Address: </label>
          <input type="text" value={address} onChange={(e)=>setAddress(e.target.value)} placeholder='Enter Address' required />
        </div>

        {/* displaying different card selection options based on the role */}
        {role === 'user' && (  // only show the Add New Payment Card option for 'user' role
          <div>
            <label>
              <input
                type="radio"
                name="paymentCard"
                value="newCard"
                checked={showCardFields}
                onChange={handleNewCardSelection}
              />
              Add New Payment Card
            </label>
          </div>
        )}

        {/* displaying saved cards if authenticated and role is 'user' */}
        {role === 'user' && savedCards.length > 0 && (
          <div>
            <label>Choose a Saved Payment Card:</label>
            {savedCards.map((card) => (
              <div key={card.id}>
                <label>
                  <input
                    type="radio"
                    name="paymentCard"
                    value={card.id}
                    checked={selectedCard === card.id}
                    onChange={() => handleSelectedSavedCard(card)}
                  />
                  {card.cardholderName} - {card.cardNumber.slice(-4)}  {/* show last 4 digits */}
                </label>
              </div>
            ))}
          </div>
        )}

        {/* show the new payment card fields if "Add New Payment Card" is selected */}
        {(role === 'guest' || showCardFields) && (
          <div>
            <div className="payment-method-list"><select  onChange={(e)=>setPaymentMethod(e.target.value)}>
              <option value="">Select a Payment Method</option>
              <option value="credit">Credit</option>
              <option value="debit">Debit</option>
            </select></div>
            <label>Cardholder Name: </label>
                <input type="text" value={cardholderName} onChange={(e)=>setCardholderName(e.target.value)} placeholder='Enter Cardholder Name' required /> <br/>
            <label>Card Number: </label>
                <input type="text" value={cardNumber} onChange={(e)=>setCardNumber(e.target.value)} placeholder='Enter Card Number' required /> <br/>
            <label>cvv: </label>
                <input type="text" value={cvv} onChange={(e)=>setCvv(e.target.value)} placeholder='Enter CVV'required /> <br/>
            <label>Expiry Date (MM/YY): </label>
                <input type="text" value={expiryDate} onChange={(e)=>setExpiryDate(e.target.value)} placeholder='Enter Expiry Date' required /> <br/>
          </div>
        )}

        <div>
          <label>
            Promo Code:
            <input type="text" value={promoCode} onChange={(e)=>setPromoCode(e.target.value)} />
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
