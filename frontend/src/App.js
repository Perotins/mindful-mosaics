import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';
import Home from './Home'; // Import the Home component





function App() {
  return (
      <Router>
        <div className="App">
          {/* Removed the <nav> and <ul> for cleaner navigation */}
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/" element={<Login />} />
              <Route path="/home" element={<Home />} /> {/* Add this line */}

          </Routes>
        </div>
      </Router>
  );
}

export default App;
