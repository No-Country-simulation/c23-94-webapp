import React, { useState, useEffect } from "react";
import '../../styles/library.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import servicesBooks from "../../services/serviceLibrary"
import Registro from "./Registro"

const Library = () => {
  const [books, setBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [item, setItem] = useState({})
  const [action, setAction] = useState("T")
  const [authors, setAuthors] = useState([])
  const [publishers, setPublishers] = useState([])
  const [categories, setCategories] = useState([])
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [deletingBookId, setDeletingBookId] = useState(null);


  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          setIsSessionExpired(true);
          throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }

        const response = await fetch('http://localhost:8080/api/v1/books', {
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
        setBooks(data);
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

    fetchBooks();
  }, []);

  const loadData = async () => {
    try {
      const token = localStorage.getItem('token');

      if (!token) {
        setIsSessionExpired(true);
        throw new Error('Token no encontrado. Por favor, inicie sesión.');
      }

      const res = await servicesBooks.getAll({
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      setBooks(res);
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


  const onAuthors = async () => {
    const eq = await servicesBooks.getNombresAuthors()
    setAuthors(eq)

  }
  const onPublishers = async () => {
    const eq = await servicesBooks.getNombresPublishers()
    setPublishers(eq)

  }
  const onCategories = async () => {
    const eq = await servicesBooks.getNombresCategories()
    setCategories(eq)

  }
  const onSubmit = async (data) => {
    if (isSubmitting) return;  // Evitar que se envíe si ya se está enviando
    setIsSubmitting(true);     // Marcar como enviando

    try {
      await servicesBooks.save(data);
      if (data.id != null) {
        toast.success('Libro actualizado con éxito');
      } else {
        toast.success('Libro creado con éxito');
      }
      await loadData();
      setAction("T");
    } catch (error) {
      toast.error('Error al guardar el libro');
    } finally {
      setIsSubmitting(false);
    }
  };

  const onRegistro = () => {
    setItem({});
    onAuthors();
    onCategories();
    onPublishers();
    setAction("R")
  }

  const onEliminar = async (id) => {
    setDeletingBookId(id);
    try {
      await servicesBooks.remove(id);

      setBooks(prevBooks => prevBooks.filter(book => book.id !== id));

      setTimeout(() => {
        toast.success("Libro eliminado con éxito");
      }, 100);

      loadData();
    } catch (error) {
      setTimeout(() => {
        toast.error("Error al eliminar el libro");
      }, 100);
      console.error("Error al eliminar el libro:", error);
    } finally {
      setDeletingBookId(null);
    }
  };








  const onActualizar = async (item) => {
    setItem(item)
    onAuthors();
    onCategories();
    onPublishers();
    loadData()
    setAction("R")
  }

  const onVolver = () => {
    setAction("T")
  }

  const onFiltrar = async (filter) => {
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
    <div className="library-container">
      <nav className="navbar"></nav>
      {error && <p style={{ color: 'red', textAlign: 'center' }}>Error: {error}</p>}

      {!isSessionExpired && !error && (
        <>
          {
            action === "T" && (
              <>
                {isLoading ? (
                  <div className="loading-message">Cargando libros...</div>
                ) : (
                  books.map((book) => (
                    <div>                      
                      <div key={book.id} className="custom-card">
                        <div className="image-container">
                          <img src={book.coverPhoto} alt={book.name} className="card-image" />
                        </div>
                        <div className="card-content">
                          <h3 className="card-title">{book.name}</h3>
                          <p className="card-description">Netflix 4K family subscription</p>
                          <p className="card-info">Two month free subscription</p>
                        </div>
                      {isAdmin && (
                        <>
                          <div className="card-actions">
                            <button
                              onClick={() => onEliminar(book.id)}
                              className="btn btn-delete"
                              disabled={deletingBookId === book.id}
                            >
                              {deletingBookId === book.id ? "Eliminando..." : "Eliminar"}
                            </button>

                            <button
                              type="button"
                              className="btn btn-primary create-library-btn"
                              onClick={() => onActualizar(book)}
                            >
                              Actualizar
                            </button>
                          </div>
                        </>
                      )}
                    </div>
                    </div>
                  ))
                )}
                <div className="create-book-button-container">
                  {isAdmin && (
                    <div>
                      <button
                        type="button"
                        className="btn btn-primary create-book-btn"
                        onClick={onRegistro}
                      >
                        Crear Libro
                      </button>
                    </div>
                  )}
                </div>
              </>
            )}

          {
            action !== "T" && (
              <Registro onVolver={onVolver} onSubmit={onSubmit} item={item} authors={authors} publishers={publishers} categories={categories} isSubmitting={isSubmitting}></Registro>
            )
          }
        </>
      )}

      <ToastContainer />
    </div>
  );
};

export default Library;
