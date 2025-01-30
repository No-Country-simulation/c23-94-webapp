const URL = "http://localhost:8080/api/v1/authors";

const getAll = async () => {
    
    try {
        const res = await fetch(URL, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`,  // Incluir el token en los encabezados
            },
        });
        if (!res.ok) {
            const errorMessage = await res.text(); // Lee el texto de la respuesta de error
            throw new Error(`Error al obtener los autores: ${res.status} - ${errorMessage}`);
        }

        const data = await res.json();
        console.log(data)
        return data;
    } catch (error) {
        console.error("Error al obtener los autores:", error.message);
        throw error;  // Re-lanzamos el error para manejarlo más arriba
    }
};


// Función para guardar un autor (crear o actualizar)
const save = async (data) => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }

        const config = {
            method: data.id ? 'PUT' : 'POST',  // Usamos PUT si tiene id, POST si no
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(data), // Convertimos el objeto a JSON
        };

        const url = data.id ? `${URL}/${data.id}` : URL;  // Si tiene id, usamos PUT con el id en la URL

        const response = await fetch(url, config);

        if (!response.ok) {
            throw new Error('Error en la solicitud de guardar');
        }

        const responseData = await response.json();
        return responseData;
    } catch (error) {
        console.error('Error en la solicitud:', error);
        throw error; // Puedes manejar este error más arriba en el componente donde se llame la función
    }
};

const remove = async (id) => {
    const urlNueva = `${URL}/${id}`;
    const token = localStorage.getItem('token');

    const response = await fetch(urlNueva, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    const responseText = await response.text();  // Obtener la respuesta como texto

    if (!response.ok) {
        throw new Error(responseText);
    }

    console.log(responseText); // Muestra el mensaje del servidor en consola

    // Si la respuesta es exitosa, recargamos los datos
    return responseText; // Ahora solo devolvemos el texto
};




export default { remove, getAll, save};
