package application;

import static application.HorrorMovBean.printRS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OverdueBean {

    private static DBConnect dbc;
    private static Connection conn;
    ResultSet rs;

    public OverdueBean() {
        try {
            conn = dbc.connect();
            String SQL = "SELECT ad.phone Phone_No, CONCAT( c.first_name, ' ', c.last_name ) customer_name, fi.title Film_Title FROM customer c JOIN address ad ON ad.address_id = c.address_id LEFT JOIN rental re ON re.customer_id = c.customer_id LEFT JOIN inventory inv ON inv.inventory_id = re.inventory_id LEFT JOIN film fi ON fi.film_id = inv.film_id WHERE ISNULL(re.return_date)";
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery(SQL);

            printRS(rs);
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(OverdueBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printRS(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            String customerName = rs.getString("Customer_name");
            String phoneNo = rs.getString("Phone_No");
            String categoryName = rs.getString("Film_Title");
            System.out.println("Customer Id: " + phoneNo);
            System.out.println("Customer Name: " + customerName);
            System.out.println("Category Name: " + categoryName);
        }
        System.out.println("");
    }

    public OverdueDvds moveFirst() {
        OverdueDvds od = null;
        try {
            rs.first();
            od = getOverDueDvd();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return od;
    }

    public OverdueDvds moveNext() {
        OverdueDvds od = null;
        try {
            if (rs.next() == false) {
                rs.previous();
            }
            od = getOverDueDvd();

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return od;
    }

    public OverdueDvds movePrevious() {
        OverdueDvds od = null;
        try {
            if (rs.previous() == false) {
                rs.next();
            }
            od = getOverDueDvd();

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return od;
    }

    public OverdueDvds moveLast() {
        OverdueDvds od = null;
        try {
            rs.last();
            od = getOverDueDvd();

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return od;
    }

    public OverdueDvds getCurrent() {
        OverdueDvds od = null;
        try {
            rs.moveToCurrentRow();
            od = getOverDueDvd();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return od;
    }

    OverdueDvds getOverDueDvd() {
        OverdueDvds od = new OverdueDvds();
        try {
            od.setCustomerName(rs.getString("Customer_name"));
            od.setPhoneNo(rs.getString("Phone_No"));
            od.setFilmTitle(rs.getString("Film_Title"));
        } catch (SQLException ex) {
            Logger.getLogger(OverdueBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return od;
    }

}
