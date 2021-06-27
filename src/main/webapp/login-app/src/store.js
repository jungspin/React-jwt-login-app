// 상태와 액션이 있어야함
// 상태가 하나가 아닐 수 있기때문에 {}

// 변수로 만들어 놓고 사용하면 실수할 일이 적어진다
// 내가 변수명 오류낼 경우 컴파일 시 오류가 날테니까
const USERLOGIN = "USERLOGIN";
const USERLOGOUT = "USERLOGOUT";

// 액션 -> 타입을 리턴
// export 붙으면 외부에서 접근 가능
export const userlogin = (userData) => {
  // 상태를 바로 변경 시키지 않음 -> not number: 1
  return {
    type: USERLOGIN,
    payload: userData,
    //payload: 변수에 값 받아 올 때
  };
};

export const userlogout = () => {
  return {
    type: USERLOGOUT,
  };
};

// 상태
// 상태에 그 true, false 가 들어가면 되나?
const initstate = {
  isLogin: false,
  user: {
    id: "",
    username: "",
    //password: "",
    email: "",
    role: "",
  }, // user 상태 여기서 관리하라고?
};

//const

// 외부에서는 결국 얘만 때림
// 얘는 직접 호출 못함. 다른걸 통해서 때릴거임
// 얘를 직접 호출하면 상태만 변경되고 동기화는 안됨
// 상태 만들고 -> 액션 만들고 (타입 만들기) -> reducer
const reducer = (state = initstate, action) => {
  switch (action.type) {
    case USERLOGIN:
      return { isLogin: true, user: action.payload };
    case USERLOGOUT:
      return {
        isLogin: false,
        user: {
          id: "",
          username: "",
          //password: "",
          email: "",
          role: "",
        },
      };
    default:
      return state;
  }
};

// 외부에서 때릴 수 있음
export default reducer;
