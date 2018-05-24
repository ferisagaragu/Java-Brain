(function ($) {
    "use strict"; // Start of use strict
    renderInfo(getParameterByName('class'),getParameterByName('categorie'));
})(jQuery); // End of use strict

function renderInfo(jsonName,categorie){
    $('#className').html('<a href="/doc/index.html"><h2 style="color:white;">'+jsonName+'</h2></a>');
    
    $.getJSON("json/" + categorie + "/" + jsonName + ".json", function (data) {
        
        var author = data.author;
        var constante = data.constant;
        var constructor = data.constructor;
        var method = data.method;

        if (typeof author !== 'undefined') {
            var out = '';
            $.each(author, function (key, value) {
                out += '<div class="list-group">' +
                        '<a href="' + value.contactname + '" class="list-group-item">' +
                        '<h4 class="list-group-item-heading">' + value.name + '</h4>' +
                        '<p class="list-group-item-text">' + value.contactname + '</p>' +
                        '</a>' +
                        '</div><hr>';
            });
            $('#authorvar').html(out);
        }
        
        if (typeof constante !== 'undefined') {
            var out = '';
            $.each(constante, function (key, value) {
                out += '<ul class="list-group" style="margin-bottom: 20px;">' +
                        '<li class="list-group-item">' +
                        '<b>ID :</b><font color=#B71C1C>&nbsp;&nbsp;'+value.id+'</font><br>'+
                        '<b>Nombre: </b><font color=#2196F3>&nbsp;&nbsp;<search>' + value.name + '</search></font><br>' +
                        '<b>Descripción: </b>&nbsp;&nbsp;' + value.description + '<br>' +
                        '<b>Retorno: </b><font color=#FF9800>&nbsp;&nbsp;' + value.value + '</font><br>' +
                        '<b>Tipo: <font color=#00BCD4>&nbsp;&nbsp;' + value.type + '</font></b><br>' +
                        '<b>Codígo fuente: </b>&nbsp;&nbsp;<pre><code>' + value.code + '</pre></code>' +
                        '<b>Ejemplo: </b><pre><code>' + value.example + '</code></pre>' +
                        '</li>' +
                        '</ul>';
            });
            $('#constvar').html('<input style="margin-bottom: 20px;" type="text" placeholder="Buscar..." class="form-control" id="searchconstant"/><div id="constantlist">'+out+'</div>');
            onKey('searchconstant','constantlist');
        }
        console.log(constructor);
        if (typeof constructor[0] !== 'undefined') {
            var out = '';
            $.each(constructor, function (key, value) {
                var parameter = value.parameter;
                var out2 = '';

                if (parameter.length !== 0 || typeof parameter !== 'undefined') {
                    $.each(parameter, function (key, value) {

                        out2 += '<b><font color=#00BCD4>' + value.type + '</font></b>&nbsp;&nbsp;' + value.name +
                                '<p>' + value.desc + '</p>';

                    });
                } else {
                    out2 = 'Este método no tiene parametros de entrada';
                }

                out += '<ul class="list-group" style="margin-bottom: 20px;">' +
                        '<li class="list-group-item">' +
                        '<b>ID :</b><font color=#B71C1C>&nbsp;&nbsp;'+value.id+'</font><br>'+
                        '<b>Nombre: </b><font color=#2196F3>&nbsp;&nbsp;<search>' + value.name + '</search></font><br>' +
                        '<b>Descripción: </b>&nbsp;&nbsp;' + value.description + '<br>' +
                        '<b>Parametros: </b><hr>' + out2 + '<hr>' +
                        '<b>Ejemplo: </b><pre><code>' + value.code + '</pre></code>' +
                        '</li>' +
                        '</ul>';
            });
            $('#constructorvar').html('<input style="margin-bottom: 20px;" type="text" placeholder="Buscar..." class="form-control" id="searchconstructor"/><div id="constructorlist">'+out+'</div>');
            onKey('searchconstructor','constructorlist');
        }

        if (typeof method !== 'undefined') {
            var out = '';
            $.each(method, function (key, value) {
                var parameter = value.parameter;
                var retur = value.return;
                var out2 = '';
                var out3 = '';

                if (parameter.length !== 0 || typeof parameter !== 'undefined') {
                    $.each(parameter, function (key, value) {

                        out2 += '<b><font color=#00BCD4>' + value.type + '</font></b>&nbsp;&nbsp;' + value.name +
                                '<p>' + value.desc + '</p>';

                    });
                } else {
                    out2 = 'Este método no tiene parametros de entrada';
                }

                if (retur.length !== 0 || typeof retur !== 'undefined') {
                    $.each(retur, function (key, value) {

                        out3 += '<b><font color=#FF9800>' + value.type + '</font></b>' +
                                '<p>' + value.desc + '</p>';

                    });
                } else {
                    out3 = 'Este método no tiene parametros de salida';
                }
                
                var encaps = '';
                
                if(typeof value.encapsulation !== 'undefined'){
                    encaps = '<b>Encapsulacion :</b>&nbsp;&nbsp;<font color=#E91E63>'+value.encapsulation+'</font><br>'
                }
                
                out += '<ul class="list-group" style="margin-bottom: 20px;">' +
                        '<li class="list-group-item">' +
                        '<b>ID :</b><font color=#B71C1C>&nbsp;&nbsp;'+value.id+'</font><br>'+
                        '<b>Nombre: </b><font color=#2196F3>&nbsp;&nbsp;<search>' + value.name + '</search></font><br>' +
                        '<b>Descripción: </b>&nbsp;&nbsp;' + value.description + '<br>' +
                        encaps+
                        '<b>Parametros: </b><hr>' + out2 + '<hr>' +
                        '<b>Retorno: </b><hr>' + out3 + '<hr>'+
                        '<b>Ejemplo: </b><pre><code>' + value.code + '</pre></code>' +
                        '</li>' +
                        '</ul>';
            });
            $('#methodvar').html('<input style="margin-bottom: 20px;" type="text" placeholder="Buscar..." class="form-control" id="searchmethod"/><div id="methodlist">'+out+'</div>');
            onKey('searchmethod','methodlist');
        }
        
    });
    
}

function onKey(name,list) {

    $('#' + name).keyup(function (evt) {
        var input, filter, ul, li, a, i;
        input = document.getElementById(name);
        filter = input.value.toUpperCase();
        ul = document.getElementById(list);     
        li = ul.getElementsByTagName("ul");
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("search")[0];
            if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                $('#nores').html("");
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    });

}

function myFunction() {
    
}