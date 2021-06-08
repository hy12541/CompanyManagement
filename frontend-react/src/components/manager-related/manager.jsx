import React, { Component } from "react";
import axios from "axios";
import { api } from "../../config";
import ManagerTable from "./managerTable";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class Manager extends Component {
  state = {
    managers: [],
    pageSize: 100,
    currentPage: 1,
    stores: []
  };
  componentDidMount() {
    axios.get(`${api}/managers`).then(res => {
      this.setState({
        managers: [...res.data]
      });
    });
  }
  addManager = () => {
    this.props.history.push("/managers/add-manager");
  };
  handleDelete = async manager => {
    const originalManagers = this.state.managers;
    const { data: stores } = await axios.get(
      `${api}/managers/activeManager/${manager.email}`
    );
    if (stores.length !== 0) {
      alert("Can't delete an active manager!");
      return;
    }
    try {
      await axios.delete(`${api}/managers/delete/${manager.email}`);
      axios.get(`${api}/managers`).then(res => {
        this.setState({
          managers: [...res.data]
        });
      });
    } catch {
      alert("Delete failed");
      this.setState({ managers: originalManagers });
    }
  };

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  render() {
    const { managers, currentPage, pageSize } = this.state;
    const data = paginate(managers, currentPage, pageSize);
    return (
      <div>
        <button
          type="button"
          className="btn text-center btn-secondary btn-lg btn-block"
          onClick={() => this.addManager()}
        >
          Add Manager
        </button>
        <ManagerTable managers={data} onDelete={this.handleDelete} />
        <Pagination
          itemsCount={managers.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        />
      </div>
    );
  }
}

export default Manager;
