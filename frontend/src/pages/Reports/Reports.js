import React, { useState } from 'react';
import axios from 'axios';
import './Reports.css'; // Ensure the CSS is properly set up

function ReportsDisplay() {
    const [timeFrame, setTimeFrame] = useState('1day'); // Use the same time frame keys as in your backend
    const [reportData, setReportData] = useState(null);

    const handleTimeFrameChange = (event) => {
        setTimeFrame(event.target.value);
    };

    const generateReport = () => {
        // Correct endpoint with query parameter
        axios.get(`http://localhost:8080/api/reports?timeFrame=${encodeURIComponent(timeFrame)}`, { withCredentials: true })
            .then(response => {
                setReportData(response.data);
            })
            .catch(error => {
                console.error('Error fetching report:', error);
            });
    };


    return (
        <div className="report-container">
            <h2>Generate Report</h2>
            <select value={timeFrame} onChange={handleTimeFrameChange}>
                <option value="1day">Last Day</option>
                <option value="1week">Last Week</option>
                <option value="1month">Last Month</option>
            </select>
            <button onClick={generateReport}>Generate Report</button>

            {reportData && (
                <div className="report-results">
                    <h3>Report Results</h3>
                    <p>Users registered: {reportData.usersRegistered}</p>
                    <p>Blogs created: {reportData.blogsCreated}</p>
                    <p>Blogs liked: {reportData.blogsLiked}</p>
                    <p>Blogs deleted: {reportData.blogsDeleted}</p>
                    <p>Average Blog Length: {reportData.averageBlogLength} words</p>
                    <p>Tags created: {reportData.tagsCreated} tags</p>
                    <p>Most used tag: #{reportData.mostUsedTag}</p>
                </div>
            )}
        </div>
    );
}

export default ReportsDisplay;
