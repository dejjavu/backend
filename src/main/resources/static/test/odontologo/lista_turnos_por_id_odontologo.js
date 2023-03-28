const form = document.querySelector('form');
form.addEventListener('submit', (event) => {
  event.preventDefault();
  const odontologoId = document.querySelector('#odontologo-id').value;
  fetch(`/odontologos/${odontologoId}/turnos`)
    .then(response => response.json())
    .then(turnos => {
      const table = document.querySelector('#lista-odontologos');
      const tbody = table.querySelector('tbody');
      tbody.innerHTML = '';
      turnos.forEach(turno => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${turno.id}</td>
          <td>${turno.odontologo.id}</td>
          <td>${turno.odontologo.nombre}</td>
          <td>${turno.paciente.id}</td>
          <td>${turno.paciente.nombre}</td>
          <td>${turno.fechaAltaTurno}</td>
          <td>${turno.fechaDeTurno}</td>
        `;
        tbody.appendChild(row);
      });
    });
});
