import React, { useState, useEffect } from 'react';
import '../../styles/ManageUsers.css';
import '../../styles/AdminPage.css';

const ManageUsers = () => {
  const [users, setUsers] = useState([]);
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const usersResponse = await fetch('http://localhost:8080/user/users');
        const usersData = await usersResponse.json();
        setUsers(usersData);
        console.log("users: ", usersData);
      } catch (error) {
        console.error('Error fetching data:', error);
        setMessage('Failed to load data.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []); // run only once(twice, really)

  const handleUpdateUserRole = (userId, newIsAdmin) => {
    fetch(`http://localhost:8080/admin/toggle-admin?userId=${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        // No need for a body as the parameters are sent via URL
    })
    .then((response) => response.json())
    .then((data) => {
        if (data) {
            setUsers(users.map((user) => (user.userId === userId ? { ...user, admin: newIsAdmin } : user)));
            setMessage(`User role updated to ${newIsAdmin ? 'admin' : 'user'}`);
        } else {
            setMessage('Failed to update user role.');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        setMessage('Failed to update user role.');
    });
};

  const handleRemoveUser = (userId) => {
    fetch(`http://localhost:8080/user/${userId}/delete-user`, { method: 'DELETE' })
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to remove account.');
        }
        return response.text(); // Assuming the server returns a plain text response
      })
      .then(data=>{
          setUsers(users.filter((user) => user.userId !== userId));
          setMessage('User removed successfully.');
      })
      .catch((error) => {
        console.error('Error:', error);
        setMessage('Failed to remove user.');
      });
  };

  if (isLoading) {
    return <div className="loading">Loading...</div>;
  }

  return (
    <div className="manage-users">
      <h2>Manage Users</h2>
      <div>{message}</div>
      <br />
      <ul>
        {users.map((user) => (
          <li key={user.email}>
            {user.email} ({user.admin ? 'admin' : 'user'})
            <button className="manage-users-buttons" onClick={() => handleUpdateUserRole(user.userId, user.admin === true ? false : true)}>
              Toggle Role
            </button>
            <button onClick={() => handleRemoveUser(user.userId)}>Remove</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ManageUsers;
