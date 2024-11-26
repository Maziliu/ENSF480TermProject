import React, { useState, useEffect } from 'react';
import { useAuthContext } from '../contexts/AuthContext.js';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
//IDK IF WE SHOULD ACTUALLY ADD THIS PAGE SO OPTIONAL
//NOT ADJUSTED TO THIS FRONTEND FORMAT AT ALL
const UserAccountPage = () => {
  const [userDetails, setUserDetails] = useState(null);
  const [savedCards, setSavedCards] = useState([]);
  const [newCard, setNewCard] = useState({ cardholderName: '', cardNumber: '', expiryDate: '', cvv: '' });
  const [selectedCard, setSelectedCard] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [message, setMessage] = useState('');
  const { userId, setUserId, setRole } = useAuthContext();

  const navigate = useNavigate();
  //fetch user details and saved cards
  useEffect(() => {
    const fetchUserDetails = () => {
      fetch(`http://127.0.0.1:5000/User/${userId}/Details`)
        .then(response => response.json())
        .then(data => {
          if (data.error) {
            setMessage(data.error);
          } else {
            setUserDetails(data);
            setSavedCards(data.savedCards);
            setIsLoading(false);
          }
        })
        .catch(error => {
          console.error('Error fetching user details:', error);
          setMessage('Failed to load user details.');
        });
    };

    fetchUserDetails();
  }, [userId]);

  //handle updating user details
  const handleUpdateDetails = () => {
    const updatedData = { first_name: userDetails.first_name, last_name: userDetails.last_name, email: userDetails.email, address: userDetails.address };
    fetch(`http://127.0.0.1:5000/User/${userId}/UpdateDetails`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setMessage('User details updated successfully.');
        } else {
          setMessage('Failed to update user details.');
        }
      })
      .catch(error => {
        console.error('Error updating user details:', error);
        setMessage('Failed to update user details.');
      });
  };

  //handle adding a new card
  const handleAddCard = () => {
    if (!newCard.cardholderName || !newCard.cardNumber || !newCard.expiryDate || !newCard.cvv){
      alert('Please fill in all payment information fields.')
      return;
    }
    const cardData = { ...newCard };  // Get the new card details
    fetch(`http://127.0.0.1:5000/User/${userId}/AddCard`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(cardData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setSavedCards([...savedCards, newCard]);
          setMessage('New card added successfully');
        } else {
          setMessage('Failed to add new card.');
        }
      })
      .catch(error => {
        console.error('Error adding new card:', error);
        setMessage('Failed to add new card.');
      });
  };

  //handle updating an existing card
  const handleUpdateCard = () => {
    if (!selectedCard.cardholderName || !selectedCard.cardNumber || !selectedCard.expiryDate || !selectedCard.cvv){
      alert('Please fill in all payment information fields.')
      return;
    }
    const updatedCardData = { ...selectedCard };
    fetch(`http://127.0.0.1:5000/User/${userId}/UpdateCard`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedCardData),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          // Update the saved cards list
          const updatedCards = savedCards.map(card => 
            card.cardNumber === selectedCard.cardNumber ? updatedCardData : card
          );
          setSavedCards(updatedCards);
          setMessage('Card updated successfully');
        } else {
          setMessage('Failed to update card.');
        }
      })
      .catch(error => {
        console.error('Error updating card:', error);
        setMessage('Failed to update card.');
      });
  };

  //handle paying the annual fee
  const handlePayFee = () => {
    fetch(`http://127.0.0.1:5000/User/${userId}/PayFee`, {
      method: 'POST',
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setUserDetails({ ...userDetails, has_paid: true });
          setMessage('Annual fee paid successfully');
        } else {
          setMessage('Failed to pay annual fee.');
        }
      })
      .catch(error => {
        console.error('Error paying annual fee:', error);
        setMessage('Failed to pay annual fee.');
      });
  };

  //handle unregistering the account
  const handleUnregister = () => {
    const confirmUnregister = window.confirm('Are you sure you want to unregister?');
    if (confirmUnregister) {
      fetch(`http://127.0.0.1:5000/User/${userId}/Unregister`, {
        method: 'DELETE',
      })
        .then(response => response.json())
        .then(data => {
          if (data.message) {
            setMessage('Account successfully unregistered');
            localStorage.removeItem('role');
            localStorage.removeItem('userId');
            setRole('guest');
            setUserId('');
            navigate('/')
          } else {
            setMessage('Failed to unregister account.');
          }
        })
        .catch(error => {
          console.error('Error unregistering:', error);
          setMessage('Failed to unregister account.');
        });
    }
  };

  if (isLoading) {
    return <div>Loading...</div>;  //display a loading message while data is being fetched
  }

  if (!userDetails) {
    return <div>No user details available.</div>;  //if userDetails is still null or not set
  }

  return (
    <div>
        <Header />
      <h1>User Account</h1>
      <div>
        <h3>Personal Information</h3>
        <input
          type="text"
          value={userDetails.first_name}
          onChange={(e) => setUserDetails({ ...userDetails, first_name: e.target.value })}
        />
        <input
          type="text"
          value={userDetails.last_name}
          onChange={(e) => setUserDetails({ ...userDetails, last_name: e.target.value })}
        />
        <input
          type="email"
          value={userDetails.email}
          onChange={(e) => setUserDetails({ ...userDetails, email: e.target.value })}
        />
        <input
          type="text"
          value={userDetails.address}
          onChange={(e) => setUserDetails({ ...userDetails, address: e.target.value })}
        />
        <button onClick={handleUpdateDetails}>Update Details</button>
      </div>

      <div>
        <h3>Saved Payment Cards</h3>
        <div>
          {/* Radio select for existing cards */}
          {savedCards.map((card, index) => (
            <div key={index}>
              <input 
                type="radio" 
                name="cardSelect" 
                checked={selectedCard?.cardNumber === card.cardNumber} 
                onChange={() => setSelectedCard(card)} 
              />
              {card.cardholderName} - {card.cardNumber} - {card.expiryDate}
            </div>
          ))}

          {/* option to add new card */}
          <div>
            <input 
              type="radio" 
              name="cardSelect" 
              checked={selectedCard === null} 
              onChange={() => setSelectedCard(null)} 
            />
            Add New Card
          </div>

          {/*show the selected card's details for editing */}
          {selectedCard && selectedCard !== null && (
            <div>
              <h4>Update Card</h4>
              <input
                type="text"
                placeholder="Cardholder Name"
                value={selectedCard.cardholderName}
                onChange={(e) => setSelectedCard({ ...selectedCard, cardholderName: e.target.value })}
              />
              <input
                type="text"
                placeholder="Card Number"
                value={selectedCard.cardNumber}
                onChange={(e) => setSelectedCard({ ...selectedCard, cardNumber: e.target.value })}
              />
              <input
                type="text"
                placeholder="Expiry Date"
                value={selectedCard.expiryDate}
                onChange={(e) => setSelectedCard({ ...selectedCard, expiryDate: e.target.value })}
              />
              <input
                type="text"
                placeholder="CVV"
                value={selectedCard.cvv}
                onChange={(e) => setSelectedCard({ ...selectedCard, cvv: e.target.value })}
              />
              <button onClick={handleUpdateCard}>Update Card</button>
            </div>
          )}

          {/* form to add new card */}
          {selectedCard === null && (
            <div>
              <input
                type="text"
                placeholder="Cardholder Name"
                value={newCard.cardholderName}
                onChange={(e) => setNewCard({ ...newCard, cardholderName: e.target.value })}
              />
              <input
                type="text"
                placeholder="Card Number"
                value={newCard.cardNumber}
                onChange={(e) => setNewCard({ ...newCard, cardNumber: e.target.value })}
              />
              <input
                type="text"
                placeholder="Expiry Date"
                value={newCard.expiryDate}
                onChange={(e) => setNewCard({ ...newCard, expiryDate: e.target.value })}
              />
              <input
                type="text"
                placeholder="CVV"
                value={newCard.cvv}
                onChange={(e) => setNewCard({ ...newCard, cvv: e.target.value })}
              />
              <button onClick={handleAddCard}>Add New Card</button>
            </div>
          )}
        </div>
      </div>

      {!userDetails.has_paid && (
        <div>
          <h3>Pay Annual Fee</h3>
          <button onClick={handlePayFee}>Pay $20.00</button>
        </div>
      )}

      <div>
        <button onClick={handleUnregister}>Unregister Account</button>
      </div>

      {message && <div>{message}</div>}
      <Footer />
    </div>
  );
};

export default UserAccountPage;
