import React, { Component } from "react";
import { Link } from "react-router-dom";
class NavBar extends Component {
  state = {
    tags: [
      ["holidays", "Holiday Management"],
      ["managers", "Manager Update"],
      ["cities", "City Management"]
    ],
    reports: [
      ["manufacturer-product", "Manufacturer's Product Report"],
      ["category", "Category Report"],
      ["gps", "GPS Report"],
      [
        "store-revenue-by-year-by-state",
        "Store Revenue by Year by State Report"
      ],
      ["ac", "Air Conditioner on Groundhog Day Report"],
      [
        "state-with-highest-volume-for-category",
        "State with Highest Volume for each Category Report"
      ],
      ["revenue-by-population", "Revenue by Population"]
    ]
  };
  styles = {
    fontSize: "10px", //or <fontSize:10> but it is different unit
    fontWeight: "bold"
  };
  render() {
    return (
      <ul
        className="Nav Nav-flex-column"
        style={{ position: "absolute", top: 120, width: 250 }}
      >
        {this.state.tags.map(tag => (
          <li key={tag[0]}>
            <Link to={"/" + tag[0]}>{tag[1]}</Link>
          </li>
        ))}
        <li>
          <Link to="/reports">Reports</Link>
        </li>
        <ul>
          {this.state.reports.map(report => {
            return (
              <li key={report[0]}>
                <Link to={"/reports-" + report[0]}>{report[1]}</Link>
              </li>
            );
          })}
        </ul>
      </ul>
    );
  }
}

export default NavBar;
