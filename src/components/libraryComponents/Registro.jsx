import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Registro({ onSubmit, item, onVolver, authors, publishers, categories, isSubmitting }) {

    const { register, handleSubmit, formState: { errors } } = useForm({ values: item })
    const onClickVolver = (e) => {
        e.preventDefault();
        onVolver();
    };
    console.log(item)
    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">{(item.id) ? "Actualizar libro" : "Nuevo libro"}</h6>
                    <div className="card-body">
                        <form className="form" onSubmit={handleSubmit(onSubmit)}>
                            <div className="row">
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="name">Título:</label>
                                    <input
                                        type="text"
                                        id="name"
                                        className="form-control"
                                        {...register("name", { required: "Campo obligatorio" })}
                                    />
                                    {errors.name && <span className="error">{errors.name.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="publishedDate">Fecha de Publicación:</label>
                                    <input
                                        type="text"
                                        id="publishedDate"
                                        className="form-control"
                                        {...register("publishedDate", { required: "Campo obligatorio" })}
                                    />
                                    {errors.publishedDate && <span className="error">{errors.publishedDate.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="numberPages">Cantidad de páginas:</label>
                                    <input
                                        type="number"
                                        id="numberPages"
                                        className="form-control"
                                        {...register("numberPages", { required: "Campo obligatorio" })}
                                    />
                                    {errors.numberPages && <span className="error">{errors.numberPages.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="edition">Edición:</label>
                                    <input
                                        type="number"
                                        id="edition"
                                        className="form-control"
                                        {...register("edition", { required: "Campo obligatorio" })}
                                    />
                                    {errors.edition && <span className="error">{errors.edition.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="isbn">ISBN:</label>
                                    <input
                                        type="number"
                                        id="isbn"
                                        className="form-control"
                                        {...register("isbn", { required: "Campo obligatorio" })}
                                    />
                                    {errors.isbn && <span className="error">{errors.isbn.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="coverPhoto">Link de portada:</label>
                                    <input
                                        type="text"
                                        id="coverPhoto"
                                        className="form-control"
                                        {...register("coverPhoto", {
                                            required: "Campo obligatorio",
                                            pattern: {
                                                value: /^(https?:\/\/)?([\w\d-]+\.)+[\w\d]{2,}(\/.*)?$/,
                                                message: "Ingrese un enlace válido"
                                            }
                                        })}
                                    />
                                    {errors.coverPhoto && <span className="error">{errors.coverPhoto.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="copies">Cantidad de copias:</label>
                                    <input
                                        type="number"
                                        id="copies"
                                        className="form-control"
                                        {...register("copies", { required: "Campo obligatorio" })}
                                    />
                                    {errors.copies && <span className="error">{errors.copies.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="authorId">Autor:</label>
                                    <select className="form-control" id="authorId" {...register("authorId", { required: "Campo obligatorio" })}>
                                        {authors.map((a) => (
                                            <option value={a.id} key={a.id}>{a.name} {a.lastName}</option>
                                        ))}
                                    </select>
                                    {errors.authorId && <span className="error">{errors.authorId.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="publisherId">Editorial:</label>
                                    <select className="form-control" id="publisherId" {...register("publisherId", { required: "Campo obligatorio" })}>
                                        {publishers.map((p) => (
                                            <option value={p.id} key={p.id}>{p.name}</option>
                                        ))}
                                    </select>
                                    {errors.publisherId && <span className="error">{errors.publisherId.message}</span>}
                                </div>
                                <div className="col-md-6 mb-3">
                                    <label htmlFor="categoryId">Categoría:</label>
                                    <select className="form-control" id="categoryId" {...register("categoryId", { required: "Campo obligatorio" })}>
                                        {categories.map((c) => (
                                            <option value={c.id} key={c.id}>{c.name}</option>
                                        ))}
                                    </select>
                                    {errors.categoryId && <span className="error">{errors.categoryId.message}</span>}
                                </div>
                            </div>
                            <div className="d-flex justify-content-center">
                                <button className="btn btn-primary mx-1 my-1" type="submit" disabled={isSubmitting}>
                                    {isSubmitting ? "Enviando..." : "Enviar"}
                                </button>
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
