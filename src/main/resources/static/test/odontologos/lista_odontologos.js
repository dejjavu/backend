//// Obtener referencia a la tabla en el HTML
//const tableBody = document.querySelector('#lista-odontologos tbody');
//
//// Hacer una petición GET a la URL /odontologos
//fetch('/odontologos')
//  .then(response => response.json()) // parsear la respuesta a JSON
//  .then(data => {
//    // Recorrer los datos obtenidos y construir las filas de la tabla
//    data.forEach(odontologo => {
//      const row = document.createElement('tr');
//      row.innerHTML = `
//        <td>${odontologo.id}</td>
//        <td>${odontologo.nombre}</td>
//        <td>${odontologo.apellido}</td>
//        <td>${odontologo.matricula}</td>
//        <td><button class="eliminar btn btn-primary" data-id="${odontologo.id}">✖</button></td>
//        <td><button class="btn btn-primary" data-id="${odontologo.id}">️️✏</button></td>
//`;
//      tableBody.appendChild(row);
//    });
//  })
//
//
//document.addEventListener('click', (event) => {
//  if (event.target.classList.contains('eliminar')) {
//    const id = event.target.dataset.id;
//    fetch(`/odontologos/${id}`, { method: 'DELETE' })
//      .then(() => {
//        alert('Odontologo eliminado correctamente');
//        event.target.closest('tr').remove();
//      })
//      .catch(error => console.error(error));
//  }
//});
//
//
//const mostrarForm = () =>{
//document.getElementById("formularito").style.display = "flex";
//}
//
//document.addEventListener('click', (event) =>{
//
//})


window.addEventListener('load', function () {
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {

        for(odontologo of data){
            var table = document.getElementById("odontologoTable");
            var odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                              ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';
            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                              ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                              odontologo.id +
                              '</button>';


            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});