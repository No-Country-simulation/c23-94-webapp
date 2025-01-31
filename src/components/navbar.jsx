import React from 'react';
import { NavLink, useNavigate } from "react-router-dom";
import { Offcanvas } from "bootstrap/dist/js/bootstrap.bundle.min.js";
import logo2 from "../assets/p-trans.png";
import logotxtwhite from "../assets/p-trans-white-txt.png";
import profilePic from "../assets/profile-picture-default.png";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

const Navbar = ({ openModal, openLoginModal }) => {
    const navigate = useNavigate();
    const closeOffcanvas = () => {
        const offcanvasElement = document.getElementById("offcanvasNavbar");
        const bsOffcanvas = Offcanvas.getInstance(offcanvasElement);
        if (bsOffcanvas) bsOffcanvas.hide();
    };

    const username = localStorage.getItem('username');
    const token = localStorage.getItem('token');

    const handleLogout = () => {
        localStorage.removeItem('username');
        localStorage.removeItem('token');
        navigate("/comienzo")
    };

    return (
        <nav className="navbar navbar-translucent fixed-top" style={{ backgroundColor: 'rgba(139, 83, 55, 0.7)' }}>
            <div className="container-fluid">
                <div className="offcanvas offcanvas-start" style={{ backgroundColor: 'rgba(255, 255, 255, 0.8)' }} tabIndex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
                    <div className="offcanvas-header center-img">
                        <NavLink to= "/comienzo" onClick={closeOffcanvas}>
                        <img src={logo2} alt="Inspire Library Logo" style={{ width: '200px', height: 'auto', marginRight: '10px' }} />
                        </NavLink>
                    </div>
                    <div className="offcanvas-body">
                        <ul className="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <li className="nav-item">
                                <NavLink className="nav-link active" to="/library" onClick={closeOffcanvas}>Libros</NavLink>
                                <NavLink className="nav-link active" to="/authors" onClick={closeOffcanvas}>Autores</NavLink>
                            </li>
                        </ul>
                    </div>
                </div>

                <div className="d-flex align-items-center justify-content-between w-100">
                    <button className="navbar-toggler text" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <NavLink className="navbar-brand ms-3" to="/comienzo">
                        <img src={logotxtwhite} alt="Inspire Library Logo" style={{ width: '150px', height: 'auto' }} />
                    </NavLink>

                    <div className="d-flex ms-auto">
                        {username && token ? (
                            <div className="dropdown">
                                <button className="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img src={profilePic} alt="Profile" className="rounded-circle" style={{ width: '30px', height: '30px' }} />
                                    <span className="ms-2 text-white">{username}</span>
                                </button>
                                <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><NavLink className="dropdown-item" to="/profile">Perfil</NavLink></li>
                                    <li><button className="dropdown-item" onClick={handleLogout}>Cerrar sesión</button></li>
                                </ul>
                            </div>
                        ) : (
                            <div>
                                <button className="btn btn-primary ms-3" onClick={openModal}>Registrarse</button>
                                <button className="btn btn-outline-light ms-3" onClick={openLoginModal}>Iniciar sesión</button>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
};

export { Navbar };
