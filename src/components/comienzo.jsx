import { Link, NavLink } from 'react-router-dom';
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
      <div id="imageCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src={c1} class="d-block w-100" alt="Imagen 1" />
          </div>
          <div class="carousel-item">
            <img src={c2} class="d-block w-100" alt="Imagen 2" />
          </div>
          <div class="carousel-item">
            <img src={c3} class="d-block w-100" alt="Imagen 3" />
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#imageCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#imageCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
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