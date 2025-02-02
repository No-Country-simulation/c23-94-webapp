import React from "react";
import "../../styles/author.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useState, useEffect } from "react";
import Reserva from "./Reserva"
import servicesBooks from "../../services/serviceLibrary";

export default function Consulta({bookQuery, onVolver, authors, publishers, categories }) {
    const [actionC, setActionC] = useState("C");
    const [isSubmittingRe, setIsSubmittingRe] = useState(false);
    
    
    const author = authors.length > 0
        ? authors.find(aut => aut.id === bookQuery.authorId)
        : null;

    const onClickVolver = (e) => {
        e.preventDefault();
        onVolver();
    };
    
    const onClickReservar = () => {
        setActionC("L")
    }

    const volverBook = () => {
        setActionC("C")
    }

    const onSubmitReserva = async (data) => {
        if (isSubmittingRe) return;
        setIsSubmittingRe(true);
        try {    
            if (!data.loanDate) {
                throw new Error("La fecha de reserva es obligatoria");
            }
            await servicesBooks.saveReserva(data);
            toast.success("Reserva creada con éxito...");
            setTimeout(() => {
                setActionC("C");
            }, 2000); 
            window.location.reload();  
        } catch (error) {
            console.error("Error al reservar:", error);
            toast.error(error.message || 'Error al reservar el libro');
        } finally {
            setIsSubmittingRe(false);
        }
    };
    
    
    
    

    return (
        <>
            {actionC === "C" && (
                <div className="card shadow-lg" style={{ width: "18rem", borderRadius: "12px" }}>
                    <div style={{ padding: "10px", display: "flex", justifyContent: "center" }}>
                        <img
                            src={bookQuery.coverPhoto}
                            className="card-img-top"
                            alt={bookQuery.name}
                            style={{ borderRadius: "12px", height: "150px", width: "auto", maxWidth: "100%" }}
                        />
                    </div>
                    <div className="card-body">
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
                            <h5 className="card-title text-primary" style={{ marginBottom: '10px', fontSize: "1.5rem" }}>{bookQuery.name}</h5>
                            <h5 className="card-title text-primary" style={{ marginBottom: '10px' }}>
                                Autor: {author ? `${author.lastName}, ${author.name}` : "Cargando..."}</h5>         
                        </div>
                        <h6 className="card-subtitle mb-2 text-dark">Este es un libro de {categories.length > 0
                            ? categories.find(cat => cat.id === bookQuery.categoryId)?.name
                            : "Cargando..."} de la editorial {publishers.length > 0
                            ? publishers.find(pub => pub.id === bookQuery.publisherId)?.name
                            : "Cargando..."} fue publicado en el año {bookQuery.publishedDate}</h6>
                        <h6 className="card-text" style={{ fontSize: "14px", color: "#555" }}>Número de edición: {bookQuery.edition}</h6>
                        <h6 className="card-text" style={{ fontSize: "14px", color: "#555" }}>ISBN: {bookQuery.isbn}</h6>
                        <h6 className="card-text" style={{ fontSize: "14px", color: "#555" }}>Páginas: {bookQuery.numberPages}</h6>
                        <h6 className="card-text" style={{ fontSize: "14px", color: "#555" }}>Copias: {bookQuery.copies}</h6>
                        <div className="d-flex justify-content-between">
                            <button className="btn btn-primary" onClick={onClickReservar}>Reservar</button>
                            <button className="btn btn-secondary" onClick={onClickVolver}>Volver</button>
                        </div>
                    </div>
                </div>
            )}
            {actionC !== "C" && (
                <Reserva onSubmitReserva={onSubmitReserva} volverBook={volverBook} bookQuery={bookQuery} isSubmittingRe={isSubmittingRe}></Reserva>
            )}
            <ToastContainer />
        </>
    
    );


}