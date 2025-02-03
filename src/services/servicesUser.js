
const urlBase = "http://localhost:8080/api/v1/users";

const getOneUser = async (email) => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error("No hay token de autenticación.");
        }
        
        const response = await fetch(`${urlBase}/email/${email}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            }
        });
        
        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(`Error en usuario ${email}: ${response.status} - ${errorMessage}`);
        }

        const data = await response.json();
        
        if (!data || Object.keys(data).length === 0) {
            console.error("Respuesta vacía o incorrecta:", data);
            throw new Error("No se encontró el usuario o la respuesta es inválida.");
        }

        return data;  // Si todo va bien, retorna los datos del usuario

    } catch (error) {
        console.error("Error en getOneUser:", error.message);
        return null;  // Retorna null o un valor que puedas manejar en el componente
    }
};


const save = async (data) => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error('Token no encontrado. Por favor, inicie sesión.');
        }

        const config = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(data),
        };

        const url = `${urlBase}/${data.id}`;

        const response = await fetch(url, config);

        if (!response.ok) {
            const errorMessage = await response.text(); 
            throw new Error(`Error en la solicitud de actualizar: ${errorMessage}`);
        }

        const responseData = await response.json();
        return responseData;
    } catch (error) {
        console.error('Error en la solicitud:', error);
        throw error;
    }
};

const remove = async (id) => {
    const urlNueva = `${urlBase}/${id}`;
    const token = localStorage.getItem('token');

    const response = await fetch(urlNueva, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    });

    const responseText = await response.text();  

    if (!response.ok) {
        throw new Error(responseText);
    }

    console.log(responseText); 

    return responseText;
};


export default { getOneUser, save, remove};
