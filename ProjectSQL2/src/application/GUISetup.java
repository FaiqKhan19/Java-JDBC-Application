package application;

//created by Faiq Khan.
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUISetup extends Application {

    ResultSet rs;
    private static DBConnect dbc;
    private static Connection conn;

    private static int lalaID;
    private Label msqLabel = new Label("---");

   CustomerInfoBean cBean = new CustomerInfoBean();
    FilmBean fBean = new FilmBean();
    HorrorMovBean hmb = new HorrorMovBean();
    OverdueBean odb = new OverdueBean();

    private String selectedTab;
    BorderPane borderPane = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tab1 = new Tab();
        tab1.setText("Customer Info");
        tab1.setContent(customInf());

        Tab tab2 = new Tab();
        tab2.setText("Film Info");
        tab2.setContent(filmInf());

        Tab tab3 = new Tab();
        tab3.setText("Rented Horror");
        tab3.setContent(horrorMovies());

        Tab tab4 = new Tab();
        tab4.setText("OverDue DVDs");
        tab4.setContent(overDvds());

        tabPane.getTabs().addAll(
                tab1,
                tab2,
                tab3,
                tab4);

        selectedTab = tabPane.getSelectionModel().
                getSelectedItem().getText();

        //init(stage);
        //rs = resultLook(selectedTab);
        borderPane.setTop(tabPane);

        Scene scene = new Scene(borderPane, 600, 420);
        stage.setScene(scene);
        stage.show();

    }

    public GridPane customInf() {
        GridPane root = new GridPane();
        root.setHgap(9);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        //**All the labels here**//
        Label firstN = new Label("First_Name");
        Label lastN = new Label("Last_Name");
        Label addressLabel = new Label("Address");
        Label cityLabel = new Label("City");
        Label countryLabel = new Label("Country");
        Label CustmLabel = new Label("Customer Id");

        //**All the text fields here**//
        final TextField firstNField = new TextField();
        final TextField lastNField = new TextField();
        final TextField addressField = new TextField();
        final TextField cityField = new TextField();
        final TextField countryField = new TextField();
        final TextField CustmField = new TextField();

        //**All the buttons here**//
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");

        //**Add them here.**//
        root.add(firstN, 0, 1);
        root.add(lastN, 0, 2);
        root.add(addressLabel, 0, 3);
        root.add(cityLabel, 0, 4);
        root.add(countryLabel, 0, 5);
        root.add(CustmLabel, 2, 1);

        root.add(firstNField, 1, 1, 1, 1);
        root.add(lastNField, 1, 2, 1, 1);
        root.add(addressField, 1, 3, 1, 1);
        root.add(cityField, 1, 4, 1, 1);
        root.add(countryField, 1, 5, 1, 1);
        root.add(CustmField, 3, 1, 1, 1);

        root.add(firstButton, 0, 8);
        root.add(nextButton, 1, 8);
        root.add(previousButton, 2, 8);
        root.add(lastButton, 3, 8);

        firstButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setCustomer(cBean.moveFirst(), firstNField,
                        lastNField, addressField, cityField, countryField, CustmField);
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setCustomer(cBean.moveNext(), firstNField,
                        lastNField, addressField, cityField, countryField, CustmField);
            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setCustomer(cBean.movePrevious(), firstNField,
                        lastNField, addressField, cityField, countryField, CustmField);
            }
        });
        lastButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                setCustomer(cBean.moveLast(), firstNField,
                        lastNField, addressField, cityField, countryField, CustmField);
            }
        });

        return root;
    }

    void setCustomer(CustomerInfo ci, TextField firstNField,
            TextField lastNField, TextField addressField, TextField cityField, TextField countryField, TextField CustmField) {
        firstNField.setText(ci.getFirst_name());
        lastNField.setText(ci.getLast_name());
        addressField.setText(ci.getAddress());
        cityField.setText(ci.getCity());
        countryField.setText(ci.getCountry());
        CustmField.setText(ci.getCustomer_id() + "");
    }

    public GridPane filmInf() {
        GridPane root = new GridPane();
        root.setHgap(9);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        //**All the labels here**//
        Label filmIdLab = new Label("Film_Id");
        Label ratingLab = new Label("Rating");
        Label rentalRLab = new Label("Rental");
        Label titleLab = new Label("Title");
        Label descriptionLab = new Label("Description");
        Label categNameLab = new Label("Category Name");

        //**All the fields here**//
        final TextField filmIdField = new TextField();
        final TextField ratingField = new TextField();
        final TextField rentedField = new TextField();
        final TextField titleField = new TextField();
        final TextField descrField = new TextField();
        final TextField catgField = new TextField();

        //**All the buttons here**//
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");

        //**Add them here.**//
        root.add(filmIdLab, 0, 1);
        root.add(ratingLab, 0, 2);
        root.add(rentalRLab, 0, 3);
        root.add(titleLab, 0, 4);
        root.add(descriptionLab, 0, 5);
        root.add(categNameLab, 2, 1);

        root.add(filmIdField, 1, 1, 1, 1);
        root.add(ratingField, 1, 2, 1, 1);
        root.add(rentedField, 1, 3, 1, 1);
        root.add(titleField, 1, 4, 1, 1);
        root.add(descrField, 1, 5, 1, 1);
        root.add(catgField, 3, 1, 1, 1);

        root.add(firstButton, 0, 8);
        root.add(nextButton, 1, 8);
        root.add(previousButton, 2, 8);
        root.add(lastButton, 3, 8);

        firstButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getFilmInfo(fBean.moveFirst(), filmIdField, ratingField,
                        rentedField, titleField, descrField, catgField);
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getFilmInfo(fBean.moveNext(), filmIdField, ratingField,
                        rentedField, titleField, descrField, catgField);

            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getFilmInfo(fBean.movePrevious(), filmIdField, ratingField,
                        rentedField, titleField, descrField, catgField);

            }
        });
        lastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getFilmInfo(fBean.moveLast(), filmIdField, ratingField,
                        rentedField, titleField, descrField, catgField);

            }
        });

        return root;
    }

    void getFilmInfo(FilmInfo fi, TextField filmIdField, TextField ratingField,
            TextField rentedField, TextField titleField, TextField descrField, TextField catgField) {
        filmIdField.setText(fi.getFilm_id() + "");
        ratingField.setText(fi.getRating() + "");
        rentedField.setText(fi.getRentalRate() + "");
        titleField.setText(fi.getTitle());
        descrField.setText(fi.getDescription());
        catgField.setText(fi.getCategoryName());
    }

    public GridPane horrorMovies() {
        GridPane root = new GridPane();
        root.setHgap(9);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        //**All the labels here**//
        Label custIdLab = new Label("Customer_Id");
        Label noOfRLab = new Label("No Of Times Rented");
        Label custmLab = new Label("Customer Name");
        Label categNLab = new Label("Category Name");

        //**All the text fields here.**//
        final TextField cstIdField = new TextField();
        final TextField noOfRentedField = new TextField();
        final TextField custmNField = new TextField();
        final TextField catgNField = new TextField();

        //**All the buttons here**//
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");

        //**Add them here.**//
        root.add(custIdLab, 0, 1);
        root.add(noOfRLab, 0, 2);
        root.add(custmLab, 0, 3);
        root.add(categNLab, 0, 4);

        root.add(cstIdField, 1, 1, 1, 1);
        root.add(noOfRentedField, 1, 2, 1, 1);
        root.add(custmNField, 1, 3, 1, 1);
        root.add(catgNField, 1, 4, 1, 1);

        root.add(firstButton, 0, 6);
        root.add(nextButton, 1, 6);
        root.add(previousButton, 2, 6);
        root.add(lastButton, 3, 6);

        firstButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setHorrotInfo(hmb.moveFirst(), cstIdField, noOfRentedField, custmNField, catgNField);

            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setHorrotInfo(hmb.moveNext(), cstIdField, noOfRentedField, custmNField, catgNField);

            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setHorrotInfo(hmb.movePrevious(), cstIdField, noOfRentedField, custmNField, catgNField);
            }
        });
        lastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setHorrotInfo(hmb.moveLast(), cstIdField, noOfRentedField, custmNField, catgNField);
            }
        });

        return root;
    }

    void setHorrotInfo(RentedHorror rh, TextField cstIdField, TextField noOfRentedField, TextField custmNField, TextField catgNField) {

        cstIdField.setText(rh.getCustomer_id() + "");
        noOfRentedField.setText(rh.getNoOfTimesRented() + "");
        custmNField.setText(rh.getCustomerName());
        catgNField.setText(rh.getCategoryName());

    }

    public GridPane overDvds() {
        GridPane root = new GridPane();
        root.setHgap(9);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        //**All the labels here**//
        Label custmLab = new Label("Customer Name");
        Label phoneNoLab = new Label("Phone Number");
        Label filmTitleLab = new Label("Film Title");

        //**All the fields here**//
        final TextField cstNField = new TextField();
        final TextField phoneNoField = new TextField();
        final TextField fTitleField = new TextField();

        //**All the buttons here**//
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");

        //**Add them here.**//
        root.add(custmLab, 0, 1);
        root.add(phoneNoLab, 0, 2);
        root.add(filmTitleLab, 0, 3);

        root.add(cstNField, 1, 1, 1, 1);
        root.add(phoneNoField, 1, 2, 1, 1);
        root.add(fTitleField, 1, 3, 1, 1);

        root.add(firstButton, 0, 5);
        root.add(nextButton, 1, 5);
        root.add(previousButton, 2, 5);
        root.add(lastButton, 3, 5);

        firstButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setOverDueInfo(odb.moveFirst(), cstNField, phoneNoField, fTitleField);
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setOverDueInfo(odb.moveNext(), cstNField, phoneNoField, fTitleField);
            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setOverDueInfo(odb.movePrevious(), cstNField, phoneNoField, fTitleField);
            }
        });
        lastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setOverDueInfo(odb.moveLast(), cstNField, phoneNoField, fTitleField);
            }
        });

        return root;
    }

    void setOverDueInfo(OverdueDvds od, TextField cstNField, TextField phoneNoField, TextField fTitleField) {

        cstNField.setText(od.getCustomerName());
        phoneNoField.setText(od.getPhoneNo() + "");
        fTitleField.setText(od.getFilmTitle());

    }

    public static void main(String[] args) {
        launch(args);

    }

}
