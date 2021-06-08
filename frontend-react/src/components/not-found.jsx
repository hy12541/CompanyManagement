import React, { Component } from "react";
class NotFound extends Component {
  state = {};
  handleClick = () => {
    this.props.history.push("/");
  };
  render() {
    return (
      <div className="text-center">
        <h1>Page Not Found</h1>
        <p>Sorry, but the page you were trying to view does not exist.</p>
        <button className="btn btn-primary" onClick={this.handleClick}>
          Back to Home Page
        </button>
      </div>
    );
  }
}

export default NotFound;
