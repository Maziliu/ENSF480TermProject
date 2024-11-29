import React, { useState, useEffect } from 'react';
import { useAuthContext } from '../contexts/AuthContext.js';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Footer from '../components/Footer';
import '../styles/UserAccountPage.css';

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
      fetch(`http://localhost:8080/user/${userId}/details`)
      //fetch(`http://localhost:8080/user/1/details`)
        .then(response => response.json())
        .then(data => {
          if (data.error) {
            setMessage(data.error);
          } else {
            console.log("userdeets:", data);
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
    const updatedData = { 
      ...userDetails,
      savedCards: [...savedCards]
      };

    console.log("updated details: ", updatedData);
  
    if (newCard.cardholderName && newCard.cardNumber && newCard.expiryDate && newCard.cvv) {
          //validate card fields if 'new' payment method is selected
      //validate cvv
      if (!/^\d{3}$/.test(newCard.cvv)) {
        alert('Please enter a valid 3-digit cvv.');
        return;
      }

      //validate expiry date
      if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(newCard.expiryDate)) {
        alert('Please enter a valid expiry date in MM/YY format.');
        return;
      }
      updatedData.savedCards.push(newCard);
    }

    fetch(`http://localhost:8080/user/${userId}/update-details`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: new URLSearchParams(updatedData).toString(),
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

  //handle paying the annual fee
  const handlePayFee = () => {
    fetch(`http://localhost:8080/User/${userId}/PayFee`, {
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
      fetch(`http://localhost:8080/User/${userId}/Unregister`, {
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
      <Navigation />
      <h1 className='user-account-header'>User Account</h1>
      <div className='user-account-page'>
        <table>
          <tr>
            <td className='user-account-input'>
              <h2>Personal Information</h2>
              <br></br>
              <label>First Name</label>
              <input
                type="text"
                value={userDetails.firstName}
                onChange={(e) => setUserDetails({ ...userDetails, firstName: e.target.value })}
              />
              <br></br>
              <label>Last Name</label>
              <input
                type="text"
                value={userDetails.lastName}
                onChange={(e) => setUserDetails({ ...userDetails, lastName: e.target.value })}
              />
              <br></br>
              <label>Email</label>
              <input
                type="email"
                value={userDetails.email}
                onChange={(e) => setUserDetails({ ...userDetails, email: e.target.value })}
              />
              <br></br>
              <label>Address</label>
              <input
                type="text"
                value={userDetails.address}
                onChange={(e) => setUserDetails({ ...userDetails, address: e.target.value })}
              />
              <button className='user-buttons' onClick={handleUpdateDetails}>Update Details</button>
            </td>

            <td>
              <h2>Saved Payment Cards</h2>
                {/* Radio select for existing cards */}
                {savedCards && savedCards.map((card, index) => (
                  <div key={index}>
                    <br></br>
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
                  <br></br>
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
                  <div className='user-account-input'>
                    <h4>Update Card</h4>
                    <br></br>
                    <label>Cardholder Name</label>
                    <input
                      type="text"
                      placeholder="Cardholder Name"
                      value={selectedCard.cardholderName}
                      onChange={(e) => setSelectedCard({ ...selectedCard, cardholderName: e.target.value })}
                    />
                    <br></br>
                    <label>Card Number</label>
                    <input
                      type="text"
                      placeholder="Card Number"
                      value={selectedCard.cardNumber}
                      onChange={(e) => setSelectedCard({ ...selectedCard, cardNumber: e.target.value })}
                    />
                    <br></br>
                    <label>Expiration Date (MM/YY)</label>
                    <input
                      type="text"
                      placeholder="Expiry Date"
                      value={selectedCard.expiryDate}
                      onChange={(e) => setSelectedCard({ ...selectedCard, expiryDate: e.target.value })}
                    />
                    <br></br>
                    <label>CVV</label>
                    <input
                      type="text"
                      placeholder="CVV"
                      value={selectedCard.cvv}
                      onChange={(e) => setSelectedCard({ ...selectedCard, cvv: e.target.value })}
                    />
                    <button onClick={handleUpdateDetails}>Update Card</button>
                  </div>
                )}

                {/* form to add new card */}
                {selectedCard === null && (
                  <div className='user-account-input'>
                    <br></br>
                    <label>Cardholder Name</label>
                    <input
                      type="text"
                      placeholder="Cardholder Name"
                      value={newCard.cardholderName}
                      onChange={(e) => setNewCard({ ...newCard, cardholderName: e.target.value })}
                    />
                    <br></br>
                    <label>Card Number</label>
                    <input
                      type="text"
                      placeholder="Card Number"
                      value={newCard.cardNumber}
                      onChange={(e) => setNewCard({ ...newCard, cardNumber: e.target.value })}
                    />
                    <br></br>
                    <label>Expiration Date (MM/YY)</label>
                    <input
                      type="text"
                      placeholder="Expiry Date"
                      value={newCard.expiryDate}
                      onChange={(e) => setNewCard({ ...newCard, expiryDate: e.target.value })}
                    />
                    <br></br>
                    <label>CVV</label>
                    <input
                      type="text"
                      placeholder="CVV"
                      value={newCard.cvv}
                      onChange={(e) => setNewCard({ ...newCard, cvv: e.target.value })}
                    />
                    <button onClick={handleUpdateDetails}>Add New Card</button>
                  </div>
                )}
            </td>
          </tr>
        </table>
      </div>


      {/* ALSO IDK IF WE WANNA DO SOMETHING LIKE THIS OR JUST DO
      ANNUAL FEE STUFF COMPLETLEY BACKEND OR JUST HAVE "AUTOMATIC BILLING: JAN 12, 2025" ON ACCOUNT PAGE OR SMTHING */}
      {!userDetails.has_paid && (
         <button className='user-account-button' onClick={handlePayFee}>Pay Annual Fee</button>
      )}

        <button className='user-account-button' onClick={handleUnregister}>Unregister Account</button>

      {message && <div>{message}</div>}
      <Footer />
    </div>
  );
};

export default UserAccountPage;
