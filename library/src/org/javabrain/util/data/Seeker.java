package org.javabrain.util.data;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Fernny
 */
public class Seeker {

    public static ArrayList<String> search(ArrayList<String> elements,String search) {
        Collection<String> collection = Collections2.filter(elements,
        Predicates.containsPattern(search));
        ArrayList<String> al = new ArrayList<>();
        
        for (String string : collection) {
            
            al.add(string);
            
        }
        
        return al;
    }

    public static ArrayList<String> search(String[] elements,String search) {
        ArrayList<String> elm = new ArrayList<>();
        
        for (int i = 0; i < elements.length; i++) {
            
            elm.add(elements[i]);
            
        }
        
        Collection<String> collection = Collections2.filter(elm,
        Predicates.containsPattern(search));
        ArrayList<String> al = new ArrayList<>();
        
        for (String string : collection) {
            
            al.add(string);
            
        }
        
        return al;
    }

    public static String[] searchToArray(ArrayList<String> elements,String search) {
        Collection<String> collection = Collections2.filter(elements,
        Predicates.containsPattern(search));
        String[] al = new String[collection.size()];
        
        int a = 0;
        for (String string : collection) {
            
            al[a] = string;
            a++;
        }
        
        return al;
    }

    public String[] searchToArray(String[] elements,String search) {
        ArrayList<String> elm = new ArrayList<>();
        
        for (int i = 0; i < elements.length; i++) {
            
            elm.add(elements[i]);
            
        }
        Collection<String> collection = Collections2.filter(elm,
        Predicates.containsPattern(search));
        String[] al = new String[collection.size()];
        
        int a = 0;
        for (String string : collection) {
            
            al[a] = string;
            a++;
        }
        
        return al;
    }
}
