
import org.javabrain.util.alert.Log;
import org.javabrain.util.data.Json;

public class Run {
    
    public static void main(String[] args) {
        
        
        Log.viewer(
        new Json(
        "[{'id':'4','name':'Test','date':'08/10/2018','money':'0 MNX','money2':'nsdfsd','type':'https://www.google.com.mx/maps'},"
      + "{'id':'3','name':'Test','date':'08/10/2018','money':'0 MNX','money2':'n','type':'0.033','array':[123,21321,12321,321312]},"
      + "{'id':'5','name':'Test','date':'08/10/2018','money':'0 MNX','money2':'n','type':[{'dato':'nuevisimo!!'}]}]"));






//        Json js = new Json("https://budget.webcindario.com/getbudgets.php");
//        Log.viewer("https://budget.webcindario.com/getbudgets.php");
    }

}
