import React, { useState } from 'react';
import axios from 'axios';
import './Report.css'; // Make sure to create a Report.css file for styling

function Report() {
    const [timeSpan, setTimeSpan] = useState('1 day');
    const [reportData, setReportData] = useState(null);

    const handleTimeSpanChange = (event) => {
        setTimeSpan(event.target.value);
    };

    const generateReport = () => {
        axios.get(`http://localhost:8080/reports?timeSpan=${encodeURIComponent(timeSpan)}`, { withCredentials: true })
            .then(response => {
                setReportData(response.data);
            })
            .catch(error => {
                console.error('Error fetching report:', error);
                // Handle error
            });
    };

    return (
        <div className="report-container">
            <h2>Generate Report</h2>
            <select value={timeSpan} onChange={handleTimeSpanChange}>
                <option value="1 day">Last Day</option>
                <option value="1 week">Last Week</option>
                <option value="1 month">Last Month</option>
            </select>
            <button onClick={generateReport}>Generate Report</button>

            {reportData && (
                <div className="report-results">
                    <h3>Report Results</h3>
                    <p>Users registered: {reportData.usersRegistered}</p>
                    <p>Blogs created: {reportData.blogsCreated}</p>
                    <p>Blogs liked: {reportData.blogsLiked}</p>
                    {/* Display other report data as needed */}
                </div>
            )}
        </div>
    );
}

export default Report;
