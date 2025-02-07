import React, { useState, useEffect } from "react";
import '../../styles/library.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import servicesBooks from "../../services/serviceLibrary";
import Registro from "./Registro";
import Consulta from "./Consulta";
import review from "../../assets/review.png"
import { useNavigate } from "react-router-dom";
import Review from "../reviewsComponents/review";
import Reserva from "./Reserva";


const Library = () => {
  const [books, setBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [item, setItem] = useState({});
  const [action, setAction] = useState("T");
  const [authors, setAuthors] = useState([]);
  const [publishers, setPublishers] = useState([]);
  const [categories, setCategories] = useState([]);
  const [bookQuery, setBookQuery] = useState([]);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [deletingBookId, setDeletingBookId] = useState(null);
  const [loadingBookId, setLoadingBookId] = useState(null);
  const [isLoadingRe, setIsLoadingRe] = useState(true);
  const navigate = useNavigate();


  const onCategories = async () => {
    const eq = await servicesBooks.getNombresCategories();
    setCategories(eq);
  };

  const onAuthors = async () => {
    const eq = await servicesBooks.getNombresAuthors()
    setAuthors(eq)
  }

  const onPublishers = async () => {
    const eq = await servicesBooks.getNombresPublishers()
    setPublishers(eq)
  }

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          setIsSessionExpired(true);
          throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }
        onAuthors();
        onCategories();
        onPublishers();
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
  const onSubmit = async (data) => {
    if (isSubmitting) return;
    setIsSubmitting(true);
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

  const onConsultarBook = async (id) => {
    if (loadingBookId) return;
    setLoadingBookId(id);

    try {
      const book = await servicesBooks.getOneBook(id);
      setBookQuery(book);
      onAuthors();
      onCategories();
      onPublishers();
      setAction("C");
    } catch (error) {
      toast.error("Error al obtener el libro");
      console.error("Error en la consulta del libro:", error);
    } finally {
      setLoadingBookId(null);
    }
  };

  const onConsultarReviews = async (id) => {
    try {
      const book = await servicesBooks.getOneBook(id);
      setBookQuery(book);
      setAction("O");
    } catch (error) {
      toast.error("Error al obtener el libro");
      console.error("Error en la consulta del libro:", error);
    } finally {
      setIsLoadingRe(false);
    }
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
          {action === "T" && (
            <>
              {isLoading ? (
                <div className="loading-message">
                  <p>Cargando libros...</p>
                  <div className="loading-gif" />
                </div>
              ) : (
                books.map((book) => (
                  <div key={book.id}>
                    <div className="book">
                      <div className="gloss"></div>
                      <img className="cover" src={book.coverPhoto} />
                      <div className="description">
                        <h1 className="title"><strong>{book.name}<button onClick={() => onConsultarReviews(book.id)}
                          className="redirect-button"
                          >
                          <img src={review} alt="Ir" className="icon-button" />
                        </button></strong></h1>
                        <hr />
                        <p>Categoría: {categories.length > 0
                          ? categories.find(cat => cat.id === book.categoryId)?.name
                          : "Cargando..."} </p>
                        <p>Editorial: {publishers.length > 0
                          ? publishers.find(pub => pub.id === book.publisherId)?.name
                          : "Cargando..."}</p>
                        <p>Autor: {authors.length > 0
                          ? (() => {
                              const author = authors.find(aut => aut.id === book.authorId);
                              return author ? `${author.name} ${author.lastName}` : "Autor no encontrado";
                            })()
                          : "Cargando..."}</p>
                        <blockquote>
                          </blockquote>
                        {isAdmin && (
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
                              className="btn btn-warning "
                              onClick={() => onActualizar(book)}
                            >
                              Actualizar
                            </button>
                          </div>
                        )}
                        {!isAdmin && (
                          <div className="card-actions" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                            <button
                              onClick={() => onConsultarBook(book.id)}
                              className="btn btn-primary"
                              disabled={loadingBookId === book.id}
                            >
                              {loadingBookId === book.id ? "Cargando..." : "Consultar"}
                            </button>
                          </div>
                        )}
                      </div>
                    </div>
                  </div>
                ))
              )}
              <div className="create-book-button-container">
                {isAdmin && (
                  <div>
                    <button
                      type="button"
                      className="btn btn-success create-author-btn"
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
            action === "R" && (
              <Registro onVolver={onVolver} onSubmit={onSubmit} item={item} authors={authors} publishers={publishers} categories={categories} isSubmitting={isSubmitting}></Registro>
            )
          }
          {
            action === "C" && (
              <Consulta onVolver={onVolver} bookQuery={bookQuery} authors={authors} publishers={publishers} categories={categories}></Consulta>
            )
          }
          {
            action === "O" && (
              <Review onVolver={onVolver} bookQuery={bookQuery}></Review>
            )
          }

        </>
      )}

      <ToastContainer />
    </div>
  );
};

export default Library;