import logo from '../assets/logo.png';
import { Link } from "react-router-dom";
function HomePage() {
  return (
    <header className="App-header">
        <Link to="/comienzo"><img src={logo} className="App-logo" alt="logo" /></Link>
        <p>
          Bienvenidos a Biblioteca NC23 94
        </p>
      </header>
  );
}
export default HomePage ;