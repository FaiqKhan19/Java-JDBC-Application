package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerInfoBean {

    private static DBConnect dbc;
    private static Connection conn;
    ResultSet rs;

    public CustomerInfoBean() {

        try {
            conn = dbc.connect();
            String SQL = "SELECT * FROM customer LEFT JOIN address ON address.address_id = customer.address_id LEFT JOIN city ON city.city_id = address.city_id LEFT JOIN country on country.country_id= city.country_id";
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery(SQL);

            System.out.println("Result List");
            printRS(rs);
            rs.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printRS(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            int CustomerId = rs.getInt("customer_id");
            String first_name = rs.getString("First_Name");
            String last_name = rs.getString("Last_Name");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String country = rs.getString("country");
            System.out.print("First_Name: " + first_name);
            System.out.println("Last_Name: " + last_name);
            System.out.println("Address: " + address);
            System.out.println("City: " + city);
            System.out.println("Country: " + country);
            System.out.println("Customer Id: " + CustomerId);
        }
        System.out.println("");
    }

    public CustomerInfo moveFirst() {
        CustomerInfo c = new CustomerInfo();
        try {
            rs.first();
             c=getCustomerInfo(); 
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return c;
    }

    public CustomerInfo moveNext() {
        CustomerInfo c = new CustomerInfo();
        try {
            if (rs.next() == false) {
                rs.previous();
            }
             c=getCustomerInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return c;
    }

    public CustomerInfo movePrevious() {
        CustomerInfo c = new CustomerInfo();
        try {
            if (rs.previous() == false) {
                rs.next();
            }
             c=getCustomerInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return c;
    }

    public CustomerInfo moveLast() {
        CustomerInfo c = new CustomerInfo();
        try {
            rs.last();
             c=getCustomerInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return c;
    }

    public CustomerInfo getCurrent() {
        CustomerInfo c = new CustomerInfo();
        try {
            rs.moveToCurrentRow();
            c=getCustomerInfo(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    CustomerInfo getCustomerInfo() {
        CustomerInfo c = new CustomerInfo();
        try { 
            c.setFirst_name(rs.getString("First_name"));
            c.setLast_name(rs.getString("Last_name"));
            c.setAddress(rs.getString("Address"));
            c.setCity(rs.getString("City"));
            c.setCountry(rs.getString("Country"));
            c.setCustomer_id(rs.getInt("Customer_id"));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}
