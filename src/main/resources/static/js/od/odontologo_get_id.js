const formularioOdontologo = document.getElementById('formulario-odontologo');

const btnBuscar = document.getElementById('btnBuscar');

btnBuscar.addEventListener('click', (e) => {
  e.preventDefault();
  const idOdontologo = document.getElementById('idOdontologo').value;
  fetch(`/odontologos/${idOdontologo}`)
    .then(response => response.json())
    .then(odontologo => {
      if (odontologo && odontologo.nombre && odontologo.apellido) {
        const mensaje = `ID Consultado: ${odontologo.id}\nNombre: ${odontologo.nombre}\nApellido: ${odontologo.apellido}\nMatricula: ${odontologo.matricula}`;
        alert(mensaje);
         window.location.replace('/odontologos_ok.html'); // Recarga la página después de aceptar el alert
      } else {
        alert('No se encontró ningún odontologo con ese ID');
      }
    });
});

