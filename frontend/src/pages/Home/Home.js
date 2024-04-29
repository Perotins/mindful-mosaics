// Home.js
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Home.css'; // Make sure to create a Home.css file

function Home() {
    const [userBlogs, setUserBlogs] = useState([]);
    const navigate = useNavigate();


    useEffect(() => {
        // Fetch blogs created by the user
          axios.get('http://localhost:8080/api/user-blogs', { withCredentials: true })
            .then(response => {
                setUserBlogs(response.data);
            })
            .catch(error => {
                console.error('Failed to fetch user blogs', error);
            });
        // You might need to adjust the API endpoint above
    }, []);



    const handleCreateBlog = () => {
        navigate('/create-blog');
    };

    const handleViewBlogs = () => {
        navigate('/view-blogs');
    };

    const handleGenerateReport = () => {
        navigate('/reports'); // Assuming '/reports' is your report page's route
    };


    // Function to navigate to the blog's detail page
    const navigateToBlog = (userId, title) => {
        // Use encodeURIComponent to ensure special characters in title are correctly handled
        navigate(`/${userId}/${encodeURIComponent(title)}`);
    };


    return (
        <div className="home-container">
            <div className="home-header">
                <button onClick={handleCreateBlog}>Create New Blog</button>
                <button onClick={handleViewBlogs}>View Blogs</button>
                <button onClick={handleGenerateReport}>Generate Report</button> {/* Add this line */}

            </div>
            <div className="user-blogs">
                {userBlogs.map(blog => (
                    <div key={blog.id} className="blog-entry" onClick={() => navigateToBlog(blog.user.id, blog.title)}>
                        <h3>{blog.title}</h3>
                        <p>{blog.content}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Home;
