import React from 'react';

function ListofPlayers() {
  const players = [
    { name: "Rohit", score: 90 },
    { name: "Kohli", score: 75 },
    { name: "Gill", score: 65 },
    { name: "Sky", score: 85 },
    { name: "Rahul", score: 50 },
    { name: "Hardik", score: 95 },
    { name: "Jadeja", score: 60 },
    { name: "Ashwin", score: 88 },
    { name: "Shami", score: 40 },
    { name: "Bumrah", score: 78 },
    { name: "Siraj", score: 55 },
  ];

  const below70 = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>List of Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} ({player.score})</li>
        ))}
      </ul>

      <h2>List of Players having Scores Less than 70</h2>
      <ul>
        {below70.map((player, index) => (
          <li key={index}>{player.name} ({player.score})</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
