import React, { useState } from 'react';
import LoginForm from './LoginForm';

const RegistrationForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [address, setAddress] = useState('');
  const [paymentMethod, setPaymentMethod] = useState('credit');
  const [cardholderName, setCardholderName] = useState('');
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

    //check name fields
    if (!firstName || !lastName){
      alert('Please enter your first and last name.');
    }

    //validate card fields if 'new' payment method is selected
    if (!paymentMethod || !cardholderName || !cardNumber || !cvv || !expiryDate) {
      alert('Please fill in all required payment fields.');
      return;
    }

    //validate cvv
    if (!/^\d{3}$/.test(cvv)) {
        alert('Please enter a valid 3-digit cvv.');
        return;
    }

    //validate expiration date
    if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(expiryDate)) {
        alert('Please enter a valid expiration date in MM/YY format.');
        return;
    }

    const paymentCard = {
      paymentMethod,
      cardholderName,
      cardNumber,
      cvv,
      expiryDate,
    }

    const userDetails = {
      address,
      email,
      first_name:firstName,
      is_admin: false,
      last_name:lastName,
      password,
      paymentCard,
    };
    console.log("registration: ", userDetails);
    fetch('http://localhost:8080/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({ email, password }).toString(),
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Registration failed. Please try again.');
        }
      })
      .then((data) => {
        console.log("reg suc ", data);
        if (data) {
          fetch(`http://localhost:8080/user/${data.userId}/update-details`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userDetails),
          })
          .then(response => {
            setRegistrationMessage('Registration successful! You can now log in.');
          })
          .catch(error => {
            setRegistrationMessage(error.message);
          });
        } else {
          setRegistrationMessage(data.message || 'Registration failed.');
        }
      })
      .catch((error) => {
        setRegistrationMessage(error.message);
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
      <h1>Register</h1>
      <p style={{ color: 'red' }}>{registrationMessage}</p>
      <form onSubmit={handleRegistration}>
        <label>Email:</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required /> <br />
        <label>Password:</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required /> <br />
        <label>Confirm Password:</label>
            <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder="Confirm your password" required /><br />
        <label>First Name: </label>
            <input type="text" onChange={(e)=> setFirstName(e.target.value)}placeholder='Enter First Name'/> <br/>
        <label>Last Name: </label>
            <input type="text" onChange={(e)=> setLastName(e.target.value)}placeholder='Enter Last Name'/> <br/>
        <label>Address: </label>
            <input type="text" value={address} onChange={(e)=>setAddress(e.target.value)} placeholder='Enter Address' required />
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
        <label>Expiration Date (MM/YY): </label>
            <input type="text" value={expiryDate} onChange={(e)=>setexpiryDate(e.target.value)} placeholder='Enter Expiration Date' required /> <br/>
        <button type="submit">Register</button>
      </form>
      <button onClick={gotoLoginForm}>Switch to Login</button>
    </div>
  );
};

export default RegistrationForm;
