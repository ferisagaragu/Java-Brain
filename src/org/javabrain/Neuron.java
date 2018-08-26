package org.javabrain;

import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.sql.Connection;
import java.sql.DriverManager;

public class Neuron {

    public static Object param(Object key){
        Json json = new Json("conf.{neuron.json}");
        return json.getJSON("param").getObject(key.toString());
    }

    public static Connection postgresConnection() {
        try {
            Json json = new Json("conf.{neuron.json}");
            Json json1 = json.getJSON("connection").getJSON("postgres");
            String user = json1.getString("user");
            String password = json1.getString("password");
            String host = json1.getString("host");
            String database = json1.getString("database");
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {}
            Connection connection = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+database,user, password);
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
    
    public static Connection connection(String dbName) {
        try {
            Json json = new Json("conf.{neuron.json}");
            Json json1 = json.getJSON("connection").getJSON(dbName);
            Console.viewer(json1);
            String user = json1.getString("user");
            String password = json1.getString("password");
            String host = json1.getString("host");
            String database = json1.getString("database");
            String classname = json1.getString("classname");
            
            try {
                Class.forName(classname);
            } catch (ClassNotFoundException ex) {}
            Connection connection = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+database,user, password);
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
}
