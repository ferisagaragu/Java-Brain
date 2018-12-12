function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 6
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            geocodePosition(pos);
        });
    } else {
        console.log('no se pudo!');
    }
}

function geocodePosition(pos) {
    var out;
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        latLng: pos
    }, function (responses) {
        if (responses && responses.length > 0) {

            out = {
                detail : JSON.stringify(responses[0].address_components),
                lat : pos.lat,
                lng : pos.lng,
                address : responses[0].formatted_address,
                token : getParameterByName('token')
            };
            
            console.log(out);
            $.post("https://javabrain.webcindario.com/location/service/addlocation.php", out).done(function () {
                $('#resp').html('<label>Get location susseful you go to the aplication</label>');
                alert("Get location susseful");
                window.open('', '_self').close();
            });
        }        
    });
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


    





