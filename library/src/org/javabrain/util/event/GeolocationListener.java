package org.javabrain.util.event;

import java.util.EventListener;
import org.javabrain.util.web.service.Location;

/**
 *
 * @author Fernando García
 * @version 0.0.1
 */
public interface GeolocationListener extends EventListener{
  
    public void success(Location location);
    public void error();
    
}
