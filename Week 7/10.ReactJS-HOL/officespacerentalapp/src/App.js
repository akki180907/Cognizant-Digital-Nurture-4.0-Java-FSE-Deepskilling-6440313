import React from 'react';
import './App.css'; // optional for background or fonts

function App() {
  const officeList = [
    {
      name: "SpaceHub Office",
      rent: 75000,
      address: "Bangalore, India",
      image: "https://via.placeholder.com/300x150?text=Office+1"
    },
    {
      name: "WorkNest Office",
      rent: 58000,
      address: "Hyderabad, India",
      image: "https://via.placeholder.com/300x150?text=Office+2"
    },
    {
      name: "Skyline Cowork",
      rent: 62000,
      address: "Pune, India",
      image: "https://via.placeholder.com/300x150?text=Office+3"
    }
  ];

  const title = <h1>Office Space Rental Application</h1>;

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial' }}>
      {title}

      {officeList.map((office, index) => {
        const rentStyle = {
          color: office.rent < 60000 ? 'red' : 'green',
          fontWeight: 'bold'
        };

        return (
          <div key={index} style={{ border: '1px solid #ccc', padding: '15px', marginBottom: '15px' }}>
            <h2>{office.name}</h2>
            <img src={office.image} alt={office.name} width="300" height="150" />
            <p><strong>Address:</strong> {office.address}</p>
            <p><strong>Rent:</strong> <span style={rentStyle}>₹{office.rent}</span></p>
          </div>
        );
      })}
    </div>
  );
}

export default App;
