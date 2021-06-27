// view 는 page 붙여주는게 나중에 파악하기 좋다
import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";

const UserPage = () => {
  const [users, setUsers] = useState([]); // 변수가 많으니까 배열

  // body, contentType, method
  // 자바스크립트 오브젝트 => JSON.parse(), JSON.stringify()
  useEffect(() => {
    fetch("http://localhost:8080/user/a")
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
        setUsers(res);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <h1>User Page</h1>
      <hr />
      <h1>유저 정보 테이블</h1>
      {users.map((user) => (
        <div>
          <Card style={{ width: "18rem" }}>
            <Card.Body>
              <Card.Title>
                {user.id} : {user.username}
              </Card.Title>
              <Card.Subtitle className="mb-2 text-muted">
                {user.password}
              </Card.Subtitle>
              <Card.Text>{user.email}</Card.Text>
            </Card.Body>
          </Card>
        </div>
      ))}
    </div>
  );
};

export default UserPage;
