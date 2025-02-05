import React from "react";
import { useForm } from "react-hook-form";
import "../../styles/author.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Registro({ onSubmit, item, onVolver}) {

    const {register, handleSubmit, formState:{errors}} = useForm({values:item})
    const onClickVolver = (e) => {
        e.preventDefault();
        onVolver();
    };

    return (
        <>
            <div className="container_app">
                <div className="card">
                    <h6 className="card-header">{(item.id) ? "Actualizar autor" : "Nuevo autor"}</h6>
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
                                <label htmlFor="lastName">Last Name:</label>
                                <input
                                    type="text"
                                    id="lastName"
                                    className="form-control"
                                    {...register("lastName", { required: "Campo obligatorio" })}
                                />
                                {errors.lastName && <span className="error">{errors.lastName.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="country">Country:</label>
                                <input
                                    type="text"
                                    id="country"
                                    className="form-control"
                                    {...register("country", { required: "Campo obligatorio" })}
                                />
                                {errors.country && <span className="error">{errors.country.message}</span>}
                            </div>
                            <div className="form-group">
                                <label htmlFor="biography">Biography:</label>
                                <textarea
                                    id="biography"
                                    className="form-control"
                                    {...register("biography", { required: "Campo obligatorio" })}
                                    />
                                {errors.biography && <span className="error">{errors.biography.message}</span>}
                            </div>
                            <div>
                                <button className="btn btn-primary mx-1 my-1" type="submit">Guardar</button>
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
