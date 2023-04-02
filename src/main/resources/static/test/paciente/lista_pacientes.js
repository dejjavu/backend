// Obtener referencia a la tabla en el HTML
const tableBody = document.querySelector('#lista-pacientes tbody');

// Hacer una petición GET a la URL /pacientes
fetch('/pacientes')
  .then(response => response.json()) // parsear la respuesta a JSON
  .then(data => {
    // Recorrer los datos obtenidos y construir las filas de la tabla
    data.forEach(paciente => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${paciente.id}</td>
        <td>${paciente.nombre}</td>
        <td>${paciente.apellido}</td>
        <td>${paciente.domicilio.calle}</td>
        <td>${paciente.domicilio.numero}</td>
        <td>${paciente.domicilio.localidad}</td>
        <td>${paciente.domicilio.provincia}</td>
        <td>${paciente.fechaAlta}</td>
        <td><button class="btn btn-danger" data-id="${paciente.id}">Eliminar</button></td>
`;
      tableBody.appendChild(row);
    });
  })


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/pacientes/${id}`, { method: 'DELETE' })
      .then(() => {
        event.target.closest('tr').remove();
      })
      .catch(error => console.error(error));
  }
});



