import React from "react";
import axios from "axios";
import { api } from "../../config";
import Form from "../common/form";
import Joi from "joi-browser";

class AssignStore extends Form {
  state = { data: { storeID: "" }, errors: {}, stores: [] };
  schema = {
    storeID: Joi.string()
      .required()
      .label("StoreID")
  };
  async componentDidMount() {
    const { data } = await axios.get(`${api}/statistics/stores`);
    this.setState({ stores: data });
    console.log(data);
  }

  doSubmit = e => {
    console.log(this.state.stores);
    //console.log(e.target);
    //if ({ storeID: this.state.data.storeID } in this.state.stores)

    const { email } = this.props;
    axios
      .post(`${api}/managers/activeManager`, {
        email: email,
        storeID: this.state.data.storeID
      })
      .then(response => {
        this.props.afterAssign(); //谁能看懂算我输
        this.setState({ data: { storeID: "" } });
      })
      .catch(error => {
        console.log(error);
        alert("Store " + this.state.data.storeID + " is already assigned!");
      });
  };
  render() {
    const { stores } = this.state;
    return (
      <form onSubmit={this.handleSubmit}>
        <select
          name="storeID"
          id="storeID"
          className="form-control"
          onChange={e => this.handleChange(e)}
        >
          <option value="">Please select a Store</option>
          {stores.map(store => {
            return (
              <option key={store.storeID} value={store.storeID}>
                {store.storeID}
              </option>
            );
          })}
        </select>
        {this.renderButton("Assign")}
      </form>
    );
  }
}

export default AssignStore;
