import React from "react";
import { Navbar, Nav, Form, FormControl, Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const NavTest = () => {
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

export default NavTest;
