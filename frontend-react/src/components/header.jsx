import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Icon } from "antd";

class Header extends Component {
  state = {};
  render() {
    return (
      <header>
        <Link
          to="/"
          style={{
            color: "white"
          }}
        >
          <button
            type="button"
            className="btn btn-primary"
            style={{
              width: 100,
              position: "absolute",
              top: 30,
              left: 50
            }}
          >
            Home
          </button>
        </Link>
        <h2
          className="text-center"
          style={{
            position: "absolute",
            top: 30,
            left: 300
          }}
        >
          Welcome to S&E Data Warehouse System!
        </h2>
      </header>
    );
  }
}

export default Header;
