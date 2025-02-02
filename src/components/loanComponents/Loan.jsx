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

  const onActualizarAdmin = async (item) => {
    setItem(item)
    setAction("R")
  }

  const onActualizarUser = async (item) => {
    setItem(item)
    setAction("R")
  }

  const onVolver = () => {
    setAction("L")
    window.location.reload();  
  }

  const onConsultarHistorial = async (id) => {
    setIsLoadingLoans(id);  
    const book = await servicesBooks.getOneBook(id);
    setBookQuery(book);
    const loans = await servicesLoans.getObjectsLoans(book.id);
    setRows(loans);
    setAction("T");
    setIsLoadingLoans(id); 
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
    <div className="library-container">
      <nav className="navbar"></nav>
      {error && <p style={{ color: 'red', textAlign: 'center' }}>Error: {error}</p>}

      {!isSessionExpired && !error && (
        <>
          {action === "L" && (
            <>
              {isLoading ? (
                <div className="loading-message">Cargando libros...</div>
              ) : (
                books.map((book) => (
                  <div key={book.id}>
                    <div className="custom-card">
                      <div className="image-container">
                        <img src={book.coverPhoto} alt={book.name} className="card-image" />
                      </div>
                      <div className="card-content">
                        <h3 className="card-title">{book.name}</h3>
                        <p className="card-description">Categoría: {categories.length > 0
                          ? categories.find(cat => cat.id === book.categoryId)?.name
                          : "Cargando..."}</p>
                        <p className="card-info">Editorial: {publishers.length > 0
                          ? publishers.find(pub => pub.id === book.publisherId)?.name
                          : "Cargando..."}</p>
                      </div>
                      {!isAdmin && (
                        <div className="card-actions" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                            <button
                            type="button"
                            className="btn btn-primary create-library-btn"
                            onClick={() => onConsultarHistorial(book.id)}
                            >
                            {isLoadingLoans === book.id ? 'Cargando préstamos...' : 'Historial préstamos'}
                            </button>
                        </div>
                      )}
                    </div>
                  </div>
                ))
              )}
            </>
          )}
          {
            action === "T" && (
              <Tabla onVolver={onVolver} rows={rows} isAdmin={isAdmin} onActualizarAdmin={onActualizarAdmin} onActualizarUser={onActualizarUser} item={item} isSubmitting={isSubmitting}></Tabla>
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