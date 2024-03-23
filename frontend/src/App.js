import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';

function App() {
  return (
      <Router>
        <div className="App">
          {/* Removed the <nav> and <ul> for cleaner navigation */}
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/" element={<Login />} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;
