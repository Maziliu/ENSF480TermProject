import React, { useState } from 'react';
import LoginForm from './LoginForm';
import '../styles/RegistrationForm.css';

const RegistrationForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [address, setAddress] = useState('');
  const [paymentCardType, setPaymentCardType] = useState('CREDIT');
  const [cardHolderName, setCardHolderName] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [cvv, setCvv] = useState('');
  const [expiryDate, setexpiryDate] = useState('');
  const [registrationMessage, setRegistrationMessage] = useState('');
  const [showLogin, setShowLogin] = useState(false);

  const handleRegistration = (event) => {
    event.preventDefault();

    if (!email || !password || !confirmPassword) {
      setRegistrationMessage('All fields are required.');
      return;
    }

    if (password !== confirmPassword) {
      setRegistrationMessage('Passwords do not match.');
      return;
    }

    if (!firstName || !lastName) {
      alert('Please enter your first and last name.');
      return;
    }

    if (!paymentCardType || !cardHolderName || !cardNumber || !cvv || !expiryDate) {
      alert('Please fill in all required payment fields.');
      return;
    }

    if (!/^\d{3}$/.test(cvv)) {
      alert('Please enter a valid 3-digit CVV.');
      return;
    }

    if (!/^\d{4}-(0[1-9]|1[0-2])$/.test(expiryDate)) {
      alert('Please enter a valid expiration date in YYYY-MM format.');
      return;
    }

    const registrationRequest = {
      credentials: {
        email: email.toString(),
        password: password.toString(),
      },
      paymentCard: {
        cardId: null,
        cardHolderName: cardHolderName.toString(),
        cardNumber: cardNumber.toString(),
        cvv: cvv.toString(),
        expiryDate: expiryDate.toString(),
        paymentCardType: paymentCardType.toString(),
      },
    };

    console.log('Registration payload:', registrationRequest);

    fetch('http://localhost:8080/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(registrationRequest),
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Registration failed. Please try again.');
        }
      })
      .then((data) => {
        console.log('Registration successful:', data);
        alert('Registration successful! You can now log in.');
        setShowLogin(true);
      })
      .catch((error) => {
        console.error('Registration error:', error);
        setRegistrationMessage(error.message || 'An error occurred.');
      });
  };

  const gotoLoginForm = () => {
    setShowLogin(true);
  };

  if (showLogin) {
    return <LoginForm />;
  }

  return (
    <div>
      <h1 className="registration-header">Register</h1>
      <p className="registration-error-message">{registrationMessage}</p>

      <form className="registration-container" onSubmit={handleRegistration}>
        <table className="registration-table">
          <tr>
            <td className="registration-table-left">
              <div className="registration-input">
                <label>Email</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required /> <br />
                <label>Password</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required /> <br />
                <label>Confirm Password</label>
                <input
                  type="password"
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  placeholder="Confirm your password"
                  required
                />
                <br />
                <label>First Name </label>
                <input type="text" onChange={(e) => setFirstName(e.target.value)} placeholder="Enter First Name" /> <br />
                <label>Last Name</label>
                <input type="text" onChange={(e) => setLastName(e.target.value)} placeholder="Enter Last Name" /> <br />
                <label>Address</label>
                <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} placeholder="Enter Address" required />
              </div>
            </td>

            <td className="registration-table-right">
              <div className="payment-method-list">
                <select onChange={(e) => setPaymentCardType(e.target.value)}>
                  <option value="">Select a Payment Method</option>
                  <option value="CREDIT">CREDIT</option>
                  <option value="DEBIT">DEBIT</option>
                </select>
              </div>

              <div className="registration-input">
                <label>Cardholder Name </label>
                <input
                  type="text"
                  value={cardHolderName}
                  onChange={(e) => setCardHolderName(e.target.value)}
                  placeholder="Enter Cardholder Name"
                  required
                />{' '}
                <br />
                <label>Card Number </label>
                <input type="text" value={cardNumber} onChange={(e) => setCardNumber(e.target.value)} placeholder="Enter Card Number" required /> <br />
                <label>CVV</label>
                <input type="text" value={cvv} onChange={(e) => setCvv(e.target.value)} placeholder="Enter CVV" required /> <br />
                <label>Expiration Date (YYYY-MM) </label>
                <input type="text" value={expiryDate} onChange={(e) => setexpiryDate(e.target.value)} placeholder="Enter Expiration Date" required /> <br />
              </div>
            </td>
          </tr>
        </table>

        <button className="registration-button" type="submit">
          Register
        </button>
      </form>

      <button className="registration-switch" onClick={gotoLoginForm}>
        Switch to Login
      </button>
    </div>
  );
};

export default RegistrationForm;
