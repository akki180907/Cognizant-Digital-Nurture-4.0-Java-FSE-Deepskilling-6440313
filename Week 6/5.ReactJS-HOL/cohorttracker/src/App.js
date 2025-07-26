import React from 'react';
import CohortDetails from './CohortDetails';

function App() {
  const sampleCohort = {
    cohortCode: "C123",
    technology: "React",
    startDate: "2024-06-01",
    currentStatus: "ongoing",  // Try changing this to "completed"
    coachName: "Akshaya",
    trainerName: "Sriram"
  };

  return (
    <div className="App">
      <h1>Cohort Tracker</h1>
      <CohortDetails cohort={sampleCohort} />
    </div>
  );
}

export default App;
