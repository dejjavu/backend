// Obtener referencia a la tabla en el HTML
const tableBody = document.querySelector('#lista-turnos tbody');

// Hacer una petición GET a la URL /turnos
fetch('/turnos')
  .then(response => response.json()) // parsear la respuesta a JSON
  .then(data => {
    // Recorrer los datos obtenidos y construir las filas de la tabla
    data.forEach(turno => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${turno.id}</td>
        <td>${turno.fechaAltaTurno}</td>
        <td>${turno.paciente.id}</td>
        <td>${turno.paciente.nombre}</td>
        <td>${turno.paciente.apellido}</td>
        <td>${turno.odontologo.id}</td>
        <td>${turno.odontologo.nombre}</td>
        <td>${turno.odontologo.apellido}</td>
        <td>${turno.fechaDeTurno}</td>
        <td><button class="eliminar" data-id="${turno.id}">Eliminar</button></td>
`;
      tableBody.appendChild(row);
    });
  })


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/turnos/${id}`, { method: 'DELETE' })
      .then(() => {
        event.target.closest('tr').remove();
      })
      .catch(error => console.error(error));
  }
});
