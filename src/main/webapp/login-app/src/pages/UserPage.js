// view 는 page 붙여주는게 나중에 파악하기 좋다
import { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
//import { useSelector } from "react-redux";

const UserPage = () => {
  const [loginUser, setLoginUser] = useState({
    id: "",
    username: "",
    email: "",
    role: "",
  });

  useEffect(() => {
    fetch("http://localhost:8080/user", {
      method: "GET",
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    })
      .then((res) => res.json())
      .then((res) => {
        console.log(res.data);
        setLoginUser(res.data);
      })
      .catch((error) => {
        // then 내부에서 실패하면!! -> 통신과 상관이 없음!!!! 파싱을 실패한다거나~~
        console.log("에러 발생함");
        console.log(error);
      });
  }, []);
  return (
    <div>
      <h1>User Page</h1>
      <hr />
      <h1>유저 정보 테이블</h1>
      <div>
        <Card style={{ width: "18rem" }}>
          <Card.Body>
            <Card.Title>
              {loginUser.id} {loginUser.username}
            </Card.Title>
            <Card.Subtitle className="mb-2 text-muted">
              {loginUser.role}
            </Card.Subtitle>
            <Card.Text>{loginUser.email}</Card.Text>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
};

export default UserPage;
