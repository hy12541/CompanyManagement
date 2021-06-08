import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";
import { paginate } from "../common/paginate";
import Pagination from "../common/pagination";

class StoreRevenueByYearByState extends Component {
  state = {
    selectedState: null,
    result: null,
    data: [],
    states: [],
    pageSize: 20,
    currentPage: 1
  };

  async componentDidMount() {
    const { data } = await axios.get(`${api}/statistics/states`);
    console.log(data);
    this.setState({ states: data });
  }

  columns = [
    {
      path: "storeID",
      label: "Store ID"
    },
    { path: "address", label: "Address" },
    {
      path: "city",
      label: "City"
    },
    { path: "sold_year", label: "Year" },
    {
      path: "total_revenue",
      label: "Revenue"
    }
  ];

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  render() {
    const { selectedState, states, data, currentPage, pageSize } = this.state;
    const perPage = paginate(data, currentPage, pageSize);

    return (
      <div>
        <h3>Store Revenue by Year by State Report</h3>
        <select
          name=""
          id=""
          className="custom-select"
          onChange={event => {
            this.setState({ selectedState: event.target.value });
            axios
              .get(`${api}/reports/storeyearlyrev/${event.target.value}`)
              .then(res => {
                this.setState({
                  data: [...res.data]
                });
              });
          }}
        >
          <option value="">Please select a State</option>
          {states.map(state => {
            return (
              <option key={state.state_name} value={state.state_name}>
                {state.state_name}
              </option>
            );
          })}
        </select>
        {selectedState && <Table columns={this.columns} data={perPage} />}
        <Pagination
          itemsCount={data.length}
          pageSize={pageSize}
          currentPage={currentPage}
          onPageChange={this.handlePageChange}
        />
      </div>
    );
  }
}

export default StoreRevenueByYearByState;
// states = [
//   "Alabama",
//   "Alaska",
//   "Arizona",
//   "Arkansas",
//   "California",
//   "Colorado",
//   "Connecticut",
//   "Delaware",
//   "Florida",
//   "Georgia",
//   "Hawaii",
//   "Idaho",
//   "Illinoi",
//   "Indiana",
//   "Iowa",
//   "Kansas",
//   "Kentucky",
//   "Louisiana",
//   "Maine",
//   "Maryland",
//   "Massachusetts",
//   "Michigan",
//   "Minnesota",
//   "Mississippi",
//   "Missouri",
//   "Montan",
//   "Nebraska",
//   "Nevada",
//   "New Hampshire",
//   "New Jersey",
//   "New Mexico",
//   "New York",
//   "North Carolina",
//   "North Dakota",
//   "Ohio",
//   "Oklahoma",
//   "Oregon",
//   "Pennsylvania",
//   "Rhode Island",
//   "South Carolina",
//   "South Dakota",
//   "Tennessee",
//   "Texas",
//   "Utah",
//   "Vermont",
//   "Virginia",
//   "Washington",
//   "West Virginia",
//   "Wisconsin",
//   "Wyoming"
// ];
