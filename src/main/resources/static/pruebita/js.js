// Obtener referencia a la tabla en el HTML
const tableBody = document.querySelector('#lista-odontologos tbody');

// Hacer una petición GET a la URL /odontologos
fetch('/odontologos')
  .then(response => response.json()) // parsear la respuesta a JSON
  .then(data => {
    // Recorrer los datos obtenidos y construir las filas de la tabla
    data.forEach(odontologo => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${odontologo.id}</td>
        <td>${odontologo.nombre}</td>
        <td>${odontologo.apellido}</td>
        <td>${odontologo.matricula}</td>
        <td><button class="eliminar btn btn-primary" data-id="${odontologo.id}">✖</button></td>
        <td><button class="btn btn-primary" data-id="${odontologo.id}">️️✏</button></td>
`;
      tableBody.appendChild(row);
    });
  })


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/odontologos/${id}`, { method: 'DELETE' })
      .then(() => {
        alert('Odontologo eliminado correctamente');
        event.target.closest('tr').remove();
      })
      .catch(error => console.error(error));
  }
});
