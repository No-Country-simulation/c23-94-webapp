import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { useState, useEffect } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import serviceLibrary from "../../services/serviceLibrary";

export default function Reserva({onSubmitReserva, volverBook, bookQuery, isSubmittingRe}) {

    const { register, setValue, handleSubmit, formState: { errors } } = useForm()
    const [userId, setUserId] = useState("")

    useEffect(() => {
        const buscarIdUser = async (email) => {
          const user = await serviceLibrary.getIdUser(email);
          setUserId(user.id || "");  
          setValue("userId", user.id || "");  
        };
    
        const email = localStorage.getItem("email");
        if (email) {
          buscarIdUser(email); 
        }
      }, [setValue]);
      
    
    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">Reservar Libro</h6>
                    <div className="card-body">
                        <form className="form" onSubmit={handleSubmit(onSubmitReserva)}>
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
                                    type="date"
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