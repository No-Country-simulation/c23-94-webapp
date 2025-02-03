import { useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import "../../styles/review.css";
import Registro from "./Registro";
import a1 from "../../assets/a1.png"
import a2 from "../../assets/a2.png"
import a3 from "../../assets/a3.png"
import a4 from "../../assets/a4.png"
import a5 from "../../assets/a5.png"

const Review = () => {
    const location = useLocation();
    const book = location.state?.book;
    const [action, setAction] = useState("T");
    const [loading, setLoading] = useState(true);
    const [reviews, setReviews] = useState([]);

    // Lista de posibles títulos para el autor de la reseña
    const authorTitles = [
        "Lector Aficionado",
        "Crítico Literario",
        "Amante de los Libros",
        "Explorador de Historias",
        "Entusiasta de la Lectura",
        "Curioso Literario"
    ];

    // Lista de imágenes de avatares
    const avatarImages = [
        a1,a2, a3, a4,a5
    ];

    // Función para obtener un título aleatorio
    const getRandomAuthorTitle = () => {
        const randomIndex = Math.floor(Math.random() * authorTitles.length);
        return authorTitles[randomIndex];
    };

    // Función para obtener una imagen de avatar aleatoria
    const getRandomAvatar = () => {
        const randomIndex = Math.floor(Math.random() * avatarImages.length);
        return avatarImages[randomIndex];
    };

    useEffect(() => {
        if (book) {
            const token = localStorage.getItem('token');
            if (!token) {
                console.error('Token no encontrado. Por favor, inicie sesión.');
                return; // No continuar si el token no está presente
            }
    
            const reviewIds = book.reviewsId; // Array de ids de reseñas asociadas al libro
    
            if (reviewIds && reviewIds.length > 0) {
                Promise.all(
                    reviewIds.map((reviewId) =>
                        fetch(`http://localhost:8080/api/v1/reviews/${reviewId}`, {
                            method: 'GET',
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: `Bearer ${token}`, 
                            },
                        }).then((response) => response.json())
                    )
                )
                    .then((reviewData) => {
                        setReviews(reviewData || []);
                        setLoading(false);
                    })
                    .catch((error) => {
                        console.error("Error fetching reviews:", error);
                        setLoading(false);
                    });
            } else {
                setLoading(false);
                setReviews([]);
            }
        }
    }, [book]);
    

    const handleNewReview = (reviewData) => {
        const token = localStorage.getItem('token');
        if (!token) {
            console.error('Token no encontrado. Por favor, inicie sesión.');
            return;
        }

        fetch(`http://localhost:8080/api/v1/reviews`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`, // Agregar el token aquí también
            },
            body: JSON.stringify(reviewData),
        })
            .then((response) => response.json())
            .then((data) => {
                setReviews((prevReviews) => [...prevReviews, data]);
                setAction("T");
            })
            .catch((error) => {
                console.error("Error submitting review:", error);
            });
    };

    if (!book) {
        return <p>Error: No se encontró el libro.</p>;
    }

    return (
        <div className="library-container">
            <nav className="navbar"></nav>
            {action === "T" && (
                <>
                    {loading ? (
                        <div className="loading-message">Cargando reseñas...</div>
                    ) : (
                        <div className="container py-5">
                            <div className="review-cards-container">
                                {reviews.length > 0 ? (
                                    reviews.map((review, index) => (
                                        <div className="review-card" key={index}>
                                            <div className="review-header">
                                                <img
                                                    src={getRandomAvatar()}
                                                    alt="User Avatar"
                                                    className="avatar"
                                                />
                                                <div className="user-info">
                                                    <h4 className="user-name">{getRandomAuthorTitle()}</h4>
                                                    <p className="review-date">{review.created_at}</p>
                                                </div>
                                            </div>
                                            <div className="review-rating">
                                                <span className="rating">
                                                    {"★".repeat(review.rating)}
                                                    {"☆".repeat(5 - review.rating)}
                                                </span>
                                            </div>
                                            <p className="review-text">{review.comment}</p>
                                        </div>
                                    ))
                                ) : (
                                    <p>No hay reseñas para este libro.</p>
                                )}
                            </div>

                        </div>
                    )}
                    
                    <button
                                onClick={() => setAction("R")}
                                className="btn btn-primary"
                            >
                                Crear reseña
                            </button>
                </>
            )}
            {action === "R" && <Registro book={book} onSubmit={handleNewReview} />}
        </div>
    );
};

export default Review;
