package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilmBean {

    private static DBConnect dbc;
    private static Connection conn;
    ResultSet rs;

    public FilmBean() {
        try {
            conn = dbc.connect();
            String SQL = "SELECT * FROM film f LEFT JOIN film_category fc ON fc.film_id = f.film_id LEFT JOIN category c ON c.category_id = fc.category_id";
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery(SQL);

            System.out.println("Result List");
        printRS(rs);
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(FilmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printRS(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        while (rs.next()) {
            int film_id = rs.getInt("Film_Id");
            String rating = rs.getString("rating");
            Double rentalRating = rs.getDouble("Rental_Rate");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String categoryName = rs.getString("Name");
            System.out.print("Rating: " + rating);
            System.out.println("RentalRating: " + rentalRating);
            System.out.println("film_id: " + film_id);
            System.out.println("title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Category Name: " + categoryName);
        }
        System.out.println("");

    }

    public FilmInfo moveFirst() {
        FilmInfo fi = null;
        try {
            rs.first();
            fi = setFilmInfo();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return fi;
    }

    public FilmInfo moveNext() {
        FilmInfo fi = null;
        try {
            if (rs.next() == false) {
                rs.previous();
            }
            fi = setFilmInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return fi;
    }

    public FilmInfo movePrevious() {
        FilmInfo fi = null;
        try {
            if (rs.previous() == false) {
                rs.next();
            }
            fi = setFilmInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return fi;
    }

    public FilmInfo moveLast() {
        FilmInfo fi = null;
        try {
            rs.last();
            fi = setFilmInfo(); 

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return fi;
    }

    public FilmInfo getCurrent() {
        FilmInfo fi = null;
        try {
            rs.moveToCurrentRow();
            fi = setFilmInfo(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fi;
    }

    FilmInfo setFilmInfo() {
        FilmInfo fi = new FilmInfo();
        try {
            fi.setFilm_id(rs.getInt("Film_id"));
            fi.setRating(rs.getString("Rating"));
            fi.setRentalRate(rs.getDouble("Rental_Rate"));
            fi.setTitle(rs.getString("Title"));
            fi.setDescription(rs.getString("Description"));
            fi.setCategoryName(rs.getString("Name"));
        } catch (SQLException ex) {
            Logger.getLogger(FilmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fi;
    }

}
