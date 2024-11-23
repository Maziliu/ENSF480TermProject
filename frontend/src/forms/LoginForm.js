import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import RegistrationForm from './RegistrationForm';

function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [authenticationMessage, setAuthenticationMessage] = useState('');
  const [showSignup, setShowSignup] = useState(false);
  const { authenticated, setAuthenticated } = useAuthContext();
  const [isInitialRender, setIsInitialRender] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    if (isInitialRender) {
      setIsInitialRender(false);
    } else if (authenticated) {
      navigate('/Products');
    }
  }, [authenticated]);

  const handleAuthentication = async (event) => {
    event.preventDefault();
    if (!email || !password) {
      setAuthenticationMessage('All fields are required');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ email, password }).toString(),
      });

      if (!response.ok) {
        throw new Error('Authentication failed');
      }

      const data = await response.json();
      setAuthenticationMessage(data.authMessage);
      setAuthenticated(data.authenticated);
      sessionStorage.setItem('authenticated', data.authenticated);
    } catch (error) {
      setAuthenticationMessage(error.message || 'An error occurred');
    }
  };

  const gotoSignupForm = () => {
    setShowSignup(true);
  };

  if (showSignup) {
    return <RegistrationForm />;
  }

  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleAuthentication}>
        <p style={{ color: 'red' }}>{authenticationMessage}</p>

        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required />
        </div>

        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required />
        </div>

        <button type="submit">Login</button>
      </form>

      <button onClick={gotoSignupForm}>Switch to Signup</button>
    </div>
  );
}

export default LoginForm;
