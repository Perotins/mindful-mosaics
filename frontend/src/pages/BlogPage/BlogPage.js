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
            <button onClick={editBlog}>Edit Blog</button>

            <button onClick={returnToHome}>Home</button>

        </div>


);
}

export default BlogPage;
