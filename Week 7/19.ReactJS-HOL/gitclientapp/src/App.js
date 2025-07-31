import React, { useEffect, useState } from "react";
import GitClient from "./GitClient";

function App() {
  const [repos, setRepos] = useState([]);

  useEffect(() => {
    const gitClient = new GitClient();
    gitClient.getRepositories("techiesyed").then(setRepos);
  }, []);

  return (
    <div>
      <h2>GitHub Repositories for techiesyed</h2>
      <ul>
        {repos.map((repo, index) => (
          <li key={index}>{repo}</li>
        ))}
      </ul>
    </div>
  );
}

export default App; // ✅ This is essential
