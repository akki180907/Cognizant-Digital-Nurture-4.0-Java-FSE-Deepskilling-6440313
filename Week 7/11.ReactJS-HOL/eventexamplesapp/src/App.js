import React from 'react';
import CurrencyConvertor from './CurrencyConvertor';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0
    };
  }

  increment = () => {
    this.setState({ count: this.state.count + 1 });
    this.sayHello();
  };

  decrement = () => {
    this.setState({ count: this.state.count - 1 });
  };

  sayHello = () => {
    alert("Hello! This is a static message.");
  };

  sayWelcome = (message) => {
    alert("Message: " + message);
  };

  handleClick = (event) => {
    alert("I was clicked");
    console.log("Synthetic Event:", event);
  };

  render() {
    return (
      <div style={{ padding: '30px' }}>
       
        <h2>Counter: {this.state.count}</h2>

        <button onClick={this.increment}>Increase</button>{" "}
        <button onClick={this.decrement}>Decrease</button>

        <br /><br />

        <button onClick={() => this.sayWelcome("Welcome to React!")}>
          Say Welcome
        </button>

        <br /><br />

        <button onClick={this.handleClick}>Synthetic Event (OnPress)</button>

        <br /><br />
        <hr />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
