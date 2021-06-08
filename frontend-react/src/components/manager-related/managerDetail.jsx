import React, { Component } from "react";
import { api } from "../../config";
import axios from "axios";
import AssignStore from "./assignStore";

class ManagerDetail extends Component {
  state = { activeManager: [] };

  componentDidMount = () => {
    const email = this.props.match.params.email;
    axios.get(`${api}/managers/activeManager/${email}`).then(res => {
      this.setState({
        activeManager: [...res.data]
      });
    });
  };

  handleDelete = async activeManager => {
    const originalManagement = this.state.ActiveManager;
    const email = this.props.match.params.email;
    console.log(activeManager);
    try {
      await axios.delete(
        `${api}/managers/delete/email/${email}/storeID/${
          activeManager.storeID
        }`,
        activeManager
      );
      axios.get(`${api}/managers/activeManager/${email}`).then(res => {
        this.setState({
          activeManager: [...res.data]
        });
      });
    } catch {
      alert("Delete failed");
      this.setState({ holidays: originalManagement });
    }
  };

  render() {
    const { match, history } = this.props;
    const { activeManager } = this.state;
    return (
      <div>
        <h3>
          {match.params.email} is managing
          <button
            className="btn btn-warning mx-5"
            onClick={() => {
              history.push("/managers");
            }}
          >
            Back
          </button>
        </h3>
        <table>
          <thead>
            <tr>
              <th>StoreID</th>
              <th />
            </tr>
          </thead>
          <tbody>
            {activeManager.map(manager => (
              <tr key={manager.storeID + manager.email}>
                <td>{manager.storeID}</td>
                <td
                  onClick={() => this.handleDelete(manager)}
                  className="btn btn-danger btn-sm m-2"
                >
                  Unassign
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <br />
        <AssignStore
          history={history}
          email={match.params.email}
          afterAssign={this.componentDidMount}
        />
      </div>
    );
  }
}

export default ManagerDetail;
