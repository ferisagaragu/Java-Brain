var names = 0;

function init() {
    $.getJSON("json/data.json", function (data) {
        if (data.length !== 0) {
            for (var key in data) {
                 
                //Static section
                for (var keys in data[key].static) {
                    $('#static-title').html('<h3>Metodos estaticos</h3>');
                    createSecction('#static',keys,data[key].static[keys].info,data[key].static[keys].code,data[key].static[keys].out,1);
                }
                
                //Method section
                for (var keys in data[key].method) {
                    $('#method-title').html('<h3>Metodos</h3>');
                    createSecction('#method',keys,data[key].method[keys].info,data[key].method[keys].code,data[key].method[keys].out,2);
                }
                
                //Attribute section
                for (var keys in data[key].method) {
                    $('#attribute-title').html('<h3>Atributos</h3>');
                    createSecction('#attribute',keys,data[key].method[keys].info,data[key].method[keys].code,data[key].method[keys].out,3);
                }

            }
        }
    });
}

function createSecction(inner,title,explication,code,out,type){
    var id1 = 'div' + names;
    var id2 = 'div' + names + 1;
    var panel = 'panel-default';
    
    switch (type) {
        case 1: panel = 'panel-info'; break;
        case 2: panel = 'panel-success'; break;
        case 3: panel = 'panel-warning'; break;
    }

    $(inner).html($(inner).html()+'<div class="panel '+panel+'">' +
                    '<div class="panel-heading">'+title+'</div>' +
                    '<div class="panel-body">' +
                        '<p>'+explication+'</p>'+
                        '<div id="'+id1+'"></div>'+
                        '<div id="'+id2+'"></div>'+
                    '</div>' +
                  '</div>');

    if (code !== null){
        insertCode('#'+id1,convertCode(code));
    }
    if (out !== null){
        insertOut('#'+id2,'<code style="color: blue;">'+out+'</code>');
    }
    names++;
}

function insertCode(inner,code){
    var id = 'code' + names;
    $(inner).html('<pre id="'+id+'" class="language-java" style="background-color: white;"><code>'+code+'</code></pre>');
    Prism.highlightElement($('#'+id)[0]);
    names++;
}

function insertOut(inner,code){
    $(inner).html('<pre style="background-color:#353535; color: white;"><code>Out:</code><br><br>'+code+'</pre>');
}

function convertCode(code) {
    var out = '';
    for (var i = 0; i < code.length; i++) {
        out = out + code[i] + "\n";
    }
    return out;
}


init();

