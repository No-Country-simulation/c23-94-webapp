import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useState, useEffect } from "react";
import serviceLibrary from "../../services/serviceLibrary";

export default function Registro({ onVolver, item, onSubmit, isSubmitting, bookQuery, isAdmin}) {

    const { register, handleSubmit, formState: { errors } } = useForm({ values: item })
    const onClickVolver = (e) => {
        e.preventDefault();
        onVolver();
    };

    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">Actualizar préstamo</h6>
                    <div className="card-body">
                        <form className="form" onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label htmlFor="loanDate">Fecha de Inicio:</label>
                                <input
                                    type="date"
                                    id="loanDate"
                                    className="form-control"
                                    {...register("loanDate", { required: "Campo obligatorio" })}
                                    disabled={!isAdmin}
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
                                <button className="btn btn-primary mx-1 my-1" type="submit" disabled={isSubmitting}>{isSubmitting ? "Enviando..." : "Enviar"}</button>
                                <button className="btn btn-secondary mx-1 my-1" onClick={onClickVolver}>Volver</button>
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