import org.javabrain.util.web.service.Geolocation;
import org.javabrain.util.web.service.Location;

public class Run{

    public static void main(String[] args) throws Exception {

        Location location = Geolocation.requestLocation();
        System.out.println(location.getFormatAddress());
    }

}
