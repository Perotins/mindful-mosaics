import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css'; // Or wherever your styles are located

function Login() {
    const navigate = useNavigate(); // Hook to navigate
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (event) => {
        event.preventDefault();
        // Implement your login logic here
        console.log('Login', { username, password });
    };

    const handleSignUp = () => {
        navigate('/signup'); // Use navigate instead of history.push
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
                        />
                    </div>
                    <div className="form-input">
                        <label htmlFor="password">Password:</label>
                        <input
                            id="password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <button type="submit">Login</button>
                    <button type="button" className="signup-btn" onClick={handleSignUp}>
                        Sign Up
                    </button>
                </form>
            </header>
        </div>
    );
}

export default Login;
