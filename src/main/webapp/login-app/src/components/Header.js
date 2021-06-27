import React from "react";
import { Navbar, Nav, Form, FormControl, Button } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { userlogout } from "../store";

const Header = () => {
  // 구조분해할당
  const { isLogin } = useSelector((store) => store);
  //const dispatcher = useDispatch();

  const logout = () => {
    localStorage.removeItem("Authorization");
    dispatcher(userlogout());
  };

  // 디스패처로 reducer 접근
  const dispatcher = useDispatch();

  return (
    <>
      <Navbar bg="primary" variant="dark">
        <Link to="/" className="navbar-brand">
          LoginApp
        </Link>
        <Nav className="mr-auto">
          <Link to="/" className="nav-link">
            Home
          </Link>
          <Link to="/user" className="nav-link">
            User
          </Link>
          {isLogin ? (
            <div className="nav-link" onClick={logout}>
              logout
            </div>
          ) : (
            <Link to="/login" className="nav-link">
              Login
            </Link>
          )}
        </Nav>
        <Form inline>
          <FormControl type="text" placeholder="Search" className="mr-sm-2" />
          <Button variant="outline-light">Search</Button>
        </Form>
      </Navbar>

      <br />
    </>
  );
};

export default Header;
