import React, { Component } from "react";
import axios from "axios";
import { api } from "../../config";
import { Link } from "react-router-dom";
import Table from "../common/table";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class City extends Component {
  state = {
    cities: [],
    pageSize: 20,
    currentPage: 1
  };
  async componentDidMount() {
    const { data } = await axios.get(`${api}/cities`);
    this.setState({ cities: data });
    console.log(data);
  }
  async editCity(state_name, city) {
    this.props.history.push(`/cities/${state_name}/${city}`);
  }

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  columns = [
    {
      path: "state_name",
      label: "State"
    },
    {
      path: "city",
      label: "City"
    },
    {
      path: "population",
      label: "Population"
    },
    {
      key: "edit",
      label: "Edit",
      content: city => (
        <Link to={`/cities/${city.state_name}/${city.city}`}>
          <button className="btn btn-success">Edit</button>
        </Link>
      )
    }
  ];

  render() {
    const { cities, currentPage, pageSize } = this.state;
    const data = paginate(cities, currentPage, pageSize);
    return (
      <div>
        <Table columns={this.columns} data={data} />
        <Pagination
          itemsCount={cities.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        />
      </div>
    );
  }
}

export default City;
