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
        <td>${paciente.dni}</td>
                <td>${paciente.domicilio.calle}</td>
                      <td>${paciente.domicilio.numero}</td>
                            <td>${paciente.domicilio.localidad}</td>
                                  <td>${paciente.domicilio.provincia}</td>
        <td><button class="eliminar btn btn-primary" data-id="${paciente.id}">✖</button></td>
        <td><button class="editar btn btn-primary" data-id="${paciente.id}">️️✏</button></td>
`;
      tableBody.appendChild(row);
    });
  })


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/pacientes/${id}`, { method: 'DELETE' })
      .then(() => {
        alert('Odontologo eliminado correctamente');
        event.target.closest('tr').remove();
      })
      .catch(error => console.error(error));
  }
});


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('editar')) {
    const id = event.target.dataset.id;
    mostrarFormularioModificar(id);
  }
});

function mostrarFormularioModificar(id) {
  const formModificar = document.getElementById('update_paciente_form');
  const divUpdating = document.getElementById('div_paciente_updating');
  divUpdating.style.display = 'block';

  const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
  const nombre = row.children[1].textContent;
  const apellido = row.children[2].textContent;
  const dni = row.children[3].textContent;
    const calle = row.children[4].textContent;
      const numero = row.children[5].textContent;
            const localidad = row.children[6].textContent;
                        const provincia = row.children[7].textContent;

  document.getElementById('paciente_id').value = id;
  document.getElementById('nombre').value = nombre;
  document.getElementById('apellido').value = apellido;
  document.getElementById('dni').value = dni;
    document.getElementById('calle').value = calle;
        document.getElementById('numero').value = numero;
                document.getElementById('localidad').value = localidad;
                        document.getElementById('provincia').value = provincia;
}

document.getElementById('cancelar_modificacion').addEventListener('click', () => {
  document.getElementById('div_paciente_updating').style.display = 'none';
});

document.getElementById('update_paciente_form').addEventListener('submit', (event) => {
  event.preventDefault();

  const id = document.getElementById('paciente_id').value;
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const matricula = document.getElementById('matricula').value;

  fetch(`/pacientes}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, matricula }),
  })
    .then(() => {
      alert('Odontólogo modificado correctamente');
      location.reload();
  const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
  const nombre = row.children[1].textContent;
  const apellido = row.children[2].textContent;
  const dni = row.children[3].textContent;
  const calle = row.children[4].textContent;
  const numero = row.children[5].textContent;
  const localidad = row.children[6].textContent;
  const provincia = row.children[7].textContent;

      document.getElementById('div_paciente_updating').style.display = 'none';
    })
    .catch(error => {
      console.error(error);
      alert('Error al modificar el pacientes');
    });
});

window.addEventListener('load', function () {
  const formulario = document.querySelector('#update_paciente_form');
  formulario.addEventListener('submit', (e) => {
    e.preventDefault();

    const domicilio = {
      calle: document.querySelector('#calle').value,
      numero: document.querySelector('#numero').value,
      localidad: document.querySelector('#localidad').value,
      provincia: document.querySelector('#provincia').value,
    };

    const formData = {
      id: document.querySelector('#paciente_id').value,
      nombre: document.querySelector('#nombre').value,
      apellido: document.querySelector('#apellido').value,
      dni: document.querySelector('#dni').value,
      domicilio:domicilio,
    };
    const url = '/pacientes';
    const settings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
    };

    fetch(url,settings)
      .then(response => {
        if (!response.ok) {
          throw new Error(response.status);
        }
        return response.json();
      })
      .then(data => {
        // Aquí manejas el caso de éxito
        console.log(data);
      })
      .catch(error => {
        // Aquí manejas el error
        alert("Error: " + error);
      });
  });
});

function findBy(id) {
  const url = '/pacientes'+"/"+id;
  const settings = {
    method: 'GET'
  };
  fetch(url,settings)
    .then(response => response.json())
    .then(data => {
      let paciente = data;
      document.querySelector('#paciente_id').value = paciente.id;
      document.querySelector('#nombre').value = paciente.nombre;
      document.querySelector('#apellido').value = paciente.apellido;
      document.querySelector('#dni').value = paciente.dni;
      document.querySelector('#calle').value = paciente.matricula;
      document.querySelector('#numero').value = paciente.matricula;
      document.querySelector('#localidad').value = paciente.matricula;
      document.querySelector('#provincia').value = paciente.matricula;
      document.querySelector('#div_paciente_updating').style.display = "block";
    })
    .catch(error => {
      alert("Error: " + error);
    });
}
