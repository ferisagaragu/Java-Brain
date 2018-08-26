package org.javabrain.util.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.javabrain.Neuron;

/**
 *
 * @author Fernando García
 */
public class Sql {
    
    private static Connection connection;
    
    public static String MYSQL = "1";
    public static String POSTGRESQL = "2";
    public static String ORACLE = "3";
    
    public static ResultSet execute(String sql, String db) {

        switch (db) {
            case "1":
                try {
                    connection = Neuron.postgresConnection();
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    return rs;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            break;
            
            case "2":
                try {
                    connection = Neuron.postgresConnection();
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    return rs;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            break;
            
            case "3":
                try {
                    connection = Neuron.postgresConnection();
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    return rs;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            break;
        }
        
        try {
            connection = Neuron.connection(db);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static void close(){
        try {
           connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
}