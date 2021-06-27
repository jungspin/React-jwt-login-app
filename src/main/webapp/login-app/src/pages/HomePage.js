import React from "react";
import { useSelector } from "react-redux";

//import { useSelector } from "react-redux";

// 리액트 클래스 컴포넌트 기반 (X)
// 리액트 hooks() => 함수형 기반 (O) => useXX 함수를 사용 가능!!!!!!
const HomePage = () => {
  // fetch 를 기다리게 할거야 -> 동기적으로 진행되게

  const { user } = useSelector((store) => store);
  //const dispatcher = useDispatch();

  // 생성자 (조건이 필요한 생성자)
  // 생성자는 한번만 실행(생성) 되니까
  // 앱이 실행될때 실행됨
  // 의존하고 있는 데이터가 변경될 때만 실행됨

  // fetch 실행할 필요가 없다. 로그인할때 데이터 받아왔잖아

  return (
    <div>
      <h1>Home Page</h1>
      <h3>접속한 유저 이름 : {user.username} </h3>
      <hr />
    </div>
  );
};

export default HomePage;
