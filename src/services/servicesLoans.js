import serviceLibrary from "./serviceLibrary";

const urlBase = "http://localhost:8080/api/v1/loans";

const getObjectsLoans = async (idLibro) => {
    try {
        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error("No hay token de autenticación.");
        }

        const book = await serviceLibrary.getOneBook(idLibro);
        const idLoans = book?.loansId;
        
        if (!idLoans || idLoans.length === 0) {
            console.log("No hay préstamos para este libro.");
            return [];
        }

        const loanResults = await Promise.allSettled(
            idLoans.map(async (idLoan) => {
                const response = await fetch(`${urlBase}/${idLoan}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    },
                });

                if (!response.ok) {
                    const errorMessage = await response.text();
                    throw new Error(`Error en préstamo ${idLoan}: ${response.status} - ${errorMessage}`);
                }

                return response.json();
            })
        );

        const loans = loanResults
            .filter(result => result.status === "fulfilled")
            .map(result => result.value);

        console.log("Préstamos obtenidos:", loans);
        return loans;
    } catch (error) {
        console.error("Error en getObjectsLoans:", error.message);
        return [];
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


export default { getObjectsLoans, save, remove};
