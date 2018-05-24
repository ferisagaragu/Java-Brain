function makeTable(name) {
    
    var intput = '<input type="text" class="form-control" id="'+name+'Search" placeholder="Buscar en '+name+'..."/><br>';
    
    var table = '<tr>' +
                    '<th>Nombre</th>' +
                    '<th>Versión</th>' +
                    '<th>Paquete</th>' +
                    '<th>Descripción</th>' +
                    '<th>Comenzar</th>' +
                '</tr>';

    $.getJSON("json/" + name + "/" + name + ".json", function (data) {
        for (var i = 0; i < data.util.length; i++) {
            table +=
                    '<tr>' +
                    '<td>' + data.util[i].name + '</td>' +
                    '<td>'+ data.util[i].version + '</td>' +
                    '<td>' + data.util[i].package + '</td>' +
                    '<td><p>' + data.util[i].description + '</p></td>' +
                    '<td><a href="/doc/doc.html?class='+data.util[i].name+'&categorie='+name+'" class="btn btn-info">Primeros pasos</a></td>' +
                    '<tr>';
            $('#'+name).html(intput+'<table class="table col-md-12" id="'+name+'Table">'+table+'</table>');
        }
    });
}

function onKey(name){
    
    $('#' + name).keyup(function (evt) {
        var input, filter, table, tr, td, i;
        input = document.getElementById(name + 'Search');
        filter = input.value.toUpperCase();
        table = document.getElementById(name);
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    });
    
}