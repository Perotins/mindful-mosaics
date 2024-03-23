import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css'; // Assuming you want to use styles from App.css

function SignUp() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = async (event) => {
        event.preventDefault();
        // Implement your registration logic here
        // This is where you would connect to your backend to register the user
        console.log('Register', { email, username, password });
        // After successful registration, you might want to navigate to the login page
        // navigate('/');
    };

    return (
        <div className="App">
            <header className="App-header">
                <h2>Sign Up</h2>
                <form onSubmit={handleRegister} className="signup-form">
                    <div className="form-input">
                        <label htmlFor="email">Email:</label>
                        <input
                            id="email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
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
                    <div className="form-actions">
                        <button type="submit">Register</button>
                        <button type="button" onClick={() => navigate('/')}>
                            Already have an account? Log In
                        </button>
                    </div>
                </form>
            </header>
        </div>
    );
}

export default SignUp;
