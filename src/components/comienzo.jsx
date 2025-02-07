import { Link, NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import React from 'react';
import '../styles/comienzo.css';
import c1 from "../assets/carrousel1.png"
import c2 from "../assets/carrousel2.png"
import c3 from "../assets/carrousel3.png"

const Comienzo = () => {
  return (
    <div className="home-container">
      <header className="header">
        <h1><strong>Bienvenidos a Inspire Library</strong></h1>
      </header>
      <div id="imageCarousel" className="carousel slide" data-bs-ride="carousel" data-bs-pause="false">
        <div className="carousel-inner">
          <div className="carousel-item active">
            <img src={c1} className="d-block w-100" alt="Imagen 1" />
          </div>
          <div className="carousel-item">
            <img src={c2} className="d-block w-100" alt="Imagen 2" />
          </div>
          <div className="carousel-item">
            <img src={c3} className="d-block w-100" alt="Imagen 3" />
          </div>
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#imageCarousel" data-bs-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#imageCarousel" data-bs-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>


      <section className="hero-section">
        <h2>Explora un mundo de libros</h2>
        <p>Descubre y disfruta de los mejores títulos para todos los gustos.</p>
        <p>Hacemos que tu imaginación vuele...</p>
        <NavLink className="btn-explore" to="/library">Explorar Libros</NavLink>
      </section>
    </div>
  );
};

export default Comienzo;