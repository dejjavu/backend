// Obtener referencia a la tabla en el HTML
const tableBody = document.querySelector('#lista-odontologos tbody');

// Obtener la lista de odontólogos y construir la tabla
function getOdontologos() {
  fetch('/odontologos')
    .then(response => response.json()) // parsear la respuesta a JSON
    .then(data => {
      // Recorrer los datos obtenidos y construir las filas de la tabla
      data.forEach(({id, nombre, apellido, matricula}) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${id}</td>
          <td>${nombre}</td>
          <td>${apellido}</td>
          <td>${matricula}</td>
          <td><button class="eliminar btn btn-primary" data-id="${id}">✖</button></td>
          <td><button class="editar btn btn-primary" data-id="${id}">️️✏</button></td>
        `;
        tableBody.appendChild(row);
      });
    })
    .catch(error => console.error(error));
}

// Eliminar un odontólogo
document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/odontologos/${id}`, { method: 'DELETE' })
      .then(() => {
        alert('Odontólogo eliminado correctamente');
        event.target.closest('tr').remove();
      })
      .catch(error => console.error(error));
  }
});

// Mostrar el formulario de modificación de un odontólogo
document.addEventListener('click', (event) => {
  if (event.target.classList.contains('editar')) {
    const id = event.target.dataset.id;
    mostrarFormularioModificar(id);
  }
});

function mostrarFormularioModificar(id) {
  const formModificar = document.getElementById('update_odontologo_form');
  const divUpdating = document.getElementById('div_odontologo_updating');
  divUpdating.style.display = 'block';

  const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
  const nombre = row.children[1].textContent;
  const apellido = row.children[2].textContent;
  const matricula = row.children[3].textContent;

  document.getElementById('odontologo_id').value = id;
  document.getElementById('nombre').value = nombre;
  document.getElementById('apellido').value = apellido;
  document.getElementById('matricula').value = matricula;
}

// Modificar un odontólogo
document.getElementById('update_odontologo_form').addEventListener('submit', (event) => {
  event.preventDefault();

  const id = document.getElementById('odontologo_id').value;
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const matricula = document.getElementById('matricula').value;

  fetch(`/odontologos/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, matricula })
  })
    .then(() => {
      alert('Odontólogo modificado correctamente');
      document.getElementById('update_odontologo_form').reset();
      document.getElementById('div_odontologo_updating').style.display = 'none';
      actualizarTabla();
    })
    .catch(error => console.error(error));
});

// Actualizar la tabla después de una modificación
function actualizarTabla() {
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
  getOdontologos();
}

// Cerrar el formulario de modificación de un odontólogo
document.getElementById('close_form_button').addEventListener('click', () => {
  document.getElementById('update_odontologo_form').reset();
  document.getElementById('div_odontologo_updating').style.display = 'none';
});

// Mostrar el formulario de creación de un odontólogo
document.getElementById('add_odontologo_button').addEventListener('click', () => {
  document.getElementById('div_odontologo_adding').style.display = 'block';
});

// Agregar un odontólogo
document.getElementById('add_odontologo_form').addEventListener('submit', (event) => {
  event.preventDefault();

  const nombre = document.getElementById('new_nombre').value;
  const apellido = document.getElementById('new_apellido').value;
  const matricula = document.getElementById('new_matricula').value;

  fetch('/odontologos', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, matricula })
  })
    .then(() => {
      alert('Odontólogo agregado correctamente');
      document.getElementById('add_odontologo_form').reset();
      document.getElementById('div_odontologo_adding').style.display = 'none';
      actualizarTabla();
    })
    .catch(error => console.error(error));
});

// Cerrar el formulario de creación de un odontólogo
document.getElementById('close_new_form_button').addEventListener('click', () => {
  document.getElementById('add_odontologo_form').reset();
  document.getElementById('div_odontologo_adding').style.display = 'none';
});

// Inicializar la tabla de odontólogos
getOdontologos();