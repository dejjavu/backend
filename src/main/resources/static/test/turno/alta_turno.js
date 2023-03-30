// Obtener el formulario
const formulario = document.querySelector('#formulario');

// Función que se ejecutará al enviar el formulario
formulario.addEventListener('submit', function(e) {
    e.preventDefault(); // Prevenir el envío del formulario

    // Obtener los valores de los campos
    const fechaTurno = document.querySelector('#fechaturno').value;
    const pacienteId = document.querySelector('#pacienteid').value;
    const odontologoId = document.querySelector('#odontologoid').value;

    // Crear el objeto turno
    const turno = {
        fechaDeTurno: fechaTurno,
        paciente: {
            id: pacienteId
        },
        odontologo: {
            id: odontologoId
        }
    };

    // Enviar los datos del turno al servidor
    fetch('/turnos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(turno)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data); // Mostrar la respuesta del servidor en la consola
        alert('Turno dado de alta correctamente'); // Mostrar un mensaje de éxito al usuario
        formulario.reset(); // Limpiar el formulario
    })
    .catch(error => {
        console.error(error); // Mostrar el error en la consola
        alert('Error al dar de alta el turno'); // Mostrar un mensaje de error al usuario
    });
});
