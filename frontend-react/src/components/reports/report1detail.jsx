import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";

class ManufacturerProductDetail extends Component {
  columns = [
    {
      path: "productPID",
      label: "ProductID"
    },
    { path: "productName", label: "Product" },
    {
      path: "productPrice",
      label: "Price"
    },
    { path: "productCategories", label: "Catogories" }
  ];
  state = {
    manufacturer: {},
    products: []
  };
  async componentDidMount() {
    console.log(this.props.match.params);
    const { data: products } = await axios.get(
      `${api}/reports/manufacturers/${this.props.match.params.manufacturer}`
    );
    this.setState({
      manufacturer: {
        manufacturer: products.manufacturerName,
        maxDiscount: products.maxDiscount
      },
      products: products.manufacturerProducts
    });
  }

  render() {
    const { manufacturer, maxDiscount } = this.state.manufacturer;
    const {
      total_number,
      avg_price,
      min_price,
      max_price
    } = this.props.match.params;
    return (
      <div>
        <h4>
          Manufacturer: {manufacturer}
          <button
            className="btn btn-warning mx-5"
            onClick={() => {
              this.props.history.push("/reports-manufacturer-product");
            }}
          >
            Back
          </button>
        </h4>

        <p>Max discount: {maxDiscount}</p>
        <p>Total Number of Products: {total_number}</p>
        <p>Average Retail Price: {avg_price}</p>
        <p>Minimum Retail Price: {min_price}</p>
        <p>Maximum Retail Price: {max_price}</p>
        <Table columns={this.columns} data={this.state.products} />
      </div>
    );
  }
}

export default ManufacturerProductDetail;
