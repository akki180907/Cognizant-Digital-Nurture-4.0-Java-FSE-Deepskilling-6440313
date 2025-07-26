import React from 'react';
import Cart from './Cart';

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      cartItems: [
        { itemname: "Laptop", price: 60000 },
        { itemname: "Headphones", price: 2000 },
        { itemname: "Mouse", price: 800 },
        { itemname: "Keyboard", price: 1500 },
        { itemname: "Monitor", price: 12000 }
      ]
    };
  }

  render() {
    return (
      <div>
        <h2>Online Shopping Cart</h2>
        {this.state.cartItems.map((item, index) => (
          <Cart key={index} itemname={item.itemname} price={item.price} />
        ))}
      </div>
    );
  }
}

export default OnlineShopping;
