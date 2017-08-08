package application;

public class FilmInfo {

    private int film_id;
    private String rating;
    private double rentalRate;
    private String title;
    private String description;
    private String categoryName;

    public FilmInfo(int film_id, String rating, double rentalRate,
            String title, String description, String categoryName) {
        super();
        this.film_id = film_id;
        this.rating = rating;
        this.rentalRate = rentalRate;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
    }

    FilmInfo() {
     }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
