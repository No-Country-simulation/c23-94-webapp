import { useState } from 'react';
import '../../styles/table.css';
import React, {useEffect } from "react";

export default function Tabla(props) {

    const onActualizarClick = async(item) => {
        props.onActualizar(item);
    };

    const onEliminarClick = async (id) => {
        props.onEliminar(id);
    }    

    const tdata = props.rows.map((e) => {
        const fechaActual = new Date(); 
        const fechaVencimiento = new Date(e.dueDate); 
        const fechaInicio = new Date(e.loanDate);
        const mostrarBoton = props.isAdmin || fechaVencimiento >= fechaActual;
        const botonEliminar = props.isAdmin || fechaInicio >= fechaActual;
        return (
            <tr key={e.id}>
                <td>{e.loanDate}</td>
                <td>{e.dueDate}</td>
                <td>
                {mostrarBoton && (
                    <button 
                        className="btn btn-warning me-2" 
                        onClick={() => onActualizarClick(e) }
                    >
                        Actualizar
                    </button>
                )}
                {botonEliminar && (
                    <button 
                        className="btn btn-danger" 
                        onClick={() => onEliminarClick(e.id)}
                    >
                        Eliminar
                    </button>
                )}
                </td>
            </tr>
        );
    });

    return (
        <>
            <div className="card">
                <div className="card-header d-flex justify-content-center">
                    <h6 className="text-center">Historial de Préstamos del libro</h6>
                </div>
                <div className="card-body">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th>Fecha Comienzo</th>
                                <th>Fecha Finalización</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {tdata}
                        </tbody>
                    </table>
                </div>
                <div className='d-flex btn-container'>
                    <button className="btn btn-custom" onClick={props.onVolver}>Volver</button>
                </div>
            </div>
        </>
    );
}