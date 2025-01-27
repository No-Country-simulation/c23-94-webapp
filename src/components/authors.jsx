import React, { useEffect, useState } from 'react';
import '../styles/author.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Author = () => {
  const [authors, setAuthors] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [newAuthor, setNewAuthor] = useState({ name: '', last_name: '', country: '', biography: '' });

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
        setAuthors(data);
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

    fetchAuthors();
  }, []);

  const handleDeleteAuthor = async (authorId) => {
    try {
      const token = localStorage.getItem('token');
      const response = await fetch(`http://localhost:8080/api/v1/authors/${authorId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        throw new Error('Error al eliminar el autor');
      }

      setAuthors(authors.filter((author) => author.id !== authorId));
      toast.success('Autor eliminado con éxito'); 
    } catch (error) {
      console.error('Error deleting author:', error);
      toast.error('Error al eliminar el autor'); 
    }
  };

  const handleCreateAuthor = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await fetch('http://localhost:8080/api/v1/authors', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(newAuthor),
      });

      if (!response.ok) {
        throw new Error('Error al crear el autor');
      }

      const createdAuthor = await response.json();
      setAuthors([...authors, createdAuthor]);
      setNewAuthor({ name: '', last_name: '', country: '', biography: '' });
      toast.success('Autor creado con éxito');
    } catch (error) {
      console.error('Error creating author:', error);
      toast.error('Error al crear el autor'); // Muestra un toast de error
    }
  };

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
          {isLoading ? (
            <div className="loading-message">Cargando autores...</div>
          ) : (
            authors.map((author) => (
              <div className="author-card" key={author.id}>
                <div className="author-info">
                  <h3>{author.name} {author.lastName}</h3>
                  <p>{author.country}</p>
                  <p>{author.biography}</p>
                </div>

                {isAdmin && (
                  <div className="author-actions">
                    <button onClick={() => handleDeleteAuthor(author.id)} className="btn btn-danger">
                      Eliminar
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
                  data-bs-toggle="modal"
                  data-bs-target="#createAuthorModal"
                >
                  Crear Autor
                </button>
              </div>
            )}
          </div>

          <div
            className="modal fade"
            id="createAuthorModal"
            tabIndex="-1"
            aria-labelledby="createAuthorModalLabel"
            aria-hidden="true"
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="createAuthorModalLabel">Crear Autor</h5>
                  <button
                    type="button"
                    className="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Cerrar"
                  ></button>
                </div>
                <div className="modal-body">
                  <input
                    type="text"
                    className="form-control mb-3"
                    placeholder="Nombre"
                    value={newAuthor.name}
                    onChange={(e) => setNewAuthor({ ...newAuthor, name: e.target.value })}
                  />
                  <input
                    type="text"
                    className="form-control mb-3"
                    placeholder="Apellido"
                    value={newAuthor.last_name}
                    onChange={(e) => setNewAuthor({ ...newAuthor, last_name: e.target.value })}
                  />
                  <input
                    type="text"
                    className="form-control mb-3"
                    placeholder="País"
                    value={newAuthor.country}
                    onChange={(e) => setNewAuthor({ ...newAuthor, country: e.target.value })}
                  />
                  <textarea
                    className="form-control mb-3"
                    placeholder="Biografía"
                    value={newAuthor.biography}
                    onChange={(e) => setNewAuthor({ ...newAuthor, biography: e.target.value })}
                  />
                </div>
                <div className="modal-footer">
                  <button
                    type="button"
                    className="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    Cancelar
                  </button>
                  <button
                    type="button"
                    className="btn btn-success"
                    onClick={handleCreateAuthor}
                    data-bs-dismiss="modal"
                  >
                    Crear
                  </button>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
      <ToastContainer /> 
    </div>
  );
};

export default Author;