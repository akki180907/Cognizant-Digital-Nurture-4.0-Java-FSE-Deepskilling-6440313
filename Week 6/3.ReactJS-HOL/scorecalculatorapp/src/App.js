import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <h1>Welcome to Score Calculator App</h1>
      <CalculateScore name="Akshaya" school="ABC High School" total={450} goal={5} />
    </div>
  );
}

export default App;
