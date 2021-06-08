import React, { Component } from "react";
import logo from "./logo.svg";
import Header from "./components/header";
import Footer from "./components/footer";
import NavBar from "./components/navBar";

class App extends Component {
  render() {
    return (
      <React.Fragment>
        <Header />
        <NavBar />
        <div
          style={{
            position: "absolute",
            left: 350,
            top: 150,
            right: 200
          }}
        >
          {this.props.children}
        </div>
        <Footer />
      </React.Fragment>
    );
  }
}
export default App;
