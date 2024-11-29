import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthContext';
import RegistrationForm from './RegistrationForm';
import '../styles/LoginForm.css';

function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [authenticationMessage, setAuthenticationMessage] = useState('');
  const [showSignup, setShowSignup] = useState(false);
  const { role, userId, setRole, setUserId, setUserEmail } = useAuthContext();
  const [isInitialRender, setIsInitialRender] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    if (isInitialRender) {
      setIsInitialRender(false);
    } else if (role !== 'guest') {
      navigate('/');
    }
  }, [role]);

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
      console.log("userdeeets:", data);
      setRole(data.user.admin ? 'admin' : 'user');
      setUserId(data.user.userId);
      setUserEmail(data.user.email);
      sessionStorage.setItem('role', role);
      sessionStorage.setItem('userId', userId);
      sessionStorage.setItem('userEmail', userId);
      navigate('/');
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
      <h1 className='login-header'>Login</h1>
      <form className='login-container' onSubmit={handleAuthentication}>
        <p className='login-error-message'>{authenticationMessage}</p>

        <div className='login-input'>
          <label>Email</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required />
        </div>

        <div className='login-input'>
          <label>Password</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required />
        </div>

        <button className='login-button' type="submit">Login</button>
      </form>

      <button className='login-switch' onClick={gotoSignupForm}>Switch to Signup</button>
    </div>
  );
}

export default LoginForm;
