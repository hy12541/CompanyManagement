import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import Manager from "./components/manager-related/manager";
import AddManager from "./components/manager-related/addManager";
import City from "./components/city-related/city";
import Home from "./components/home";
import Holiday from "./components/holiday-related/holidays";
import AddHoliday from "./components/holiday-related/addHoliday";
import CityDetail from "./components/city-related/cityDetail";
import NotFound from "./components/not-found";
import "antd/dist/antd.css";
import ManufacturerProduct from "./components/reports/report1";
import CategoryReport from "./components/reports/report2";
import GPSUnitReport from "./components/reports/report3";
import StoreRevenueByYearByState from "./components/reports/report4";
import ACOnGroundhogDay from "./components/reports/report5";
import StateWithHighestVolumeForCategory from "./components/reports/report6";
import RevenueByPopulation from "./components/reports/report7";
import Reports from "./components/reports/report";
import ManagerDetail from "./components/manager-related/managerDetail";
import ManufacturerProductDetail from "./components/reports/report1detail";
import StateWithHighestVolume from "./components/reports/report6Detail";

ReactDOM.render(
  <BrowserRouter>
    <App>
      <Switch>
        <Route path="/managers/add-manager" component={AddManager} />
        <Route path="/managers/:email" component={ManagerDetail} />
        <Route path="/managers" component={Manager} />
        <Route path="/home" component={Home} />
        <Route path="/cities/:state_name/:city" component={CityDetail} />
        <Route path="/cities" component={City} />
        <Route path="/holidays/add-holiday" component={AddHoliday} />
        <Route path="/holidays" component={Holiday} />
        <Route
          path="/reports-revenue-by-population"
          component={RevenueByPopulation}
        />
        <Route
          path="/reports-manufacturer-product/:manufacturer/:total_number/:avg_price/:min_price/:max_price"
          component={ManufacturerProductDetail}
        />
        <Route
          path="/reports-manufacturer-product"
          component={ManufacturerProduct}
        />
        <Route path="/reports-category" component={CategoryReport} />
        <Route path="/reports-gps" component={GPSUnitReport} />
        <Route
          path="/reports-store-revenue-by-year-by-state"
          component={StoreRevenueByYearByState}
        />
        <Route path="/reports-ac" component={ACOnGroundhogDay} />
        <Route
          path="/reports-state-with-highest-volume-for-category/:state/:category/:selectedYear"
          component={StateWithHighestVolume}
        />
        <Route
          path="/reports-state-with-highest-volume-for-category"
          component={StateWithHighestVolumeForCategory}
        />
        <Route
          path="/reports-revenue-by-population"
          component={RevenueByPopulation}
        />
        <Route path="/reports" component={Reports} />
        <Route path="/" exact component={Home} />
        <Route path="/not-found" component={NotFound} />
        <Redirect to="/not-found" />
      </Switch>
    </App>
  </BrowserRouter>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
