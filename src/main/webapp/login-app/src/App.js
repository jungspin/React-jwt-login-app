import "bootstrap/dist/css/bootstrap.min.css";
import { Route } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import UserPage from "./pages/UserPage";

function App() {
  return (
    <div>
      {/* nav 는 어디서나 다 있으니까 */}
      <Header />
      {/* 2번. 주소 설계 */}
      {/* exact={true} 없으면 / 이하 다 낚아 채서 user로 못가게 됨 (/* 랑 같은 의미) */}
      {/* 페이지를 바꿔치기 하는 것 */}
      <Route path="/" exact={true} component={HomePage} />
      <Route path="/user" exact={true} component={UserPage} />
      <Route path="/login" exact={true} component={LoginPage} />
    </div>
  );
}

export default App;
