import React, { useState, useEffect } from "react";
import '../../styles/library.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import servicesBooks from "../../services/serviceLibrary";
import Registro from "./Registro";
import Tabla from "./Tabla"
import servicesLoans from "../../services/servicesLoans";


const Loan = () => {
  const [books, setBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isSessionExpired, setIsSessionExpired] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false); 
  const [item, setItem] = useState({});
  const [action, setAction] = useState("L");
  const [authors, setAuthors] = useState([]);
  const [publishers, setPublishers] = useState([]);
  const [categories, setCategories] = useState([]);
  const [bookQuery, setBookQuery] = useState([]);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [rows, setRows] = useState([])
  const [isLoadingLoans, setIsLoadingLoans] = useState(null);
  const [deletingLoanId, setDeletingLoanId] = useState(null);

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
      await servicesLoans.save(data);
      if (data.id != null) {
        toast.success('Préstamo actualizado con éxito');
        window.location.reload();  
      } else {
        toast.success('Préstamo creado con éxito');
        window.location.reload();  
      }
      await loadData();
      setAction("L");
    } catch (error) {
      toast.error('Error al guardar el Préstamo');
    } finally {
      setIsSubmitting(false);
    }
  };

  const onActualizar = async (item) => {
    setItem(item)
    setAction("R")
  }

  const onVolver = () => {
    setAction("L")
    window.location.reload();  
  }

  const onEliminar = async (id) => {
    setDeletingLoanId(id);
    try {
      await servicesLoans.remove(id);

      setBooks(prevBooks => prevBooks.filter(book => book.id !== id));

      setTimeout(() => {
        toast.success("Préstamo eliminado con éxito");
      }, 100);
      window.location.reload();
      loadData();
    } catch (error) {
      setTimeout(() => {
        toast.error("Error al eliminar el préstamo");
      }, 100);
      console.error("Error al eliminar el préstamo:", error);
    } finally {
      setDeletingLoanId(null);
    }
  };

  const onConsultarHistorial = async (id) => {
    try {
        setIsLoadingLoans(id);  
        const book = await servicesBooks.getOneBook(id);
        setBookQuery(book);
        const loans = await servicesLoans.getObjectsLoans(book.id);
        if (isAdmin) {
            setRows(loans);  
        } else {
            const user = await servicesBooks.getIdUser(localStorage.getItem('email'));
            const idUser = user.id;
            const loansUser = loans.filter(l => l.userId === idUser);
            setRows(loansUser);
        }
        setAction("T");
    } catch (error) {
        console.error("Error en onConsultarHistorial:", error);
    } finally {
        setIsLoadingLoans(null); 
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
    <div className="loan-container">
      <nav className="navbar"></nav>
      {error && <p style={{ color: 'red', textAlign: 'center' }}>Error: {error}</p>}

      {!isSessionExpired && !error && (
        <>
          {action === "L" && (
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
                        <h1 className="title"><strong>{book.name}</strong></h1>
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

                        {(!isAdmin || isAdmin) && (
                        <div className="card-actions" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                            <button
                            type="button"
                            className="btn btn-primary "
                            onClick={() => onConsultarHistorial(book.id)}
                            >
                            {isLoadingLoans === book.id ? 'Cargando préstamos...' : 'Historial préstamos'}
                            </button>
                        </div>
                        )}
                      </div>
                    </div>
                  </div>
                  
                ))
              )}
            </>
          )}
          {
            action === "T" && (
              <Tabla onVolver={onVolver} rows={rows} isAdmin={isAdmin} onActualizar={onActualizar} item={item} isSubmitting={isSubmitting} onEliminar={onEliminar}></Tabla>
            )
          }
          {
            action === "R" && (
              <Registro onVolver={onVolver} item={item} onSubmit={onSubmit} isSubmitting={isSubmitting} bookQuery={bookQuery} isAdmin={isAdmin}></Registro>
            )
          }
        </>
      )}

      <ToastContainer />
    </div>
  );
};

export default Loan;