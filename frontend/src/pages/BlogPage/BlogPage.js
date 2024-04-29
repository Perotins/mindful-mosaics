import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function BlogPage() {
    const { userId, title } = useParams();
    const [blog, setBlog] = useState(null);
    const navigate = useNavigate()

    const returnToHome = () => {
        navigate('/home');
    };

    const editBlog = () => {
        navigate(`/update-blog/${userId}/${title}`);
    }

    const likeBlog = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/blogs/${blog.id}/like`, {}, {
                withCredentials: true
            });
            if (response.status === 200) {
                setBlog({ ...blog, likes: blog.likes + 1 }); // Update the likes count in the state
            }
        } catch (error) {
            console.error("Error liking blog", error);
            // Show error message
        }
    };


    const deleteBlog = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/${userId}/${title}`, {
                withCredentials: true
            });
            navigate('/home'); // Redirect to home after deletion
        } catch (error) {
            console.error("Error deleting blog", error);
            // Show error message
        }
    };


    useEffect(() => {
        axios.get(`http://localhost:8080/api/${userId}/${title}`, {
            withCredentials: true
        })
            .then(response => setBlog(response.data))
            .catch(error => console.error("Could not load blog", error));
    }, [userId, title]);

    if (!blog) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{blog.title}</h2>
            <p>{blog.content}</p>
            <div>Likes: {blog.likes}</div>

            <button onClick={likeBlog}>Like Blog</button>

            <button onClick={editBlog}>Edit Blog</button>
            <button onClick={deleteBlog}>Delete Blog</button>

            <button onClick={returnToHome}>Home</button>

        </div>


);
}

export default BlogPage;
