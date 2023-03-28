const listaPacientes = document.getElementById('lista-pacientes');

fetch('/pacientes')
  .then(response => response.json())
  .then(pacientes => {
    pacientes.forEach(paciente => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${paciente.id}</td>
        <td>${paciente.nombre}</td>
        <td>${paciente.apellido}</td>
        <td>${paciente.domicilio.calle}</td>
        <td>${paciente.domicilio.numero}</td>
        <td>${paciente.domicilio.localidad}</td>
        <td>${paciente.domicilio.provincia}</td>
                <td>${paciente.fechaIngreso}</td>

      `;
      listaPacientes.appendChild(row);
    });
  });
