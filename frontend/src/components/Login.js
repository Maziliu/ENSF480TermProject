import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/auth/register', null, {
        params: { email, password },
        headers: { 'Content-Type': 'application/json' },
      });
      navigate('/Home');
    } catch (error) {
      if (error.response) {
        setMessage(`Error: ${error.response.status} - ${error.response.data}`);
      } else if (error.request) {
        setMessage('No response from server.');
      } else {
        setMessage(`Error: ${error.message}`);
      }
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email</label>
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit">Login</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default Login;
