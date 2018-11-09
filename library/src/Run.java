
import java.util.List;
import org.javabrain.util.event.GeolocationListener;
import org.javabrain.util.event.SearchGeolocationListener;
import org.javabrain.util.web.service.Geolocation;
import org.javabrain.util.web.service.Location;



public class Run {
    
    public static void main(String[] args) {
        Geolocation g = new Geolocation();
        
        
        g.setOnRequestLocation(new GeolocationListener() {
            @Override
            public void success(Location location) {
                System.out.println(location);
            }

            @Override
            public void error() {
                
            }
        });
        g.requestGeoLocation();
        
        g.setOnSearchLocation(new SearchGeolocationListener() {
            @Override
            public void success(List<Location> location) {
                location.forEach( o -> {
                    System.out.println(o);
                });
            }

            @Override
            public void error() {
                
            }
        });
        g.searchGeoLocation("Isla menorca 2139");
    }

}



