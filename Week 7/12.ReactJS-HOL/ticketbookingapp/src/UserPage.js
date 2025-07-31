import React from 'react';

function UserPage() {
  return (
    <div>
      <h2>Welcome, User!</h2>
      <p>Select your flight and confirm booking:</p>
      <ul>
        <li>Flight A → <button>Book</button></li>
        <li>Flight B → <button>Book</button></li>
        <li>Flight C → <button>Book</button></li>
      </ul>
      <p>Enjoy your journey!</p>
    </div>
  );
}

export default UserPage;
