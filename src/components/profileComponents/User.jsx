import React, { useEffect, useState } from "react";
import { NavLink, useParams } from "react-router-dom";
import servicesUser from "../../services/servicesUser";
import "../../styles/user.css";  // Asegúrate de que este archivo exista
import imagen from "../../assets/profile-picture-default.png"

export default function User() {

  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [user, setUser] = useState(null);
  const { email } = useParams();  
  console.log(email);
  
  const loadData = async () => {
    if (!email) {
      setIsSessionExpired(true);
      setIsLoading(false);
      return; 
    }
    
    try {
      const userData = await servicesUser.getOneUser(email);
      console.log(userData)
      if (userData) {
        setUser(userData);
        setIsLoading(false);
      } else {
        setIsSessionExpired(true);
        setIsLoading(false);
      }
    } catch (error) {
      console.error("Error al cargar los datos del usuario:", error.message);
      setIsSessionExpired(true);
      setIsLoading(false);
    }
  };

  useEffect(() => {
    loadData();  
  }, [email]);  
  
  if (isLoading) {
    return <div>Cargando...</div>;
  }

  if (isSessionExpired) {
    return <div>Sesión expirada o error al cargar el usuario.</div>;
  }

  return (
    <>
      <div id="gradient"></div>
      <div id="card">
      <img
          src={imagen}
          alt="User"
          style={{
            borderRadius: "50%", 
            width: "75px",      
            height: "75px",    
            objectFit: "cover", 
            marginTop: "10px" 
          }}
        />
        <h1 style={{marginTop: '10px'}}>{user ? user.username: "Cargando..."}</h1>
        <h2 style={{marginBottom: '15px', marginTop: "-10px"}}>{user ? user.email : 'Cargando...'}</h2>
        <p style={{marginTop: '30px'}}>Télefono: {user ? user.phone : 'No disponible'}</p>
        <p>Domicilio: {user ? user.address : 'Dirección no disponible'}</p>
        <span className="left bottom">Está iniciando sesión como: {user ? localStorage.getItem("role") : 'No disponible'}</span>
      </div>
      <div className="d-flex justify-content-center" style={{ marginTop: '50px' , marginLeft : "-65px"}}>
      <NavLink
        className="nav-link active btn"
        to="/comienzo"
        style={{
          textAlign: 'center',
          backgroundImage: 'linear-gradient(#6f4f29 20%, #5a4024 20% 40%, #4a3021 40% 60%, #3b2a1c 60% 80%, #2f2116 80% 100%)',
          color: 'white',  
          padding: '15px 30px',  
          fontSize: '18px',  
          borderRadius: '5px', 
          textDecoration: 'none',  
        }}
      >
        Volver al Inicio
      </NavLink>

      </div>
    </>
  );
  
}
