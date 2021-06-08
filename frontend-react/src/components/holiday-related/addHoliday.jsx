import React from "react";
import axios from "axios";
import { api } from "../../config";
import Form from "../common/form";
import Joi from "joi-browser";

class AddHoliday extends Form {
  state = { data: { holiday_name: "", date: "" }, errors: {} };
  schema = {
    date: Joi.date()
      .required()
      .label("Date"),
    holiday_name: Joi.string()
      .required()
      .label("Holiday Name")
  };

  doSubmit = e => {
    const date = this.state.data.date + "T05:00:00.000Z";
    axios
      .post(`${api}/holidays`, {
        holiday_name: this.state.data.holiday_name,
        holiday_date: date
      })
      .then(response => {
        console.log(response);
        this.props.history.push("/holidays");
      })
      .catch(error => {
        alert(
          this.state.data.date +
            " is already in the holiday list. If you want to change the holiday name, please go back to the holiday list, delete it first and then come back assign this date with a new name!"
        );
        console.log(error);
      });
  };

  render() {
    return (
      <div>
        <h3 className="text-center">Add Holiday</h3>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("date", "Date", "date")}
          {this.renderInput("holiday_name", "Holiday Name")}
          {this.renderButton("Add")}
        </form>
      </div>
    );
  }
}

export default AddHoliday;
