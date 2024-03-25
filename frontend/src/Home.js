// Home.js
import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
    const navigate = useNavigate();

    const handleCreateBlog = () => {
        // Redirect to the blog creation form
        navigate('/create-blog');
    };

    const handleViewBlogs = () => {
        // Redirect to the blog viewing page
        navigate('/view-blogs');
    };

    return (
        <div>
            <h2>Home Page</h2>
            <button onClick={handleCreateBlog}>Create New Blog</button>
            <button onClick={handleViewBlogs}>View Blogs</button>
        </div>
    );
}

export default Home;
