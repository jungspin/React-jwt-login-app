import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { createStore } from "redux";
import reducer from "./store";
import { Provider } from "react-redux";

const store = createStore(reducer);

ReactDOM.render(
  // 1번

  <BrowserRouter>
    <Provider store={store}>
      <App />
    </Provider>
  </BrowserRouter>,
  document.getElementById("root"),
);
