const btnEnviar = document.getElementById('btnEnviar');

// Añadir un evento submit al formulario
formulario.addEventListener('submit', (event) => {
  event.preventDefault(); // Prevenir el envío por defecto del formulario

  // Crear un objeto con los datos a enviar
    const paciente = {
      id: id.value,
    };

    const odontologo = {
      id: id.value,
    };

      const turno = {
        fechaDeTurno: fechaDeTurno.value,
        paciente : paciente,
        odontologo : odontologo,
      };

const settings = {method: 'POST',
                     headers: {
                       'Content-Type': 'application/json'
                     },
                     body: JSON.stringify(turno)}


  // Realizar la solicitud POST al servidor
  fetch('/turnos', settings  )
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al enviar los datos');
    }
    // Si la respuesta es correcta, mostrar un mensaje de éxito
    alert('Los datos se han guardado correctamente');
    location.reload();
  })
  .catch(error => {
    console.error(error);
    alert('Ha ocurrido un error al guardar los datos');
    location.reload();
  });
});
