import React, { useState } from 'react';

function ComplaintRegister() {
  const [empName, setEmpName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!empName || !complaint) {
      alert("Please fill in all fields.");
      return;
    }

    const reference = "REF" + Math.floor(Math.random() * 1000000);
    alert(`Complaint submitted!\n\nEmployee: ${empName}\nReference No: ${reference}`);
    
    // Reset form
    setEmpName('');
    setComplaint('');
  };

  return (
    <div style={{ padding: '40px', maxWidth: '600px', margin: 'auto' }}>
      <h2>Raise a Complaint</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Employee Name: </label><br />
          <input
            type="text"
            value={empName}
            onChange={(e) => setEmpName(e.target.value)}
            placeholder="Enter your name"
            required
          />
        </div>

        <div style={{ marginTop: '15px' }}>
          <label>Complaint: </label><br />
          <textarea
            value={complaint}
            onChange={(e) => setComplaint(e.target.value)}
            placeholder="Describe your complaint"
            rows="4"
            required
          />
        </div>

        <button type="submit" style={{ marginTop: '20px' }}>
          Submit Complaint
        </button>
      </form>
    </div>
  );
}

export default ComplaintRegister;
