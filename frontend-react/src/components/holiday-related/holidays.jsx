import React, { Component } from "react";
import { api } from "../../config";
import axios from "axios";

class Holiday extends Component {
  state = {
    holidays: []
  };
  componentDidMount() {
    axios.get(`${api}/holidays`).then(res => {
      this.setState({
        holidays: [...res.data]
      });
    });
  }
  addHoliday = () => {
    this.props.history.push("/holidays/add-holiday");
  };
  handleDelete = async holiday => {
    const originalHolidays = this.state.holidays;
    console.log(holiday);
    try {
      await axios.delete(`${api}/holidays/${holiday.holiday_date}`);
      axios.get(`${api}/holidays`).then(res => {
        this.setState({
          holidays: [...res.data]
        });
      });
    } catch {
      alert("Delete failed");
      this.setState({ holidays: originalHolidays });
    }
  };
  render() {
    return (
      <main>
        <button
          type="button"
          className="btn text-center btn-secondary btn-lg btn-block"
          onClick={() => this.addHoliday()}
        >
          Add Holiday
        </button>

        <table className="table table-striped ">
          <thead>
            <tr>
              <th>Holiday Name</th>
              <th>Holiday Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {this.state.holidays.map(holiday => {
              return (
                <tr key={holiday.holiday_date + holiday.holiday_name}>
                  <th>{holiday.holiday_name}</th>
                  <th>{holiday.holiday_date}</th>
                  <th>
                    <button
                      className="btn btn-danger"
                      onClick={this.handleDelete.bind(this, holiday)}
                    >
                      Delete
                    </button>
                  </th>
                </tr>
              );
            })}
          </tbody>
        </table>
      </main>
    );
  }
}

export default Holiday;
