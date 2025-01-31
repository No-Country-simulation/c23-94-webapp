import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { useState, useEffect } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Reserva({onSubmit, volverBook, bookQuery, isSubmittingRe}) {

    const { register, handleSubmit, formState: { errors } } = useForm({})
    const [userId, setUserId] = useState("")

    useEffect(() => {
        // Establece el userId desde localStorage cuando el componente se monta
        setUserId(localStorage.getItem("id"));
      }, [userId]);
      console.log("usuario",userId)
      console.log(localStorage)
    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">Reservar Libro</h6>
                    <div className="card-body">
                        <form className="form" onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label htmlFor="loanDate">Fecha de reservación:</label>
                                <input
                                    type="date"
                                    id="loanDate"
                                    className="form-control"
                                    {...register("loanDate", { required: "Campo obligatorio" })}
                                />
                                {errors.loanDate && <span className="error">{errors.loanDate.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="dueDate">Fecha de Vencimiento:</label>
                                <input
                                    type="text"
                                    id="dueDate"
                                    className="form-control"
                                    {...register("dueDate", { required: "Campo obligatorio" })}
                                />
                                {errors.dueDate && <span className="error">{errors.dueDate.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="userId">Usuario Número: {userId}</label>
                                <input
                                    id="userId"
                                    type="hidden"
                                    value={userId}  // El userId se pasa al backend
                                    {...register("userId", { required: "Campo obligatorio" })}  // Registramos el userId para react-hook-form
                                />
                                {errors.userId && <span className="error">{errors.userId.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="bookId">Libro a reservar: {bookQuery.name}</label>
                                <input
                                    type="hidden"
                                    id="bookId"
                                    value={bookQuery.id}
                                    className="form-control"
                                    {...register("bookId", { required: "Campo obligatorio" })}
                                />
                                {errors.bookId && <span className="error">{errors.bookId.message}</span>}
                            </div>
                            <div>
                                <button className="btn btn-primary mx-1 my-1" type="submit" disabled={isSubmittingRe}>{isSubmittingRe ? "Reservando..." : "Reservar"}</button>
                                <button className="btn btn-secondary mx-1 my-1" onClick={volverBook}>Volver al libro</button>
                                <button className="btn btn-secondary mx-1 my-1" type="reset">Limpiar</button>
                            </div>
                        </form>
                    </div>
                </div>
                <ToastContainer />
            </div>
        </>
    );
}