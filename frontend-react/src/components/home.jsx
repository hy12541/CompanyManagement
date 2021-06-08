import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { api } from "../config";
import { Card, Divider, Anchor } from "antd";

class Home extends Component {
  state = {
    data: [
      {
        count_manager: 0,
        count_manufacturer: 0,
        count_product: 0,
        count_store: 0
      }
    ]
  };
  styles = {
    fontSize: "10px",
    fontWeight: "bold"
  };
  async componentDidMount() {
    const { data } = await axios.get(`${api}/reports/dashboard`);
    this.setState({
      data
    });
  }
  render() {
    return (
      <main>
        <Card title={<Link to="/managers">Total Number of Managers</Link>}>
          {this.state.data[0].count_manager || ""}
        </Card>
        <Divider />
        <Card
          title={
            <Link to="/reports-manufacturer-product">
              Total Number of Manufacturers
            </Link>
          }
        >
          {this.state.data[0].count_manufacturer || ""}
        </Card>
        <Divider />
        <Card title="Total Number of Products">
          {this.state.data[0].count_product || ""}
        </Card>
        <Divider />
        <Card title="Total Number of Stores">
          {this.state.data[0].count_store || ""}
        </Card>
      </main>
    );
  }
}

export default Home;
