// view 는 page 붙여주는게 나중에 파악하기 좋다
//import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import { useSelector } from "react-redux";

const UserPage = () => {
  const { user } = useSelector((store) => store);

  return (
    <div>
      <h1>User Page</h1>
      <hr />
      <h1>유저 정보 테이블</h1>
      <div>
        <Card style={{ width: "18rem" }}>
          <Card.Body>
            <Card.Title>
              {user.id} {user.username}
            </Card.Title>
            <Card.Subtitle className="mb-2 text-muted">
              {user.role}
            </Card.Subtitle>
            <Card.Text>{user.email}</Card.Text>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
};

export default UserPage;
