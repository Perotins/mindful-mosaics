// // ViewBlogs.js
// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import './ViewBlogs.css'; // Make sure to define the necessary CSS for the grid layout
//
// function ViewBlogs() {
//     const [blogs, setBlogs] = useState([]);
//
//     useEffect(() => {
//         // Fetch all blogs when the component mounts
//         axios.get('http://localhost:8080/api/blogs', {withCredentials:true}) // Update with your actual endpoint
//             .then(response => setBlogs(response.data))
//             .catch(error => console.error("Could not load blogs", error));
//     }, []);
//
//     // Inside your React component that renders the blogs
//     return (
//         <div className="blogs-grid">
//             {blogs.map((blog) => (
//                 <div key={blog.id} className="blog-card">
//                     <div className="blog-title">{blog.title}</div>
//                     <div className="blog-content">{blog.content}</div>
//                     {/* You can add buttons or other interactive elements here */}
//                 </div>
//             ))}
//         </div>
//     );
//
// }
//
// export default ViewBlogs;
// ViewBlogs.js
// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom';
// import axios from 'axios';
// import './ViewBlogs.css'; // Make sure to define the necessary CSS for the grid layout
//
// function ViewBlogs() {
//     const [blogs, setBlogs] = useState([]);
//     const [searchTerm, setSearchTerm] = useState('');
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         // Fetch all blogs when the component mounts
//         axios.get('http://localhost:8080/api/blogs', {withCredentials: true}) // Update with your actual endpoint
//             .then(response => setBlogs(response.data))
//             .catch(error => console.error("Could not load blogs", error));
//     }, []);
//
//     // Handle the back navigation
//     const handleBack = () => {
//         navigate(-1); // Go back to the previous page
//     };
//
//     // Render blogs based on the search term
//     const filteredBlogs = searchTerm
//         ? blogs.filter(blog =>
//             blog.title.toLowerCase().includes(searchTerm.toLowerCase()))
//         : blogs;
//
//     return (
//         <div>
//             <button onClick={handleBack}>Back</button>
//             <input
//                 type="text"
//                 placeholder="Search blogs by title..."
//                 onChange={(e) => setSearchTerm(e.target.value)}
//             />
//             <div className="blogs-grid">
//                 {filteredBlogs.map((blog) => (
//                     <div key={blog.id} className="blog-card">
//                         <div className="blog-title">{blog.title}</div>
//                         <div className="blog-content">{blog.content}</div>
//                     </div>
//                 ))}
//             </div>
//         </div>
//     );
// }
//
// export default ViewBlogs;

// ViewBlogs.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './ViewBlogs.css'; // Ensure you have the correct CSS for the layout

function ViewBlogs() {
    const [blogs, setBlogs] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [author, setAuthor] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8080/api/blogs', { withCredentials: true }) // Adjust to your API
            .then(response => {
                setBlogs(response.data);
            })
            .catch(error => {
                console.error("Could not load blogs", error);
            });
    }, []);

    const handleBack = () => {
        navigate(-1); // Go back to the previous page
    };

    // Filter blogs based on search term and author
    const filteredBlogs = blogs.filter(blog => {
        const titleMatch = blog.title.toLowerCase().includes(searchTerm.toLowerCase());
        const authorMatch = blog.user.username.toLowerCase().includes(author.toLowerCase()); // Adjust according to your data structure
        return titleMatch && authorMatch;
    });

    return (
        <div>
            <button onClick={handleBack}>Back</button>
            <input
                type="text"
                placeholder="Search blogs by title..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            <input
                type="text"
                placeholder="Search by author username..."
                value={author}
                onChange={(e) => setAuthor(e.target.value)}
            />
            <div className="blogs-grid">
                {filteredBlogs.map((blog) => (
                    <div key={blog.id} className="blog-card" onClick={() => navigate(`/${blog.user.id}/${blog.title}`)}>
                        <div className="blog-title">{blog.title}</div>
                        <div className="blog-author">{blog.user.username}</div>
                        <div className="blog-content">{blog.content}</div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ViewBlogs;

