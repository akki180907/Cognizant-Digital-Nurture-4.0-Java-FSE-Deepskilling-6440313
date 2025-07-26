import React from 'react';

class Cart extends React.Component {
  render() {
    return (
      <div style={{ border: '1px solid #ddd', padding: '10px', margin: '10px' }}>
        <h3>{this.props.itemname}</h3>
        <p>Price: ₹{this.props.price}</p>
      </div>
    );
  }
}

// Optional: Set default props
Cart.defaultProps = {
  itemname: "Unknown Item",
  price: 0
};

export default Cart;
