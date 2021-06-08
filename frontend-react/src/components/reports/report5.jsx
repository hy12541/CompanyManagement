import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";

class ACOnGroundhogDay extends Component {
  state = { ACOnGroundhogDay: [] };
  columns = [
    {
      path: "sold_year",
      label: "Year"
    },
    { path: "total_num", label: "Total Sold Quantity" },
    {
      path: "avg_num",
      label: "Daiy Sold Quantity"
    },
    { path: "groundhog_num", label: "Sold Quantity on Groundhog Day" }
  ];
  async componentDidMount() {
    const { data } = await axios.get(`${api}/reports/ac-on-groundhog-day`);
    this.setState({ ACOnGroundhogDay: data });
  }

  render() {
    return (
      <div>
        <h3>Air Conditioners on GroudhogDay Report</h3>
        <Table columns={this.columns} data={this.state.ACOnGroundhogDay} />
      </div>
    );
  }
}

export default ACOnGroundhogDay;
