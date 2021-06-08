import React, { Component } from "react";
import axios from "axios";
import Form from "../common/form";
import Joi from "joi-browser";
import { api } from "../../config";

class CityDetail extends Form {
  state = { city: [], data: { population: "" }, errors: {} };
  schema = {
    population: Joi.number()
      .required()
      .label("Population")
  };
  componentDidMount() {
    const city = this.props.match.params.city;
    const state = this.props.match.params.state_name;
    axios.get(`${api}/cities/population/${state}/${city}`).then(res => {
      const population = res.data[0].population;
      console.log(population);
      this.setState({
        city: [...res.data],
        data: { population }
      });
    });
  }
  doSubmit = e => {
    const { params } = this.props.match;
    axios
      .put(`${api}/cities`, [
        {
          state_name: params.state_name,
          city: params.city,
          population: this.state.data.population
        },
        this.state.city[0]
      ])
      .then(response => {
        this.props.history.push("/cities");
      })
      .catch(error => {
        console.log(error);
      });
  };

  render() {
    const { match } = this.props;
    return (
      <div>
        <h3>
          {match.params.state_name}
          <br />
          {match.params.city}
        </h3>
        <br />
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("population", "Population", "number")}
          {this.renderButton("Save")}
        </form>
      </div>
    );
  }
}
//   state = {};
//   handleSave = async () => {
//     const newCity = {};
//     const { data } = await axios.put(`http://3.16.108.85:8080/cities`, newCity);
//     this.props.history.push("/cities");
//   };
//   handleBack = () => {
//     this.props.history.push("/cities");
//   };
//   render() {
//     return (
//       <div>
//         <h1>City Detail Information</h1>
//         <form>
//           <div className="form-group">
//             <label htmlFor="state">State</label>
//             <input id="state" type="text" className="formcontrol" />
//           </div>
//           <div className="form-group">
//             <label htmlFor="city">City</label>
//             <input id="city" type="text" className="formcontrol" />
//           </div>
//           <div className="form-group">
//             <label htmlFor="population" >Population</label>
//             <input id="population" type="number" className="formcontrol" />
//           </div>
//         <button className="btn btn-primary" onClick={this.handleSave}>Save</button>
//         <button className="btn btn-primary" onClick={this.handleBack}>Back</button>
//         </form>
//       </div>
//     );
//   }
// }

export default CityDetail;
