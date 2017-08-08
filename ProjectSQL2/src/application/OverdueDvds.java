package application;

public class OverdueDvds {

    private String customerName;
    private String phoneNo;
    private String filmTitle;

    public OverdueDvds(String customerName, String phoneNo, String filmTitle) {
        super();
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.filmTitle = filmTitle;

    }

    OverdueDvds() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

}
