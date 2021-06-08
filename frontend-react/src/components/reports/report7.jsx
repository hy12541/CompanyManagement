import React, { Component } from "react";
import axios from "axios";
import { api } from "./../../config";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class RevenueByPopulation extends Component {
  state = {
    selectedCategory: "",
    storeTotalData: [],
    cityYearlyData: [],
    pageSize: 30,
    currentPage: 1
  };
  async componentDidMount() {
    axios.get(`${api}/reports/revenue-by-population/`).then(res => {
      const { data: cityYearlyData } = res;
      this.setState({ cityYearlyData });
    });
  }
  handleSelect = async e => {
    this.setState({ selectedCategory: e.target.value });
    const { data: storeTotalData } = await axios.get(
      `${api}/reports/revenue-by-population/${e.target.value}`
    );
    this.setState({ storeTotalData });
    console.log(this.state);
  };

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };

  render() {
    const {
      selectedCategory,
      storeTotalData: data,
      currentPage,
      pageSize
    } = this.state;
    const perPage = paginate(data, currentPage, pageSize);

    return (
      <div>
        <h3>Revenue By Population Report</h3>
        <table className="table table-striped">
          <thead>
            <tr>
              <td>Year</td>
              <td>Small City(K)</td>
              <td>Medium City(K)</td>
              <td>Large City(K)</td>
              <td>Extra Large City(K)</td>
            </tr>
          </thead>
          <tbody>
            {this.state.cityYearlyData.map(record => {
              return (
                <tr key={record.year}>
                  <td>{record.year}</td>
                  <td>{record.smallCity}</td>
                  <td>{record.mediumCity}</td>
                  <td>{record.largeCity}</td>
                  <td>{record.extraLargeCity}</td>
                </tr>
              );
            })}
          </tbody>
        </table>

        {/* <select
          className="custom-select"
          onChange={this.handleSelect}
          value={selectedCategory}
        >
          <option value="">Select a Category</option>
          <option value="small">Small</option>
          <option value="medium">Medium</option>
          <option value="large">Large</option>
          <option value="extralarge">Extra Large</option>
        </select>
        {selectedCategory && (
          <table className="table table-striped">
            <thead>
              <tr>
                <td>Store ID</td>
                <td>Revenue</td>
              </tr>
            </thead>
            <tbody>
              {perPage.map(store => {
                return (
                  <tr key={store.storeID}>
                    <td>{store.storeID}</td>
                    <td>{store.revenue}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        )}
        <Pagination
          itemsCount={data.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        /> */}
      </div>
    );
  }
}

export default RevenueByPopulation;
