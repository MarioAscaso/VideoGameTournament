const API_BASE_URL = "/api/games";
const CREATE_URL   = "/game/new";
const UPDATE_URL   = "/game/update";
const DELETE_URL   = "/games/delete";

async function loadGames() {
    const tableBody = document.getElementById('gamesTableBody');
    if (!tableBody) return;

    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error("Error en servidor");
        const games = await response.json();

        tableBody.innerHTML = '';
        if (games.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="7">No hay juegos.</td></tr>';
            return;
        }
        games.forEach(game => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${game.id}</td>
                <td>${game.name}</td>
                <td>${game.description}</td>
                <td>${game.associatedID}</td>
                <td>${game.bannerImage ? `<img src="/files/${game.bannerImage}" class="game-image">` : 'Sin imagen'}</td>
                <td>${game.cardImage ? `<img src="/files/${game.cardImage}" class="game-image">` : 'Sin imagen'}</td>
                <td>
                    <button onclick="window.location.href='/editGame.html?id=${game.id}'" style="background-color:orange; cursor:pointer;">Editar</button>
                    <button onclick="deleteGame(${game.id})" style="background-color:red; color:white; cursor:pointer;">Eliminar</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    } catch (e) {
        console.error(e);
        tableBody.innerHTML = '<tr><td colspan="7">Error de conexión.</td></tr>';
    }
}

async function createGame(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    try {
        const response = await fetch(CREATE_URL, { method: 'POST', body: formData });
        if (response.ok) {
            alert("Creado correctamente");
            window.location.href = '/index.html';
        } else {
            alert("Error al crear: " + await response.text());
        }
    } catch (e) { alert("Error de red"); }
}

async function loadGameForEdit() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    if (!id || !document.getElementById('editGameForm')) return;

    try {
        const response = await fetch(`${API_BASE_URL}/${id}`);
        if (!response.ok) throw new Error("Juego no encontrado");
        const game = await response.json();
        document.getElementById('gameId').value = game.id;
        document.getElementById('name').value = game.name;
        document.getElementById('description').value = game.description;
        document.getElementById('associatedID').value = game.associatedID;
    } catch (e) {
        alert("Error cargando juego");
        window.location.href = '/index.html';
    }
}

async function updateGame(event) {
    event.preventDefault();
    const id = document.getElementById('gameId').value;
    const formData = new FormData(event.target);

    try {
        const response = await fetch(`${UPDATE_URL}/${id}`, { method: 'POST', body: formData });
        if (response.ok) {
            alert("Actualizado correctamente");
            window.location.href = '/index.html';
        } else {
            alert("Error al actualizar");
        }
    } catch (e) { alert("Error de red"); }
}

async function deleteGame(id) {
    if (!confirm("¿Eliminar juego?")) return;
    const formData = new FormData();
    formData.append('id', id);
    try {
        await fetch(DELETE_URL, { method: 'POST', body: formData });
        window.location.reload();
    } catch (e) { window.location.reload(); }
}