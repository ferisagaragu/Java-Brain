function makeTable(name) {
    
    var table = '<tr>' +
                    '<th>Nombre</th>' +
                    '<th>Versión</th>' +
                    '<th>Paquete</th>' +
                    '<th>Comenzar</th>' +
                '</tr>';

    $.getJSON("json/" + name + "/" + name + ".json", function (data) {
        for (var i = 0; i < data.util.length; i++) {
            table +=
                    '<tr>' +
                    '<td>' + data.util[i].name + '</td>' +
                    '<td>' + data.util[i].version + '</td>' +
                    '<td>' + data.util[i].package + '</td>' +
                    '<td><button type="button" class="btn btn-info">Primeros pasos</button></td>' +
                    '<tr>';
            $('#'+name).html(table);
        }
    });
}


