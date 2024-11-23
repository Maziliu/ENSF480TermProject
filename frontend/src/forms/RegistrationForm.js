import React, { useState } from 'react';
import LoginForm from './LoginForm';

const RegistrationForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [registrationMessage, setRegistrationMessage] = useState('');
  const [showLogin, setShowLogin] = useState(false);

  const handleRegistration = (event) => {
    event.preventDefault();

    // Validate input fields
    if (!email || !password || !confirmPassword) {
      setRegistrationMessage('All fields are required.');
      return;
    }

    if (password !== confirmPassword) {
      setRegistrationMessage('Passwords do not match.');
      return;
    }

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
        if (data.success) {
          setRegistrationMessage('Registration successful! You can now log in.');
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

  // If the user opts to switch to the login form, render it
  if (showLogin) {
    return <LoginForm />;
  }

  return (
    <div>
      <h1>Register</h1>
      <p style={{ color: 'red' }}>{registrationMessage}</p>
      <form onSubmit={handleRegistration}>
        <label>Email:</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required />
        <br />
        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required />
        <br />
        <label>Confirm Password:</label>
        <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder="Confirm your password" required />
        <br />
        <button type="submit">Register</button>
      </form>
      <button onClick={gotoLoginForm}>Switch to Login</button>
    </div>
  );
};

export default RegistrationForm;
