package org.javabrain.util.resource;

/**
 *
 * @author Fernando García
 * @version 0.0.1
 */
public class Layout {

    private String layout;

    public Layout(String path) {
        this.layout = Archive.read(path);
    }
    
    public void put(String key,String value) {
        this.layout = this.layout.replace("${"+key+"}",value);
    }

    public String getLayout() {
        return layout;
    }

    @Override
    public String toString() {
        return layout;
    }

}
