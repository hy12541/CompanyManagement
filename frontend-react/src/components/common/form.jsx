import React, { Component } from "react";
import Joi from "joi-browser";
import Input from "./input";
import Select from "./select";

class Form extends Component {
  state = { data: {}, errors: {} };
  //validating a form
  validate = () => {
    const result = Joi.validate(this.state.data, this.schema, {
      abortEarly: false
    });
    //不加abortEarly，它会只找到第一个就报错。
    const errors = {};
    if (!result.error) return null;
    for (let item of result.error.details) errors[item.path[0]] = item.message;
    return errors;
  };
  //validatig a field
  validateProperty = ({ name, value }) => {
    const obj = { [name]: value };
    const schema = { [name]: this.schema[name] };
    const { error } = Joi.validate(obj, schema);
    return error ? error.details[0].message : null;
  };
  handleSubmit = e => {
    e.preventDefault(); //prevent the full page reload caused by submitting the form
    const errors = this.validate();
    this.setState({ errors: errors || {} });
    if (errors) return;
    this.doSubmit();
  };

  handleChange = ({ currentTarget: input }) => {
    const errors = { ...this.state.errors };
    const errorMessage = this.validateProperty(input);
    if (errorMessage) errors[input.name] = errorMessage;
    else delete errors[input.name];
    const data = { ...this.state.data };
    data[input.name] = input.value;
    this.setState({ data, errors });
  }; //controlled element,把input的东西，存进了state
  renderSelect(name, label, options) {
    const { data, errors } = this.state;
    return (
      <Select
        options={options}
        name={name}
        value={data[name]}
        onChange={this.handleChange}
        label={label}
        error={errors[name]}
      />
    );
  }
  renderInput = (name, label, type = "text") => {
    const { data, errors } = this.state;
    return (
      <Input
        type={type}
        name={name}
        value={data[name]}
        onChange={this.handleChange}
        label={label}
        error={errors[name]}
      />
    );
  };

  renderButton = label => {
    return (
      <button disabled={this.validate()} className="btn btn-primary">
        {label}
      </button>
    );
  };
}
export default Form;
