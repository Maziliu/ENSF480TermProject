import React, { useState, useEffect } from 'react';
import '../styles/ManageUsers.css';

//NOT ADJUSTED TO THIS FRONTEND ROUTING
const ManageUsers = () => {
  const [users, setUsers] = useState([]);
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const usersResponse = await fetch('http://localhost:5000/Users');
        const usersData = await usersResponse.json();
        setUsers(usersData);

      } catch (error) {
        console.error('Error fetching data:', error);
        setMessage('Failed to load data.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []); // run only once(twice, really)

  const handleUpdateUserRole = (username, newIsAdmin) => {
    fetch(`http://localhost:5000/User/Update/${username}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ admin: newIsAdmin }),
    })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setUsers(
            users.map(user => (user.username === username ? { ...user, admin: newIsAdmin } : user))
          );
          setMessage(`User role updated to ${newIsAdmin ? 'admin' : 'user'}`);
        } else {
          setMessage('Failed to update user role.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to update user role.');
      });
  };

  const handleRemoveUser = (username) => {
    fetch(`http://localhost:5000/User/${username}/Unregister`, { method: 'DELETE' })
      .then(response => response.json())
      .then(data => {
        if (data.message) {
          setUsers(users.filter(user => user.username !== username));
          setMessage('User removed successfully.');
        } else {
          setMessage('Failed to remove user.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('Failed to remove user.');
      });
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div className='manage-users'>
      <h2>Manage Users</h2>
      <div>{message}</div><br/>
      <ul>
        {users.map(user => (
          <li key={user.username}>
            {user.username} ({user.admin ? 'admin' : 'user'})
            <button className='manage-users-buttons' onClick={() => handleUpdateUserRole(user.username, user.admin === true ? false : true)}>Toggle Role</button>
            <button onClick={() => handleRemoveUser(user.username)}>Remove</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageUsers;
