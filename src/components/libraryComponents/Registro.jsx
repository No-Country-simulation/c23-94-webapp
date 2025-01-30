import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Registro({ onSubmit, item, onVolver, authors, publishers, categories, isSubmitting}) {

    const {register, handleSubmit, formState:{errors}} = useForm({values:item})
    const onClickVolver = (e) => {
        e.preventDefault();  // Prevenir la acción por defecto del botón "Volver" (que podría ser recargar la página)
        onVolver();
    };

    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">{(item.id) ? "Actualizar libro" : "Nuevo libro"}</h6>
                    <div className="card-body">
                        <form className="form" onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label htmlFor="name">Name:</label>
                                <input
                                    type="text"
                                    id="name"
                                    className="form-control"
                                    {...register("name", { required: "Campo obligatorio" })}
                                />
                                {errors.name && <span className="error">{errors.name.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="publishedDate">Fecha de Publicación:</label>
                                <input
                                    type="text"
                                    id="publishedDate"
                                    className="form-control"
                                    {...register("publishedDate", { required: "Campo obligatorio" })}
                                />
                                {errors.publishedDate && <span className="error">{errors.publishedDate.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="numberPages">numberPages:</label>
                                <input
                                    type="text"
                                    id="numberPages"
                                    className="form-control"
                                    {...register("numberPages", { required: "Campo obligatorio" })}
                                />
                                {errors.numberPages && <span className="error">{errors.numberPages.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="edition">edition:</label>
                                <input
                                    type="number"
                                    id="edition"
                                    className="form-control"
                                    {...register("edition", { required: "Campo obligatorio" })}
                                />
                                {errors.edition && <span className="error">{errors.edition.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="isbn">ISBN:</label>
                                <input
                                    type="number"
                                    id="isbn"
                                    className="form-control"
                                    {...register("isbn", { required: "Campo obligatorio" })}
                                />
                                {errors.isbn && <span className="error">{errors.isbn.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="coverPhoto">coverPhoto:</label>
                                <input
                                    type="text"
                                    id="coverPhoto"
                                    className="form-control"
                                    {...register("coverPhoto", { required: "Campo obligatorio" })}
                                />
                                {errors.coverPhoto && <span className="error">{errors.coverPhoto.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="copies">copies:</label>
                                <input
                                    type="number"
                                    id="copies"
                                    className="form-control"
                                    {...register("copies", { required: "Campo obligatorio" })}
                                />
                                {errors.copies && <span className="error">{errors.copies.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="authorId">Autor:</label>
                                <select className="form-control" id="authorId" {...register("authorId", {required:"Campo obligatorio"})}>
                                    {authors.map((a) => {
                                        return (
                                            <option value={a.id} key={a.id}>{a.name} {a.lastName}</option>
                                        )
                                    })}
                                </select>
                                {errors.authorId && <span className="error">{errors.authorId.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="publisherId">Editorial:</label>
                                <select className="form-control" id="publisherId" {...register("publisherId", {required:"Campo obligatorio"})}>
                                    {publishers.map((p) => {
                                        return (
                                            <option value={p.id} key={p.id}>{p.name}</option>
                                        )
                                    })}
                                </select>
                                {errors.publisherId && <span className="error">{errors.publisherId.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="categoryId">Categorías:</label>
                                <select className="form-control" id="categoryId" {...register("categoryId", {required:"Campo obligatorio"})}>
                                    {categories.map((c) => {
                                        return (
                                            <option value={c.id} key={c.id}>{c.name}</option>
                                        )
                                    })}
                                </select>
                                {errors.categoryId && <span className="error">{errors.categoryId.message}</span>}
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
