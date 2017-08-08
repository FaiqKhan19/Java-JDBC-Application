package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn;
    private DriverManager driver;
    private SQLException sqlExcept;
    
//    private static String url = "jdbc:mysql://localhost:8889/products";
//    private static String user = "root";
//    private static String pass = "faiq";
    
    private static String url = "jdbc:mysql://localhost:3306/sakila";
    private static String user = "root";
    private static String pass = "1111";

    public static Connection connect() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.out.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("Error: " + iae.getMessage());
        }
        conn = DriverManager.getConnection(url, user, pass);
        return conn;

    }

}
