import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";

class GPSUnitReport extends Component {
  state = {
    data: []
  };
  columns = [
    {
      path: "pid",
      label: "Product ID"
    },
    { path: "product_name", label: "Product" },
    {
      path: "price",
      label: "Retail Price"
    },
    { path: "total_quantity", label: "Total Sold Quantity" },
    {
      path: "sale_quantity",
      label: "On Sale Sold Quantity"
    },
    { path: "non_sale_quantity", label: "Not On Sale Sold Quantity" },

    {
      path: "actural_revenue",
      label: "Actural Revenue"
    },
    { path: "predicted_revenue", label: "Predicted Revenue" },
    { path: "difference", label: "Difference" }
  ];
  componentDidMount() {
    axios.get(`${api}/reports/actual-vs-predicted-revenue`).then(res => {
      this.setState({
        data: [...res.data]
      });
    });
  }

  render() {
    return (
      <div>
        <h3>Actual vs Predicted Revenue for GPS Units Report</h3>
        <Table columns={this.columns} data={this.state.data} />
      </div>
    );
  }
}

export default GPSUnitReport;
