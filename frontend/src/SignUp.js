import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie'; // Import js-cookie
import { useNavigate } from 'react-router-dom';
import './App.css'; // Assuming you want to use styles from App.css

function SignUp() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = (event) => {
        event.preventDefault();
        const csrfToken = Cookies.get('XSRF-TOKEN');



        axios.post('http://localhost:8080/api/register', { email, username, password }, {
            withCredentials: true,
            headers: {
                // Include CSRF token in the request
                'X-CSRF-TOKEN': csrfToken,
                'Content-Type': 'application/json'
            }
        }) // Using relative path thanks to proxy setting
            .then(response => {
                console.log('User registered:', response.data);
                // navigate('/'); // Navigate to login page after successful registration
            })
            .catch(error => {
                console.error('Registration failed:', error.response);
                // Here, you'd handle errors, possibly showing an error message to the user
            });
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
