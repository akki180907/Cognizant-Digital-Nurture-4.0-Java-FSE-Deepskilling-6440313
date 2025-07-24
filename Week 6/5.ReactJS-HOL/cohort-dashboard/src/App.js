import React from 'react';
import CohortDetails from './components/CohortDetails';

function App() {
  return (
    <div className="App">
      <h1>My Academy Dashboard</h1>

      <CohortDetails
        name="React Bootcamp"
        status="ongoing"
        mentor="Akshaya"
      />

      <CohortDetails
        name="Spring Boot Training"
        status="completed"
        mentor="Sriram"
      />
    </div>
  );
}

export default App;
