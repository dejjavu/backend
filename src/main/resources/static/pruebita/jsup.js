
document.addEventListener('click', (event) => {
  if (event.target.classList.contains('editar')) {
    const id = event.target.dataset.id;
    mostrarFormularioModificar(id);
  }
});

function mostrarFormularioModificar(id) {
  // Muestra el formulario
  const formModificar = document.getElementById('form-modificar');
  formModificar.style.display = 'block';

  // Encuentra el odontólogo por ID y carga los datos en el formulario
  const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
  const nombre = row.children[1].textContent;
  const apellido = row.children[2].textContent;
  const matricula = row.children[3].textContent;

  document.getElementById('modificar-id').value = id;
  document.getElementById('modificar-nombre').value = nombre;
  document.getElementById('modificar-apellido').value = apellido;
  document.getElementById('modificar-matricula').value = matricula;
}
document.getElementById('form-modificar').addEventListener('submit', (event) => {
  event.preventDefault();

  // Recupera los datos del formulario
  const id = document.getElementById('modificar-id').value;
  const nombre = document.getElementById('modificar-nombre').value;
  const apellido = document.getElementById('modificar-apellido').value;
  const matricula = document.getElementById('modificar-matricula').value;

  // Realiza la petición PUT para actualizar el odontólogo
  fetch(`/odontologos/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, matricula }),
  })
    .then(() => {
      alert('Odontólogo modificado correctamente');

      // Actualiza la tabla con los nuevos datos
      const row = document.querySelector(`[data-id="${id}"]`).closest('tr');
      row.children[1].textContent = nombre;
      row.children[2].textContent = apellido;
      row.children[3].textContent = matricula;

      // Oculta el formulario
      document.getElementById('form-modificar').style.display = 'none';
    })
    .catch(error => {
      console.error(error);
      alert('Error al modificar el odontólogo');
    });
});