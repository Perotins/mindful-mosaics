import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function CreateBlog() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            console.log("123")
            // Adjust URL and add authorization headers as necessary
            const response = await axios.post('http://localhost:8080/api/create-blog', { title, content }, { withCredentials: true });
            console.log(response.data);
            console.log("323")

            console.log("456") // NOT PRINTING THIS LINE, assuming navigate is causing an error

            // Redirect or show success message
            const { user: { id: userId } } = response.data;

            console.log("userId = " + userId + " blogTitle= " + title)

            // Navigate to the newly created blog
            navigate(`/${userId}/${title}`); // Adjust the path as necessary

        } catch (error) {
            console.error("Error creating blog", error.response);
            // Show error message
        }
    };

    return (
        <div>
            <h2>Create Blog</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Title:</label>
                    <input type="text" value={title} onChange={e => setTitle(e.target.value)} required />
                </div>
                <div>
                    <label>Content:</label>
                    <textarea value={content} onChange={e => setContent(e.target.value)} required />
                </div>
                <button type="submit">Create</button>
            </form>
        </div>
    );
}

export default CreateBlog;
