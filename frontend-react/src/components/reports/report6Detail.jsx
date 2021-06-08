import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";
import moment from "moment";

class StateWithHighestVolume extends Component {
  columns = [
    {
      path: "storeID",
      label: "StoreID"
    },
    { path: "city", label: "City" },
    {
      path: "address",
      label: "Address"
    },
    {
      path: "manager_name",
      label: "Manager"
    },
    { path: "email", label: "Email" }
  ];

  state = {
    stores: []
  };
  async componentDidMount() {
    const { selectedYear, category } = this.props.match.params;
    const year = selectedYear.substring(0, 4);
    const month = selectedYear.substring(5, 7);
    console.log(month);
    const { data: stores } = await axios.get(
      `${api}/reports/stateVolume/${
        this.props.match.params.state
      }/${year}/${month}/${category}`
    );
    this.setState({
      stores
    });
  }

  render() {
    console.log(this.props.match.params);
    const { selectedYear, category, state } = this.props.match.params;
    return (
      <div>
        <h3>
          {state}'s Store Info
          <button
            className="btn btn-warning mx-5"
            onClick={() => {
              this.props.history.push(
                "/reports-state-with-highest-volume-for-category"
              );
            }}
          >
            Back
          </button>
        </h3>
        <p>
          In <b>{selectedYear || ""}</b>,<b>{state}</b> sold the most number of{" "}
          <b>{category}</b> products.
        </p>
        <Table columns={this.columns} data={this.state.stores} />
      </div>
    );
  }
}
export default StateWithHighestVolume;
