// Obtener referencia a la tabla en el HTML
const tableBody = document.querySelector('#lista-odontologos tbody');

// Hacer una petición GET a la URL /odontologos
fetch('/odontologos')
  .then(response => response.json()) // parsear la respuesta a JSON
  .then(data => {
    // Recorrer los datos obtenidos y construir las filas de la tabla

    data.forEach(odontologo => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${odontologo.id}</td>
        <td>${odontologo.nombre}</td>
        <td>${odontologo.apellido}</td>
        <td>${odontologo.matricula}</td>
        <td><button class="eliminar btn btn-primary" data-id="${odontologo.id}">✖</button></td>
        <td><button class="editar btn btn-primary" data-id="${odontologo.id}">️️✏</button></td>
`;
      tableBody.appendChild(row);
    });
  })


document.addEventListener('click', (event) => {
  if (event.target.classList.contains('eliminar')) {
    const id = event.target.dataset.id;
    fetch(`/odontologos/${id}`, { method: 'DELETE' })
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

document.getElementById('cancelar_modificacion').addEventListener('click', () => {
  document.getElementById('div_odontologo_updating').style.display = 'none';
});

document.getElementById('update_odontologo_form').addEventListener('submit', (event) => {
  event.preventDefault();

  const id = document.getElementById('odontologo_id').value;
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const matricula = document.getElementById('matricula').value;

  fetch(`/odontologos}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, matricula }),
  })
    .then(() => {
      alert('Odontólogo modificado correctamente');
      location.reload();
      const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
      row.children[1].textContent = nombre;
      row.children[2].textContent = apellido;
      row.children[3].textContent = matricula;

      document.getElementById('div_odontologo_updating').style.display = 'none';
    })
    .catch(error => {
      console.error(error);
      alert('Error al modificar el odontólogo');
    });
});

window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            location.reload();
        })

    })
 });

function findBy(id) {
    const url = '/odontologos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        document.querySelector('#odontologo_id').value = odontologo.id;
        document.querySelector('#nombre').value = odontologo.nombre;
        document.querySelector('#apellido').value = odontologo.apellido;
        document.querySelector('#matricula').value = odontologo.matricula;
        document.querySelector('#div_odontologo_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}
