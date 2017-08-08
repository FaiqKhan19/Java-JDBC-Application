package application;

public class RentedHorror {

    private int customer_id;
    private int noOfTimesRented;
    private String customerName;
    private String categoryName;

    public RentedHorror(int customer_id, int noOfTimesRented,
            String customerNmae, String categoryName) {
        super();
        this.customer_id = customer_id;
        this.noOfTimesRented = noOfTimesRented;
        this.customerName = customerName;
        this.categoryName = categoryName;
    }

    RentedHorror() {
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getNoOfTimesRented() {
        return noOfTimesRented;
    }

    public void setNoOfTimesRented(int noOfTimesRented) {
        this.noOfTimesRented = noOfTimesRented;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
