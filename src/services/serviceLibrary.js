const URL = "http://localhost:8080/api/v1/books";

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
            throw new Error(`Error al obtener los libros: ${res.status} - ${errorMessage}`);
        }

        const data = await res.json();
        console.log(data)
        return data;
    } catch (error) {
        console.error("Error al obtener los libros:", error.message);
        throw error;  // Re-lanzamos el error para manejarlo más arriba
    }
};

const getNombresAuthors = async () => {
    const urlNueva = "http://localhost:8080/api/v1/authors";

    try {
        const res = await fetch(urlNueva, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`,  // Token incluido
            },
        });

        if (!res.ok) {
            const errorMessage = await res.text();
            throw new Error(`Error en la petición: ${res.status} - ${errorMessage}`);
        }

        const datos = await res.json();
        return datos.map(e => ({
            lastName: e.lastName,
            name: e.name,
            id: e.id
        }));

    } catch (error) {
        console.error("Error obteniendo los autores:", error.message);
        return [];
    }
};


const getNombresPublishers = async () => {
    const urlNueva = "http://localhost:8080/api/v1/publishers";

    try {
        const res = await fetch(urlNueva, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`,  // Token incluido
            },
        });

        if (!res.ok) {
            const errorMessage = await res.text();
            throw new Error(`Error en la petición: ${res.status} - ${errorMessage}`);
        }

        const datos = await res.json();     
        return datos.map(e => ({
            name: e.name,
            id: e.id
        }));

    } catch (error) {
        console.error("Error obteniendo las editoras:", error);
        return [];
    }
};
const getNombresCategories = async () => {
    const urlNueva = "http://localhost:8080/api/v1/categories";

    try {
        const res = await fetch(urlNueva, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`,  // Token incluido
            },
        });

        if (!res.ok) {
            const errorMessage = await res.text();
            throw new Error(`Error en la petición: ${res.status} - ${errorMessage}`);
        }
        const datos = await res.json(); 
        return datos.map(e => ({
            name: e.category,
            id: e.id
        }));

    } catch (error) {
        console.error("Error obteniendo las categorías:", error);
        return [];
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
            method: data.id ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(data),
        };

        const url = data.id ? `${URL}/${data.id}` : URL;

        const response = await fetch(url, config);

        if (!response.ok) {
            const errorMessage = await response.text(); // Obtener el mensaje de error
            throw new Error(`Error en la solicitud de guardar: ${errorMessage}`);
        }

        const responseData = await response.json();
        return responseData;
    } catch (error) {
        console.error('Error en la solicitud:', error);
        throw error;
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




export default { remove, getAll, save, getNombresAuthors, getNombresCategories, getNombresPublishers};
