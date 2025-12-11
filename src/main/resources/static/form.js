// Rutas de tu API (coinciden con tus @Controller)
const API_BASE_URL = "/api/games"; // Para listar y borrar
const CREATE_URL = "/game/new";    // Para crear (subida de ficheros)

/**
 * 1. Lógica para LISTAR juegos (index.html)
 */
async function loadGames() {
    const tableBody = document.getElementById('gamesTableBody');

    // Si no estamos en la página de la lista (index.html), salimos
    if (!tableBody) return;

    try {
        const response = await fetch(API_BASE_URL);

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const games = await response.json();
        tableBody.innerHTML = ''; // Limpiar tabla antes de pintar

        if (games.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="7">No hay juegos registrados.</td></tr>';
            return;
        }

        games.forEach(game => {
            const row = document.createElement('tr');

            // Construimos la fila.
            // IMPORTANTE: Añadimos '/uploads/' delante del nombre de la imagen.
            row.innerHTML = `
                <td>${game.id}</td>
                <td>${game.name}</td>
                <td>${game.description}</td>
                <td>${game.associatedID}</td>
                <td>
                    ${game.bannerImage ? `<img src="/uploads/${game.bannerImage}" alt="Banner" width="80" height="50" style="object-fit: cover;">` : 'Sin imagen'}
                </td>
                <td>
                    ${game.cardImage ? `<img src="/uploads/${game.cardImage}" alt="Carta" width="50" height="70" style="object-fit: cover;">` : 'Sin imagen'}
                </td>
                <td>
                    <button onclick="deleteGame(${game.id})" style="background-color: #ff4444; color: white; border: none; padding: 5px 10px; cursor: pointer;">Eliminar</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error cargando juegos:', error);
        tableBody.innerHTML = '<tr><td colspan="7" style="color:red">Error conectando con el servidor.</td></tr>';
    }
}

/**
 * 2. Lógica para CREAR juego (newGame.html)
 */
async function createGame(event) {
    event.preventDefault(); // Evita que el formulario recargue la página

    // FormData captura automáticamente los textos y los archivos (MultipartFile)
    const formData = new FormData(event.target);

    try {
        const response = await fetch(CREATE_URL, {
            method: 'POST',
            body: formData
            // NOTA: No ponemos 'Content-Type': 'application/json' ni 'multipart/form-data'.
            // El navegador lo detecta y lo pone automáticamente con el "boundary" correcto.
        });

        if (response.ok) {
            alert("¡Juego guardado correctamente!");
            window.location.href = '/index.html'; // Redirigir a la lista
        } else {
            // Intentar leer el error si el servidor devuelve texto
            const errorText = await response.text();
            alert('Error al guardar: ' + errorText);
        }
    } catch (error) {
        console.error('Error de red:', error);
        alert('Error de conexión con el servidor');
    }
}

/**
 * 3. Lógica para ELIMINAR juego
 */
async function deleteGame(id) {
    if (!confirm('¿Estás seguro de que quieres eliminar este juego permanentemente?')) return;

    try {
        // Petición DELETE a /api/games/{id}
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            loadGames(); // Recargar la tabla para ver que desaparece
        } else {
            alert('No se pudo eliminar el juego.');
        }
    } catch (error) {
        console.error('Error al eliminar:', error);
    }