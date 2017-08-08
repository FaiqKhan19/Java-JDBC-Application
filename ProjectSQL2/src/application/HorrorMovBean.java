package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HorrorMovBean {

    private static DBConnect dbc;
    private static Connection conn;
    ResultSet rs;

    public HorrorMovBean() {
        try {
            conn = dbc.connect();
            String SQL =  "SELECT c.customer_id,CONCAT(c.first_name,' ', c.last_name) customer_name,"
            		+ "COUNT(distinct re.rental_id)  cont,"
            		+ "cat.`name` category_name  FROM customer c"
            		+" LEFT JOIN rental re ON re.customer_id=c.customer_id"
            		+" LEFT JOIN inventory inv ON inv.inventory_id=re.inventory_id"
            		+" LEFT JOIN film fi ON fi.film_id=inv.film_id"
            		+" LEFT JOIN film_category fc ON fc.film_id=fi.film_id"
            		+" right JOIN category cat ON cat.category_id=fc.category_id "
            		+ "AND cat.`name`='Horror'   " 
            		+"GROUP BY c.customer_id,customer_name,category_name"
            		+" HAVING COUNT(  re.rental_id  ) > 4";
            
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery(SQL);

           printRS(rs);
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(HorrorMovBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printRS(ResultSet rs) throws SQLException {
    System.out.println("h___________________________________________________");
        rs.beforeFirst();
        while (rs.next()) {
            int CustomerId = rs.getInt("Customer_Id");
            int noOfTimesRented = rs.getInt("cont");
            String customerName = rs.getString("customer_name");
            String categoryName = rs.getString("Category_Name");
            System.out.println("Customer Id: " + CustomerId);
            System.out.print("No Of Times Rented: " + noOfTimesRented);
            System.out.println("Customer Name: " + customerName);
            System.out.println("Category Name: " + categoryName);
        }
        System.out.println("");
    }

    public RentedHorror moveFirst() {
        RentedHorror rh = new RentedHorror();
        try {
            rs.first();
            rh = getRentedHorror(); 
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return rh;
    }

    public RentedHorror moveNext() {
        RentedHorror rh = new RentedHorror();
        try {
            if (rs.next() == false) {
                rs.previous();
            }
            rh = getRentedHorror(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return rh;
    }

    public RentedHorror movePrevious() {
        RentedHorror rh = new RentedHorror();
        try {
            if (rs.previous() == false) {
                rs.next();
            }
            rh = getRentedHorror(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return rh;
    }

    public RentedHorror moveLast() {
        RentedHorror rh = new RentedHorror();
        try {
            rs.last();
            rh = getRentedHorror(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return rh;
    }

    public RentedHorror getCurrent() {
        RentedHorror rh = new RentedHorror();
        try {
            rs.moveToCurrentRow();
            rh = getRentedHorror(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rh;
    }

    RentedHorror getRentedHorror() {
        RentedHorror rh = new RentedHorror();
        try {

            rh.setCustomer_id(rs.getInt("Customer_Id"));
            rh.setNoOfTimesRented(rs.getInt("cont"));
            rh.setCustomerName(rs.getString("customer_name"));
            rh.setCategoryName(rs.getString("Category_Name"));

        } catch (SQLException ex) {
            Logger.getLogger(HorrorMovBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rh;
    }

}
