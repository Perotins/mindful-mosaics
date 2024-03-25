import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = (event) => {
        event.preventDefault();
        axios.post('http://localhost:8080/api/login', { username, password }, {
            withCredentials: true // If you're maintaining sessions
        })
            .then(response => {
                console.log(response.data);
                // If you're using JWT tokens, store it in local storage or state management
                // localStorage.setItem('token', response.data.token);
                navigate('/home'); // Navigate to the home page or dashboard
            })
            .catch(error => {
                console.error('Login failed:', error.response);
                // Show an error message
            });
    };

    return (
        <div className="App">
            <header className="App-header">
                <h2>Login</h2>
                <form onSubmit={handleLogin} className="login-form">
                    <div className="form-input">
                        <label htmlFor="username">Username:</label>
                        <input
                            id="username"
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-input">
                        <label htmlFor="password">Password:</label>
                        <input
                            id="password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Login</button>
                </form>
            </header>
        </div>
    );
}

export default Login;
