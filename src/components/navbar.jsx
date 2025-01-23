import { NavLink } from "react-router-dom";
import logo2 from '../assets/p-trans.png';
import logotxtwhite from '../assets/p-trans-white-txt.png';

const Navbar = ({ openModal, openLoginModal }) => {
    return (
        <nav className="navbar navbar-translucent fixed-top" style={{ backgroundColor: 'rgba(139, 83, 55, 0.7)' }}>
            <div className="container-fluid">
                {/* Offcanvas */}
                <div className="offcanvas offcanvas-start" style={{ backgroundColor: 'rgba(255, 255, 255, 0.8)' }} tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
                    <div className="offcanvas-header center-img">
                        <img src={logo2} alt="Inspire Library Logo" style={{ width: '200px', height: 'auto', marginRight: '10px' }} />
                    </div>
                    <div className="offcanvas-body">
                        <ul className="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#">Libros</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link active" href="#">Autores</a>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dropdown
                                </a>
                                <ul className="dropdown-menu">
                                    <li><a className="dropdown-item" href="#">Action</a></li>
                                    <li><a className="dropdown-item" href="#">Another action</a></li>
                                    <li>
                                        <hr className="dropdown-divider" />
                                    </li>
                                    <li><a className="dropdown-item" href="#">Something else here</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

                <div className="d-flex align-items-center justify-content-between w-100">
                    <button className="navbar-toggler text" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <NavLink className="navbar-brand ms-3" to="/homepage">
                        <img src={logotxtwhite} alt="Inspire Library Logo" style={{ width: '150px', height: 'auto' }} />
                    </NavLink>

                    <div className="d-flex ms-auto">
                        <button className="btn btn-primary ms-3" onClick={openModal}>Registrarse</button>
                        <button className="btn btn-outline-light ms-3" onClick={openLoginModal}>Iniciar sesión</button>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export { Navbar };
