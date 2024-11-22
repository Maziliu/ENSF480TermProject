import React, { useState } from 'react';
import axios from 'axios';

const Testing = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/auth/register', null, {
        params: { email, password },
        headers: { 'Content-Type': 'application/json' },
      });
      setMessage('Success');
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
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit">Button</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default Testing;
