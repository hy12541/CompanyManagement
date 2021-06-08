import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class CategoryReport extends Component {
  state = {
    categories: [],
    pageSize: 10,
    currentPage: 1
  };
  async componentDidMount() {
    const { data: categories } = await axios.get(`${api}/reports/categories`);
    this.setState({ categories });
  }
  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  render() {
    const { categories, currentPage, pageSize } = this.state;
    const data = paginate(categories, currentPage, pageSize);

    return (
      <div>
        <h3>Category Report</h3>
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Category Name</th>
              <th>Total Number of products</th>
              <th>Total Number of Manufacturers</th>
              <th>Average Retail price</th>
            </tr>
          </thead>
          <tbody>
            {data.map(category => {
              return (
                <tr key={category.categoryName}>
                  <td>{category.categoryName}</td>
                  <td>{category.num_product}</td>
                  <td>{category.num_manufacturename}</td>
                  <td>{category.avg_price}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <Pagination
          itemsCount={categories.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        />
      </div>
    );
  }
}

export default CategoryReport;
