import { useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import "../../styles/review.css";
import Registro from "./Registro";
import a1 from "../../assets/a1.png"
import a2 from "../../assets/a2.png"
import a3 from "../../assets/a3.png"
import a4 from "../../assets/a4.png"
import a5 from "../../assets/a5.png"
import serviceLibrary from "../../services/serviceLibrary";

const Review = ({ onVolver, bookQuery }) => {
    const [action, setAction] = useState("T");
    const [loading, setLoading] = useState(true);
    const [reviews, setReviews] = useState([]);
    const [book, setBook] = useState(bookQuery)

    const authorTitles = [
        "Lector Aficionado",
        "Crítico Literario",
        "Amante de los Libros",
        "Explorador de Historias",
        "Entusiasta de la Lectura",
        "Curioso Literario"
    ];

    const avatarImages = [
        a1, a2, a3, a4, a5
    ];


    const getRandomAuthorTitle = () => {
        const randomIndex = Math.floor(Math.random() * authorTitles.length);
        return authorTitles[randomIndex];
    };


    const getRandomAvatar = () => {
        const randomIndex = Math.floor(Math.random() * avatarImages.length);
        return avatarImages[randomIndex];
    };


    const getAll = async (book) => {
        if (!book) return [];

        const token = localStorage.getItem('token');
        if (!token) {
            console.error('Token no encontrado. Por favor, inicie sesión.');
            return [];
        }

        const reviewIds = book.reviewsId;

        if (reviewIds && reviewIds.length > 0) {
            try {
                const reviewData = await Promise.all(
                    reviewIds.map(async (reviewId) => {
                        const response = await fetch(`http://localhost:8080/api/v1/reviews/${reviewId}`, {
                            method: 'GET',
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: `Bearer ${token}`,
                            },
                        });

                        if (!response.ok) {
                            throw new Error(`Error al obtener review ${reviewId}: ${response.statusText}`);
                        }

                        return response.json();
                    })
                );
                return reviewData;
            } catch (error) {
                console.error("Error fetching reviews:", error);
                return [];
            }
        }

        return [];
    };

    const loadData = async () => {
        try {
            const libroActualizado = await serviceLibrary.getOneBook(bookQuery.id);
            const reviewData = await getAll(libroActualizado)
            setReviews(reviewData);
        } catch (error) {
            console.error("Error fetching reviews:", error);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        const load = async () => {
            setLoading(true);
            try {
                const reviewData = await getAll(book);
                setReviews(reviewData);
            } catch (error) {
                console.error("Error fetching reviews:", error);
            } finally {
                setLoading(false);
            }
        };

        load();
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
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(reviewData),
        })
            .then((response) => response.json())
            .then((data) => {
                setReviews((prevReviews) => [...prevReviews, data]);
                setAction("T");
                loadData();
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
                        <div className="loading-message">
                        <p>Cargando reseñas...</p>
                        <div className="loading-gif-rating" />
                      </div>
                    ) : (
                        <div className="container py-5">
                            <h1 className="page-title" style={{ textAlign: "center", marginBottom: "20px" }}>
                                Reseñas del Libro {book.name}
                            </h1>
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

                            <div className="d-flex justify-content-center mt-4">
                                <button
                                    onClick={() => setAction("R")}
                                    className="btn btn-primary me-5"
                                >
                                    Crear reseña
                                </button>
                                <button className="btn btn-primary" onClick={onVolver}>Libros</button>
                            </div>
                        </div>
                    )}
                </>
            )}

            {action === "R" && <Registro book={book} onSubmit={handleNewReview} />}
        </div>
    );
};

export default Review;