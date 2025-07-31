import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';

function EmployeeCard({ employee }) {
  const theme = useContext(ThemeContext); // ✅ Get theme

  return (
    <div className="card">
      <h3>{employee.name}</h3>
      <button className={theme}>View Profile</button> {/* ✅ Styled by context */}
    </div>
  );
}

export default EmployeeCard;
