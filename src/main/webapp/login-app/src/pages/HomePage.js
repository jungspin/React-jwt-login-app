import React from "react";
import { useEffect } from "react";
//import { useSelector } from "react-redux";

//import { useSelector } from "react-redux";

// 리액트 클래스 컴포넌트 기반 (X)
// 리액트 hooks() => 함수형 기반 (O) => useXX 함수를 사용 가능!!!!!!
const HomePage = () => {
  // fetch 를 기다리게 할거야 -> 동기적으로 진행되게

  //const { user } = useSelector((store) => store);
  //const dispatcher = useDispatch();

  // 생성자 (조건이 필요한 생성자)
  // 생성자는 한번만 실행(생성) 되니까
  // 앱이 실행될때 실행됨
  // 의존하고 있는 데이터가 변경될 때만 실행됨

  // fetch 실행할 필요가 없다. 로그인할때 데이터 받아왔잖아
  useEffect(() => {
    fetch("http://localhost:8080/user", {
      method: "GET",
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    }) // 메모리가 데이터 받아옴 cpu는 내려감
      .then((res) => res.json()) // then 에는 method 가 들어와야됨 -> 문자열로 들어온 데이터를 자바스크립트 객체로 파싱
      .then((res) => {
        // 파싱이 끝난 값이 여기 들어옴
        console.log("통신 성공");
        console.log(res.data);
      })
      .catch((error) => {
        // then 내부에서 실패하면!! -> 통신과 상관이 없음!!!! 파싱을 실패한다거나~~
        console.log("에러 발생함");
        console.log(error);
      });
  }); // 의존하는 데이터가 변경될때만 생성자를 실행시킬 수 있음-> 따라서 비워두면 한번만 실행하겠다는 의미
  // React.DependencyList -> 검색 시 활용하기 좋음!!
  return (
    <div>
      <h1>Home Page</h1>
      <h3>접속한 유저 이름 : </h3>
      <hr />
    </div>
  );
};

export default HomePage;
