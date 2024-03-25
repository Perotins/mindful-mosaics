import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';
import Home from './Home';
import CreateBlog from "./CreateBlog"; // Import the Home component





function App() {
  return (
      <Router>
        <div className="App">
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/" element={<Login />} />
              <Route path="/home" element={<Home />} />
              <Route path="/create-blog" element={<CreateBlog />} />

          </Routes>
        </div>
      </Router>
  );
}

export default App;
