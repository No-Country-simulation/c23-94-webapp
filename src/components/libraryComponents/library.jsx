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
  
          // Si la respuesta tiene la estructura { data: [...] }, mantén el acceso con res.data
          // Si la respuesta no tiene la propiedad 'data', accede a res directamente
          setBooks(res);  // Esto previene problemas si la respuesta no tiene 'data'
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
  
    
  const onAuthors = async() => {
    const eq = await servicesBooks.getNombresAuthors()
    setAuthors(eq)
    
  }    
  const onPublishers = async() => {
    const eq = await servicesBooks.getNombresPublishers()
    setPublishers(eq)
    
  }    
  const onCategories = async() => {
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
        setIsSubmitting(false);  // Rehabilitar el envío
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
    try {
        await servicesBooks.remove(id);  // Elimina el autor
        toast.success('Libro eliminado con éxito'); 
        loadData();  // Recarga los datos después de la eliminación
    } catch (error) {
        toast.error('Error al eliminar el book'); 
        console.error("Error al eliminar el book:", error);
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
            <div key={book.id} className="book-card">
              <div className="book-info">
                <h3>{book.name}</h3>
                <p>{book.category?.name}</p>
                <p>Fecha de publicación: {book.publishedDate}</p>
                <p>Páginas: {book.numberPages}</p>
                <p>Edición: {book.edition}</p>
                <p>ISBN: {book.isbn}</p>
                <img src={book.coverPhoto} alt={book.name} className="book-image" />
                <p>{book.description}</p>
              </div>
  
              {isAdmin && (
                <>
                <div className="book-actions">
                  <button
                    onClick={() => onEliminar(book.id)}
                    className="btn btn-danger"
                  >
                    Eliminar
                  </button>
                  </div>
                  <div>
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
