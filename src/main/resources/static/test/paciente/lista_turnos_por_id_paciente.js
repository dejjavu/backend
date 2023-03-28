const form = document.querySelector('form');
form.addEventListener('submit', (event) => {
  event.preventDefault();
  const pacienteId = document.querySelector('#paciente-id').value;
  fetch(`/pacientes/${pacienteId}/turnos`)
    .then(response => response.json())
    .then(turnos => {
      const table = document.querySelector('#lista-pacientes');
      const tbody = table.querySelector('tbody');
      tbody.innerHTML = '';
      turnos.forEach(turno => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${turno.id}</td>
          <td>${turno.paciente.id}</td>
          <td>${turno.paciente.nombre}</td>
          <td>${turno.paciente.id}</td>
          <td>${turno.paciente.nombre}</td>
          <td>${turno.fechaAltaTurno}</td>
          <td>${turno.fechaDeTurno}</td>
        `;
        tbody.appendChild(row);
      });
    });
});
