import React, { useEffect, useState } from 'react';
import '../../styles/author.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import servicesAuthors from '../../services/servicesAuthors';
import Registro from "./Registro"

export default function Author() {
  const [authors, setAuthors] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [item, setItem] = useState({})
  const [libros, setLibros] = useState([])
  const [action, setAction] = useState("T") 


 useEffect(() => {
    const fetchAuthors = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          setIsSessionExpired(true);
          throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }

        const response = await fetch('http://localhost:8080/api/v1/authors', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        });
        if (!response.ok) {
          throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        console.log(data)
        setAuthors(data);
        setIsLoading(false);

        const role = localStorage.getItem('role');
        if (role === 'ADMIN') {
          setIsAdmin(true);
        }
      } catch (error) {
        console.error('Error fetching books:', error);
        setIsLoading(false);
        setIsSessionExpired(true);
      }
    };

    fetchAuthors();
  }, []);

  
  const loadData = async () => {
    try {
        const token = localStorage.getItem('token');

        if (!token) {
            setIsSessionExpired(true);
            throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }

        const res = await servicesAuthors.getAll({
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
        });     
        setAuthors(res);
        setIsLoading(false);

        const role = localStorage.getItem('role');
        if (role === 'ADMIN') {
            setIsAdmin(true);
        }

    } catch (error) {
        console.error('Error fetching authors:', error);
        setError(error.message);
        setIsLoading(false);
    }
};

  

  const onSubmit = async(data) => {
    await servicesAuthors.save(data)
    if (data.id != null) {
      toast.success('Autor actualizado con éxito'); 
    } else {
      toast.success('Autor creado con éxito'); 
    }
    await loadData()
    setAction("T")
}

const onRegistro = () => {
    setItem({});
    setAction("R")
}

const onEliminar = async (id) => {
  try {
      await servicesAuthors.remove(id);
      toast.success('Autor eliminado con éxito'); 
      loadData();
  } catch (error) {
      toast.error('Error al eliminar el autor'); 
      console.error("Error al eliminar el autor:", error);
  }
};

const onActualizar = async(item) => {
    setItem(item)
    loadData()
    setAction("R")
}

const onVolver = () => {
    setAction("T")
}

const onFiltrar = async(filter) => {
    await loadData(filter)
}
 
  if (isSessionExpired) {
    return (
      <div className="session-expired">
        <div className="message-box">
          <h2>Acceso restringido</h2>
          <p>Por favor, inicie sesión para acceder a esta página.</p>
        </div>
      </div>
    );
  }

  if (error) {
    return <p style={{ color: 'red', textAlign: 'center' }}>Error: {error}</p>;
  }

  return (
    <div className="authors-container">
      <nav className="navbar"></nav>

      {error && <p style={{ color: 'red', textAlign: 'center' }}>Error: {error}</p>}

      {!isSessionExpired && !error && (
        <>
        {
          action === "T" && (
            <>
            {isLoading ? (
              <div className="loading-message">Cargando autores...</div>
            ) : (
              authors.map((author) => (<div className="author-card" key={author.id}>
                <div className="author-info">
                  <h3>{author.name} {author.lastName}</h3>
                  <p>{author.country}</p>
                  <p className="bio">{author.biography}</p>
                </div>
                {isAdmin && (
                  <div className="card-actions">
                    <button onClick={() => onEliminar(author.id)} className="btn btn-delete">
                      Eliminar
                    </button>
                    <button
                      type="button"
                      className="btn btn-primary create-author-btn"
                      onClick={() => onActualizar(author)}
                    >
                      Actualizar Autor
                    </button>
                  </div>
                )}
              </div>
              
              ))
            )}
            <div className="create-author-button-container">
              {isAdmin && (
                <div>
                  <button
                    type="button"
                    className="btn btn-primary create-author-btn"
                    onClick={onRegistro}
                  >
                    Crear Autor
                  </button>
                </div>
              )}
            </div>
          </>
          )
        }
        {
          action !== "T" && (
            <Registro onVolver={onVolver} onSubmit={onSubmit} item={item}></Registro> 
          )
        }  
        </>
      )}
      <ToastContainer /> 
    </div>
  );
};

