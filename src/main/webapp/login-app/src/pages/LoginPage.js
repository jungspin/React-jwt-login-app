import React from "react";
import { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { userlogin } from "../store";

// 여기서 header에 그림 변경해야대
const LoginPage = (props) => {
  // 리덕스는 전역상태 관리할 때!
  const dispatcher = useDispatch();

  const [loginUser, setLoginUser] = useState({
    username: "",
    password: "",
  }); // 오브젝트 타입으로 초기화

  const login = (e) => {
    // event 를 받음
    e.preventDefault(); // submit 액션 차단
    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(loginUser),
    })
      .then((res) => {
        //console.log(res.headers.get("Authorization"));
        let jwtToken = res.headers.get("Authorization");
        localStorage.setItem("Authorization", jwtToken);
        // 여기서 백날 출력해봐라 똑바로 나오는가
        return res.json(); // 실제로는 이렇게 하지마라~~~json으로 받아라
      })
      .then((res) => {
        console.log(res);
        // body 데이터가 들어옴
        if (res.code === 1) {
          console.log("통신성공");
          // 화면 동기화 해야됨
          dispatcher(userlogin(res.data)); // res.data 를 넘겨줘야함. 스프링에서 어떻게 보내는지 보자
          props.history.push("/");
        } else {
          console.log("통신 실패");
        }
      });
  };

  const changeInput = (e) => {
    // console.log(e.target.value);

    setLoginUser({
      ...loginUser,
      [e.target.name]: e.target.value, // name 프롬프트?? 변수이름(키값)을 동적으로 만들 수 있대!!! (compute property names)
    });

    //console.log(loginUser);
    // 새로 그려질 때(상태값 바뀔때) 값이 바뀜. 여기 있으면 새로 그려지기 전의 상태를 보여줌
  };

  /*
  const changeUsername = (e) => {
    // console.log(e.target.value);

    setLoginUser({
      username: e.target.value,
      password: loginUser.password,
    });
  };

  const changePassword = (e) => {
    setLoginUser({
      username: loginUser.username,
      password: e.target.value,
    });

  };
*/
  console.log(loginUser); // 여기로 옮겨주면 잘 확인 됨

  return (
    <Container>
      <Form>
        <Form.Group>
          <Form.Label>Username</Form.Label>
          <Form.Control
            name="username"
            type="text"
            placeholder="Enter Username"
            onChange={changeInput}
          />
        </Form.Group>

        <Form.Group>
          <Form.Label>Password</Form.Label>
          <Form.Control
            name="password"
            type="password"
            placeholder="Enter Password"
            onChange={changeInput}
          />
        </Form.Group>

        <Button variant="primary" type="submit" onClick={login}>
          로그인
        </Button>
      </Form>
    </Container>
  );
};

export default LoginPage;
