package org.javabrain.util.event;

import java.util.EventListener;
import org.javabrain.util.web.service.Ajax;

/**
 *
 * @author Fernando García
 */
public interface PetitionListener extends EventListener{
  
    public void success(Ajax ajax);
    public void error(Ajax ajax);
    public void always(Ajax ajax);
    
}
