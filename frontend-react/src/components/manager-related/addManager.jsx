import React from "react";
import Joi from "joi-browser";
import Form from "../common/form";
import axios from "axios";
import { api } from "../../config";

class AddManager extends Form {
  state = { data: { email: "", name: "" }, errors: {} };

  schema = {
    email: Joi.string()
      .email()
      .required()
      .label("Email"),
    name: Joi.string()
      //.token()
      .regex(/^[a-zA-Z '-]+$/)
      //.regex(/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]$/)
      .required()
      .error(errors => {
        return {
          message:
            "Please eneter a valid name: first name, a space, and then last name, such as John Smith"
        };
      })
      .required()
      .label("Name")
  };

  doSubmit = e => {
    //call the server
    console.log("submit");
    axios
      .post(`${api}/managers`, {
        email: this.state.data.email,
        manager_name: this.state.data.name
      })
      .then(response => {
        console.log(response);
        this.props.history.push("/managers");
      })
      .catch(error => {
        alert(
          "ERROR: " +
            this.state.data.email +
            " is already a manager in the Database!"
        );
      });
  };

  render() {
    return (
      <div>
        <h3 className="text-center">Add Manager</h3>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("email", "Email")}
          {this.renderInput("name", "Name")}
          {this.renderButton("Add")}
        </form>
      </div>
    );
  }
}

export default AddManager;
