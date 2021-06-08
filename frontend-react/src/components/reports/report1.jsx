import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";
import { Link } from "react-router-dom";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class ManufacturerProduct extends Component {
  columns = [
    {
      path: "manufacturer",
      label: "Manufacturer",
      content: manufacturer => (
        <Link
          to={`/reports-manufacturer-product/${manufacturer.manufacturer}/${
            manufacturer.total_number
          }/${manufacturer.avg_price}/${manufacturer.min_price}/${
            manufacturer.max_price
          }`}
        >
          {manufacturer.manufacturer}
        </Link>
      )
    },
    { path: "total_number", label: "Totol Number of Products" },
    {
      path: "avg_price",
      label: "Average Retail Price"
    },
    { path: "min_price", label: "Minimum Retail Price" },
    { path: "max_price", label: "Maximum Retail Price" }
  ];
  state = {
    manufacturers: [],
    pageSize: 10,
    currentPage: 1
  };
  async componentDidMount() {
    const { data: manufacturers } = await axios.get(
      `${api}/reports/manufacturers`
    );
    this.setState({ manufacturers });
  }
  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  render() {
    const { manufacturers, currentPage, pageSize } = this.state;
    const data = paginate(manufacturers, currentPage, pageSize);

    return (
      <div>
        <h3>Manufacturer's Product Report</h3>
        <Table columns={this.columns} data={data} />
        <Pagination
          itemsCount={manufacturers.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        />
      </div>
    );
  }
}

export default ManufacturerProduct;
{
  /* <table className="table table-striped">
          <thead>
            <tr>
              <td>Manufacturer Name</td>
              <td>Total Number of Products</td>
              <td>Average Retail Price</td>
              <td>Minimum Retail Price</td>
              <td>Maximum Retail Price</td>
              <td>Details</td>
            </tr>
          </thead>
          <tbody>
            {this.state.manufacturers.map(manufacturer => {
              return (
                <tr key={manufacturer.manufacturer}>
                  <td>{manufacturer.manufacturer}</td>
                  <td>{manufacturer.total_number}</td>
                  <td>{manufacturer.avg_price}</td>
                  <td>{manufacturer.min_price}</td>
                  <td>{manufacturer.max_price}</td>
                  <td>
                    <button
                      className="btn btn-primary"
                      onClick={this.handleClick.bind(
                        this,
                        manufacturer.manufacturer
                      )}
                    >
                      Show Detail
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        {this.state.manufacturerDetail && (
          <div>
            <h4>
              Manufacturer Name:{" "}
              {this.state.manufacturerDetail.manufacturerName}
            </h4>
            <h4>
              Maximum Discount: {this.state.manufacturerDetail.maxDiscount}
            </h4>
            <h4>manufacturer Products: </h4>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td>Product PID</td>
                  <td>Product Name</td>
                  <td>Product Price</td>
                  <td>Catetory</td>
                </tr>
              </thead>
              <tbody>
                {this.state.manufacturerDetail.manufacturerProducts.map(
                  product => {
                    return (
                      <tr key={product.productPID}>
                        <td>{product.productPID}</td>
                        <td>{product.productName}</td>
                        <td>{product.productPrice}</td>
                        <td>{product.productCategories}</td>
                      </tr>
                    );
                  }
                )}
              </tbody>
            </table>
          </div>
        )} */
}
