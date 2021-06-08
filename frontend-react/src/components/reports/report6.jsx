import React, { Component } from "react";
import { api } from "./../../config";
import axios from "axios";
import Table from "../common/table";
import { Link } from "react-router-dom";
import { DatePicker } from "antd";
import moment from "moment";

class StateWithHighestVolumeForCategory extends Component {
  columns = [
    {
      path: "category",
      label: "Category"
    },
    {
      path: "state_name",
      label: "State",
      content: data => (
        <Link
          to={`/reports-state-with-highest-volume-for-category/${
            data.state_name
          }/${data.category}/${data.selectedYear}
          `}
        >
          {data.state_name}
        </Link>
      )
    },
    { path: "max_units_sold", label: "Sold Units" }
  ];

  state = {
    selectedMonth: "",
    data: [],
    dateRange: [],
    maxDate: ""
  };

  async componentDidMount() {
    //console.log(this.props);
    const { data } = await axios.get(`${api}/statistics/dateRange`);
    const minDate = data[0].min_date.substring(0, 7);
    const maxDate_display = data[0].max_date.substring(0, 7);
    const maxDate =
      maxDate_display.substring(0, 5) +
      (parseInt(maxDate_display.substring(5, 7)) + 1).toString();
    this.setState({
      dateRange: [minDate, maxDate],
      maxDate: maxDate_display
    });
  }

  handlePageChange = page => {
    this.setState({ currentPage: page });
  };
  handleSelectMonth = date => {
    if (date !== null) {
      this.setState({ selectedMonth: date });
      // console.log(date);
      //console.log(typeof date.month());
      //console.log(date.year());
      axios
        .get(`${api}/reports/stateVolume/${date.year()}/${date.month() + 1}`)
        .then(res => {
          const data = res.data.map(record => {
            record.selectedYear = this.state.selectedMonth.format("YYYY-MM");
            console.log(record);
            return record;
          });
          this.setState({ data });
        });
    }
  };

  render() {
    const { data, dateRange, maxDate } = this.state;

    console.log(maxDate);

    const { MonthPicker } = DatePicker;
    return (
      <div>
        <h3>State with Highest Volume for each Category Report</h3>
        <p>
          Select Month from {dateRange[0]} to {maxDate}
        </p>

        <MonthPicker
          placeholder="select a month"
          onChange={this.handleSelectMonth}
          defaultValue={new moment("2002-10-02")}
          disabledDate={d =>
            !d ||
            d.isBefore(this.state.dateRange[0]) ||
            d.isAfter(this.state.dateRange[1])
          }
        />
        {this.state.data[0] && <Table columns={this.columns} data={data} />}
      </div>
    );
  }
}

export default StateWithHighestVolumeForCategory;
