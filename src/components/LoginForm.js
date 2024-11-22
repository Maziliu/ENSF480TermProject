import React, {useState, useEffect} from 'react';
import RegistrationForm from './RegistrationForm.js';
import {useNavigate} from 'react-router-dom';
import { useAuthContext } from '../App.js';

function LoginForm() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [authenticationMessage, setAuthenticationMessage] = useState("");
    const [showSignup, setShowSignup] = useState(false);
    const {authenticated, setAuthenticated} = useAuthContext();
    const [isInitialRender, setIsInitialRender] = useState(true);

    const navigate = useNavigate();

    useEffect(()=> {
        if (isInitialRender){
            setIsInitialRender(false)
        }
        else if (authenticated){
            navigate(`/Products`);
        }
    },[authenticated])
    
    function handleAuthenication() {
        if (!username || !password) {
            setAuthenticationMessage("All fields required");
        }
        else{
            fetch('http://localhost:5000/Login',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({'username':username, 'password':password}),
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Authentication failed');
                }
            })
            .then(data => {setAuthenticationMessage(data.authMessage); setAuthenticated(data.authenticated); sessionStorage.setItem('authenticated', data.authenticated)})
            .catch(error => setAuthenticationMessage(error));
    }console.log(authenticationMessage)
    };

    function gotoSignupForm() {
        setShowSignup(true); 
    }

    if (showSignup) {
        return <RegistrationForm />; 
    }

    return (
        <div>
            <h1>Login</h1>
            <p style={{ color: "red" }}>{authenticationMessage}</p>

            <label>Username: </label>
            <input
                type="text"
                onChange={(e) => setUsername(e.target.value)}
                placeholder='Enter your username'
            />
            <br />

            <label>Password: </label>
            <input
                type="password"
                onChange={(e) => setPassword(e.target.value)}
                placeholder='Enter your password'
            />
            <br />

            <button onClick={handleAuthenication}>Login</button> <br />
            <button onClick={gotoSignupForm}>Switch to Signup</button>
        </div>
    );
};

export default LoginForm;