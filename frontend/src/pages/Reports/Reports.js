// Reports.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Reports() {
    const navigate = useNavigate();
    const [duration, setDuration] = useState('1day'); // Default selection

    const handleDurationChange = (event) => {
        setDuration(event.target.value);
    };

    const handleGenerateClick = () => {
        console.log(`Generating report for: ${duration}`);
        // Here you would call your backend service to generate the report
    };

    return (
        <div>
            <h2>Generate Report</h2>
            <select value={duration} onChange={handleDurationChange}>
                <option value="1day">1 Day</option>
                <option value="1week">1 Week</option>
                <option value="1month">1 Month</option>
            </select>
            <button onClick={handleGenerateClick}>Generate</button>
            <button onClick={() => navigate('/home')}>Back to Home</button>
        </div>
    );
}

export default Reports;
