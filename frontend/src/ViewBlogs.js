// ViewBlogs.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ViewBlogs.css'; // Make sure to define the necessary CSS for the grid layout

function ViewBlogs() {
    const [blogs, setBlogs] = useState([]);

    useEffect(() => {
        // Fetch all blogs when the component mounts
        axios.get('http://localhost:8080/api/blogs', {withCredentials:true}) // Update with your actual endpoint
            .then(response => setBlogs(response.data))
            .catch(error => console.error("Could not load blogs", error));
    }, []);

    // Inside your React component that renders the blogs
    return (
        <div className="blogs-grid">
            {blogs.map((blog) => (
                <div key={blog.id} className="blog-card">
                    <div className="blog-title">{blog.title}</div>
                    <div className="blog-content">{blog.content}</div>
                    {/* You can add buttons or other interactive elements here */}
                </div>
            ))}
        </div>
    );

}

export default ViewBlogs;
