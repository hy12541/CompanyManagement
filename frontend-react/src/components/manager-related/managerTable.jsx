import React, { Component } from "react";
import { Link } from "react-router-dom";
import Table from "../common/table";
import axios from "axios";
import { api } from "../../config";
import { Icon } from "antd";

class ManagerTable extends Component {
  columns = [
    {
      path: "email",
      label: "Email",
      content: manager => (
        <Link to={`/managers/${manager.email}`}>{manager.email}</Link>
      )
    },
    { path: "manager_name", label: "Name" },
    {
      key: "stores",
      label: "IsActive",
      content: manager => manager.stores > 0 && <Icon type="check" />
    },
    {
      key: "delete",
      label: "Delete",
      content: manager => (
        <button
          onClick={() => this.props.onDelete(manager)}
          className="btn btn-danger btn-sm m-2"
          disabled={manager.stores > 0}
        >
          Delete
        </button>
      )
    }
  ];

  render() {
    const { managers } = this.props;
    return <Table columns={this.columns} data={managers} />;
  }
}

export default ManagerTable;
