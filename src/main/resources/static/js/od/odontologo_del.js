const formularioOdontologo = document.getElementById('formulario-odontologo');

const btnEliminar = document.getElementById('btnEliminar');

btnEliminar.addEventListener('click', (e) => {
  e.preventDefault();
  const idOdontologo = document.getElementById('idOdontologo').value;
  fetch(`/odontologos/${idOdontologo}`, {
    method: 'DELETE'
  })
    .then(response => {
      if (response.ok) {
        alert('El odontólogo ha sido eliminado correctamente');
        window.location.replace('/odontologos_ok.html'); // Recarga la página después de aceptar el alert
      } else {
        alert('No se pudo eliminar el odontólogo');
      }
    });
});
