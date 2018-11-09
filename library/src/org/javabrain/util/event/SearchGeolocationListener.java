package org.javabrain.util.event;

import java.util.EventListener;
import java.util.List;
import org.javabrain.util.web.service.Location;

/**
 *
 * @author Fernando García
 * @version 0.0.1
 */
public interface SearchGeolocationListener extends EventListener{
  
    public void success(List<Location> locations);
    public void error();
    
}
