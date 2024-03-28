import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';
import Home from './pages/Home/Home';
import CreateBlog from './pages/CreateBlog/CreateBlog';
import BlogPage from './pages/BlogPage/BlogPage';
import ViewBlogs from './pages/ViewBlogs/ViewBlogs';
import EditBlog from './pages/EditBlog/EditBlog';






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
              <Route path="/update-blog/:userId/:title" element={<EditBlog />} />


          </Routes>
        </div>
      </Router>
  );
}

export default App;
