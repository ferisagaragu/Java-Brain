(function ($) {
    "use strict"; // Start of use strict
    
    renderInfo(getParameterByName('class'),getParameterByName('categorie'));
    
    SyntaxHighlighter.all();
})(jQuery); // End of use strict

function renderInfo(jsonName,categorie){
    $('#className').html('<a href="/doc/index.html"><h2 style="color:white;">'+jsonName+'</h2></a>');
    
    $.getJSON("json/" + categorie + "/" + jsonName + ".json",function (data){
        var navvar = '';
        var section = '';
        
        var author = data.author;
        var variable;
        var constructor;
        var method;
        
        
        $.each(data,function (key,value){
            navvar += '<li class="nav-item">'+
                        '<a class="nav-link js-scroll-trigger" href="#'+key+'var">'+key+'</a>'+
                       '</li>';
            section += '<section class="resume-section p-3 p-lg-5 d-flex flex-column" id="'+key+'var"></section>';
        });
        
        $('.navbar-nav').html(navvar);
        $('#views').html(section);
        
        
        if(author.length !== 0 || typeof author !== 'undefined'){
            var out = '';
            $.each(author,function (key,value){
                out +=  '<div class="list-group">'+
                            '<a href="'+value.contactname+'" class="list-group-item">'+
                                '<h4 class="list-group-item-heading">'+value.name+'</h4>'+
                                '<p class="list-group-item-text">'+value.contactname+'</p>'+
                            '</a>'+
                        '</div><hr>';
            });
            
            
            $('#authorvar').html(out);
            
            $('#variablevar').html('<ul class="list-group">'+
                                        '<li class="list-group-item">'+
                                            '<b>Nombre:</b> JSON_MODE<br>'+
                                            '<b>Descripción:</b> Variable para seleccionar el modo del Json <br>'+ 
                                            '<b>Codigo:</b> JSON_MODE<br>'+
                                            '<b>Valor:</b> 1<br>'+
                                            '<hr>'+
                                            '<b>Ejemplo:</b> JSON_MODE<br>'+
                                        '</li>'+
                                    '</ul>');
        }
        
        
        
        console.log(author);
        
    });
    
    
    
}