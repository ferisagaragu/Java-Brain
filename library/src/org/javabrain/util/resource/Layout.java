package org.javabrain.util.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fernando García
 * @version 0.0.2
 */
public class Layout {

    private String layout;

    public Layout() {}

    public Layout(String path) {
        this.layout = Archive.read(path);
    }
    
    public void put(String key,String value) {
        this.layout = this.layout.replace("${"+key+"}",value);
    }

    public String getLayout() {
        return layout;
    }

    public List<String> getKeys() {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("\\$\\{(.*?)}").matcher(layout);
        while(m.find()) {
            list.add(m.group(1));
        }
        
        return list;
    }
    
    public void setString(String layout) {
        this.layout = layout;
    }
    
    @Override
    public String toString() {
        return layout;
    }

}
