// EditBlog.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function EditBlog() {
    const { userId, title } = useParams();
    const [blogContent, setBlogContent] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch the current blog content
        axios.get(`http://localhost:8080/api/${userId}/${title}`, { withCredentials: true })
            .then(response => {
                setBlogContent(response.data.content);
            })
            .catch(error => console.error("Could not load blog for editing", error));
    }, [userId, title]);

    const handleSave = async () => {
        try {
            // Update the blog content
            await axios.put(`http://localhost:8080/api/update-blog/${userId}/${title}`, { content: blogContent }, { withCredentials: true });
            navigate(`/${userId}/${title}`); // Navigate to the updated blog view
        } catch (error) {
            console.error("Error updating blog", error.response);
            // Show error message
        }
    };

    return (
        <div>
            <h2>Editing Blog: {title}</h2>
            <textarea
                value={blogContent}
                onChange={(e) => setBlogContent(e.target.value)}
            />
            <button onClick={handleSave}>Save</button>
        </div>
    );
}

export default EditBlog;
