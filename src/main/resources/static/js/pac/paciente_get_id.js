const formularioPaciente = document.getElementById('formulario-paciente');

const btnBuscar = document.getElementById('btnBuscar');

btnBuscar.addEventListener('click', (e) => {
  e.preventDefault();
  const idPaciente = document.getElementById('idPaciente').value;
  fetch(`/pacientes/${idPaciente}`)
    .then(response => response.json())
    .then(paciente => {
      if (paciente && paciente.nombre && paciente.apellido) {
        const mensaje = `ID Consultado: ${paciente.id}\nNombre: ${paciente.nombre}\nApellido: ${paciente.apellido}\nDNI: ${paciente.dni}`;
        alert(mensaje);
         window.location.replace('/pacientes_ok.html'); // Recarga la página después de aceptar el alert
      } else {
        alert('No se encontró ningún paciente con ese ID');
      }
    });
});
