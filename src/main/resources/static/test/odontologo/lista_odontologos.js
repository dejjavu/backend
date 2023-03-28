const listaOdontologos = document.getElementById('lista-odontologos');

fetch('/odontologos')
  .then(response => response.json())
  .then(odontologos => {
    odontologos.forEach(odontologo => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${odontologo.id}</td>
        <td>${odontologo.nombre}</td>
        <td>${odontologo.apellido}</td>
        <td>${odontologo.matricula}</td>
      `;
      listaOdontologos.appendChild(row);
    });
  });
