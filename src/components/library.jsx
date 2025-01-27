import React, { useState, useEffect } from "react";

const Library = () => {
  const [books, setBooks] = useState([]);
  const [visibleBooks, setVisibleBooks] = useState({});
  const [isAdmin, setIsAdmin] = useState(false);  // Estado para verificar si el usuario es admin

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await fetch("http://localhost:8080/api/v1/books", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        const data = await response.json();
        setBooks(data);

        const initialVisible = {};
        data.forEach((book) => {
          if (!initialVisible[book.category]) {
            initialVisible[book.category] = 3;
          }
        });
        setVisibleBooks(initialVisible);

        // Verificar si el usuario es admin
        const role = localStorage.getItem('role');  // Asumimos que el rol se guardó en localStorage
        if (role === 'ADMIN') {
          setIsAdmin(true);
        }
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, []);

  const loadMoreBooks = (category) => {
    setVisibleBooks((prev) => ({
      ...prev,
      [category]: prev[category] + 3,
    }));
  };

  const handleDeleteBook = (bookId) => {
    // Lógica para eliminar un libro
    console.log("Eliminar libro con ID:", bookId);
    // Aquí deberías hacer una solicitud para eliminar el libro
  };

  const handleEditBook = (bookId) => {
    // Lógica para editar un libro
    console.log("Editar libro con ID:", bookId);
    // Aquí deberías redirigir a una página de edición de libro o abrir un modal
  };

  const handleCreateBook = () => {
    // Lógica para crear un nuevo libro
    console.log("Crear nuevo libro");
    // Aquí deberías redirigir a la página de creación de libro o abrir un modal
  };

  const categories = [...new Set(books.map((book) => book.category))];

  return (
    <div className="library-container">
      {isAdmin && (
        <div className="admin-actions">
          <button onClick={handleCreateBook} className="btn btn-primary">
            Crear libro
          </button>
        </div>
      )}
      
      {categories.map((category) => (
        <div key={category} className="category-section">
          <h2 className="category-title">{category}</h2>
          <div className="books-column">
            {books
              .filter((book) => book.category === category)
              .slice(0, visibleBooks[category])
              .map((book) => (
                <div key={book.id} className="book-card">
                  <img src={book.coverImage} alt={book.title} className="book-image" />
                  <p className="book-title">{book.title}</p>
                  
                  {isAdmin && (
                    <div className="book-actions">
                      <button
                        onClick={() => handleEditBook(book.id)}
                        className="btn btn-warning btn-sm"
                      >
                        Editar
                      </button>
                      <button
                        onClick={() => handleDeleteBook(book.id)}
                        className="btn btn-danger btn-sm"
                      >
                        Eliminar
                      </button>
                    </div>
                  )}
                </div>
              ))}
          </div>
          {books.filter((book) => book.category === category).length >
            visibleBooks[category] && (
            <button
              className="load-more-button"
              onClick={() => loadMoreBooks(category)}
            >
              Mostrar más
            </button>
          )}
        </div>
      ))}
    </div>
  );
};

export default Library;
