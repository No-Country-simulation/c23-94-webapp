import { useState } from "react";

const Registro = ({ book, onSubmit }) => {
    const [rating, setRating] = useState(0);
    const [hoveredRating, setHoveredRating] = useState(null);
    const [comment, setComment] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        const newReview = {
            bookId: book.id,
            rating: rating,
            comment: comment,
        };


        onSubmit(newReview);


        setRating(0);
        setComment("");
    };

    return (
        <div className="review-style">
            <h2>Escribe una reseña para el libro: {book.name}</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="rating">Calificación</label>
                    <div className="star-rating">
                        {[...Array(5)].map((_, index) => {
                            const starIndex = index + 1;
                            return (
                                <span
                                    key={starIndex}
                                    className={`star ${starIndex <= (hoveredRating || rating) ? "filled" : ""}`}
                                    onClick={() => setRating(starIndex)}
                                    onMouseEnter={() => setHoveredRating(starIndex)}
                                    onMouseLeave={() => setHoveredRating(null)}
                                >
                                    ★
                                </span>
                            );
                        })}
                    </div>
                </div>

                <div className="form-group mt-3">
                    <label htmlFor="comment">Comentario</label>
                    <textarea
                        id="comment"
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        rows={5}
                        className="form-control"
                        placeholder="Escribe tu reseña aquí..."
                    ></textarea>
                </div>
                <div className="crea-res">
                    <button type="submit" className="btn btn-primary mt-3" disabled={isSubmitting}>
                        {isSubmitting ? "Enviando..." : "Enviar Reseña"}
                    </button>
                </div>
            </form>
        </div>
    );
};

export default Registro;
