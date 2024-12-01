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
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email: email.toString(),
          password: password.toString(),
        }),
      });

      if (!response.ok) {
        throw new Error('Authentication failed');
      }

      const data = await response.json();
      if (data) {
        console.log('userdeeets:', data);
        setRole(data.admin ? 'admin' : 'user');
        setUserId(data.userId);
        setUserEmail(data.email);
        sessionStorage.setItem('role', role);
        sessionStorage.setItem('userId', userId);
        sessionStorage.setItem('userEmail', userId);
        navigate('/');
      } else {
        setAuthenticationMessage('Incorrect email or password. Please try again.');
      }
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
    <div className='login-container'>
    <div className='login-form'>
      <h1 className="login-header">Login</h1>
      <form className="login-form-container" onSubmit={handleAuthentication}>
        <p className="login-error-message">{authenticationMessage}</p>

        <div className="login-input">
          <label>Email</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" required />
        </div>

        <div className="login-input">
          <label>Password</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" required />
        </div>
        <div className='login-button-container'>
          <button className="login-button" type="submit">
            Login
          </button>
          <button className="login-switch" onClick={gotoSignupForm}>
          Switch to Signup
        </button>
      </div>
      </form>
    </div>
    </div>
  );
}

export default LoginForm;
