import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';
import Home from './Home';
import CreateBlog from "./CreateBlog"; // Import the Home component
import BlogPage from './BlogPage'; // Import the BlogPage component
import ViewBlogs from './ViewBlogs'; // Import the new component





function App() {
  return (
      <Router>
        <div className="App">
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/" element={<Login />} />
              <Route path="/home" element={<Home />} />
              <Route path="/create-blog" element={<CreateBlog />} />
              <Route path="/:userId/:title" element={<BlogPage />} /> {/* Step 4: Route for individual blog pages */}
              <Route path="/view-blogs" element={<ViewBlogs />} />

          </Routes>
        </div>
      </Router>
  );
}

export default App;
