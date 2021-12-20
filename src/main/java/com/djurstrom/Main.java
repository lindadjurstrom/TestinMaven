package com.djurstrom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.persistence.*;


public class Main extends Application {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    final ObservableList<CustomerCopy> data = FXCollections.observableArrayList();

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textFieldStoreID = new TextField();
        TextField textFieldFirstName = new TextField();
        TextField textFieldLastName = new TextField();
        TextField textFieldEmail = new TextField();
        TextField textFieldAddress = new TextField();
        TextField textFieldAddress2 = new TextField();
        TextField textFieldPostalCode = new TextField();
        TextField textFieldDistrict = new TextField();
        TextField textFieldCity = new TextField();
        TextField textFieldCountry = new TextField();
        TextField textFieldPhone = new TextField();
        TextField textFieldSearchField = new TextField();

        TableView tableViewResultArea = new TableView();
        TableColumn storeIDCol = new TableColumn("StoreID");
        TableColumn customerIDCol = new TableColumn("CustomerID");
        TableColumn firstNameCol = new TableColumn("First name");
        TableColumn lastNameCol = new TableColumn("Last name");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn addressCol = new TableColumn("Address");
        TableColumn address2Col = new TableColumn("Address2");
        TableColumn postalCodeCol = new TableColumn("Postal code");
        TableColumn districtCol = new TableColumn("District");
        TableColumn cityCol = new TableColumn("City");
        TableColumn countryCol = new TableColumn("Country");
        TableColumn phoneCol = new TableColumn("Phone");

        Label labelAddCustomer = new Label("Skapa ny kund");
        Label labelStoreID = new Label("StoreID");
        Label labelFirstName = new Label("First name");
        Label labelLastName = new Label("Last name");
        Label labelEmail = new Label("Email");
        Label labelAddress = new Label("Address");
        Label labelAddress2 = new Label("Address 2");
        Label labelPostalCode = new Label("Postal code");
        Label labelDistrict = new Label("Disctrict");
        Label labelCity = new Label("City");
        Label labelCountry = new Label("Country");
        Label labelPhone = new Label("Phone");
        Label infoBannerCanNotDelete = new Label("Denna kund går inte att radera än, se till att kunden \n återlämnat " +
                "alla filmer och betalat sina avgifter.");

        Button bCreate = new Button("Skapa");
        Button bSearch = new Button("Sök");
        Button bUpdate = new Button("Update");
        Button bDelete = new Button("Delete");

        HBox hBoxStoreID = new HBox();
        HBox hBoxFirstName = new HBox();
        HBox hBoxLastName = new HBox();
        HBox hBoxEmail = new HBox();
        HBox hBoxAddress = new HBox();
        HBox hBoxAddress2 = new HBox();
        HBox hBoxPostalCode = new HBox();
        HBox hBoxDistrict = new HBox();
        HBox hBoxCity = new HBox();
        HBox hBoxCountry = new HBox();
        HBox hBoxPhone = new HBox();
        HBox hBoxButtonsCreate = new HBox();
        HBox hBoxAddCustomer = new HBox();
        HBox hBoxChange = new HBox();
        HBox hBoxSearchField = new HBox();
        HBox hBoxResultsField = new HBox();
        HBox hBoxInfoBannerCanNotDelete = new HBox();

        VBox vBoxCreateCustomer = new VBox();
        VBox vBoxSearch = new VBox();

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 900, 900);

        textFieldStoreID.setText("1");
        textFieldStoreID.setFont(Font.font(null, FontPosture.ITALIC, 15));
        textFieldStoreID.setEditable(false);
        textFieldSearchField.setPromptText("Här kan du söka efter kunder");
        textFieldSearchField.setMinWidth(500);

        tableViewResultArea.setMinHeight(700);
        tableViewResultArea.setMinWidth(500);
        tableViewResultArea.setEditable(false);

        storeIDCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,Integer>("storeID")
        );
        customerIDCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,Integer>("customerID")
        );
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("firstName")

        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("lastName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("email")
        );

        addressCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,Integer>("address")
        );
        address2Col.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("address2")

        );
        postalCodeCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("postalCode")
        );
        districtCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("district")
        );
        cityCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("city")
        );
        countryCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("country")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<CustomerCopy,String>("phone")
        );

        labelAddCustomer.setFont(Font.font(null, FontWeight.BOLD, 18));
        labelStoreID.setMinWidth(90);
        labelFirstName.setMinWidth(90);
        labelLastName.setMinWidth(90);
        labelEmail.setMinWidth(90);
        labelAddress.setMinWidth(90);
        labelAddress2.setMinWidth(90);
        labelPostalCode.setMinWidth(90);
        labelDistrict.setMinWidth(90);
        labelCity.setMinWidth(90);
        labelCountry.setMinWidth(90);
        labelPhone.setMinWidth(90);

        bUpdate.setVisible(false);

        hBoxAddCustomer.getChildren().addAll(labelAddCustomer);
        hBoxAddCustomer.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddCustomer.setPadding(new Insets(0, 0, 5, -120));
        hBoxStoreID.getChildren().addAll(labelStoreID, textFieldStoreID);
        hBoxStoreID.setAlignment(Pos.BASELINE_CENTER);
        hBoxFirstName.getChildren().addAll(labelFirstName, textFieldFirstName);
        hBoxFirstName.setAlignment(Pos.BASELINE_CENTER);
        hBoxLastName.getChildren().addAll(labelLastName, textFieldLastName);
        hBoxLastName.setAlignment(Pos.BASELINE_CENTER);
        hBoxEmail.getChildren().addAll(labelEmail, textFieldEmail);
        hBoxEmail.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddress.getChildren().addAll(labelAddress, textFieldAddress);
        hBoxAddress.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddress2.getChildren().addAll(labelAddress2, textFieldAddress2);
        hBoxAddress2.setAlignment(Pos.BASELINE_CENTER);
        hBoxPostalCode.getChildren().addAll(labelPostalCode, textFieldPostalCode);
        hBoxPostalCode.setAlignment(Pos.BASELINE_CENTER);
        hBoxDistrict.getChildren().addAll(labelDistrict, textFieldDistrict);
        hBoxDistrict.setAlignment(Pos.BASELINE_CENTER);
        hBoxCity.getChildren().addAll(labelCity, textFieldCity);
        hBoxCity.setAlignment(Pos.BASELINE_CENTER);
        hBoxCountry.getChildren().addAll(labelCountry, textFieldCountry);
        hBoxCountry.setAlignment(Pos.BASELINE_CENTER);
        hBoxPhone.getChildren().addAll(labelPhone, textFieldPhone);
        hBoxPhone.setAlignment(Pos.BASELINE_CENTER);
        hBoxButtonsCreate.getChildren().addAll(bUpdate, bCreate);
        hBoxButtonsCreate.setAlignment(Pos.BASELINE_CENTER);
        hBoxButtonsCreate.setPadding(new Insets(5, 0, 0, 110));
        hBoxChange.getChildren().addAll(bDelete);
        hBoxChange.setAlignment(Pos.BASELINE_CENTER);
        hBoxChange.setPadding(new Insets(5, 0, 0, 365));
        hBoxSearchField.getChildren().addAll(textFieldSearchField);
        hBoxSearchField.setAlignment(Pos.BASELINE_CENTER);
        hBoxSearchField.setPadding(new Insets(0, 0, 2, 0));
        hBoxResultsField.getChildren().addAll(tableViewResultArea);
        hBoxResultsField.setAlignment(Pos.BASELINE_CENTER);
        hBoxResultsField.setMaxWidth(500);
        hBoxResultsField.setMinHeight(700);
        HBox hBoxSearchSettings = new HBox(bSearch);
        hBoxSearchSettings.setAlignment(Pos.BASELINE_LEFT);
        hBoxSearchSettings.setPadding(new Insets(0, 0, 5, 0));
        hBoxInfoBannerCanNotDelete.getChildren().addAll(infoBannerCanNotDelete);
        hBoxInfoBannerCanNotDelete.setVisible(false);
        hBoxInfoBannerCanNotDelete.setPadding(new Insets(5, 0, 5, 0));

        tableViewResultArea.getColumns().addAll(storeIDCol, customerIDCol, firstNameCol, lastNameCol, emailCol, addressCol, address2Col,
                postalCodeCol, districtCol, cityCol, countryCol, phoneCol);

        vBoxCreateCustomer.getChildren().addAll(hBoxAddCustomer, hBoxStoreID, hBoxFirstName, hBoxLastName, hBoxEmail,
                hBoxAddress, hBoxAddress2, hBoxPostalCode, hBoxDistrict, hBoxCity, hBoxCountry, hBoxPhone, hBoxButtonsCreate);
        vBoxCreateCustomer.setPadding(new Insets(5, 30, 0, 50));
        vBoxSearch.setPadding(new Insets(5, 50, 0, 30));
        vBoxSearch.getChildren().addAll(hBoxSearchField, hBoxSearchSettings, hBoxResultsField, hBoxChange, hBoxInfoBannerCanNotDelete);

        borderPane.setCenter(vBoxCreateCustomer);
        borderPane.setRight(vBoxSearch);


        bCreate.setOnAction(event -> {
            short countryID = getCountryID(textFieldCountry);
            if(countryID < 0) {
                createCountry(textFieldCountry);
                countryID = getCountryID(textFieldCountry);
            }
            short cityID = getCityID(textFieldCity);
            if(cityID < 0) {
                createCity(textFieldCity, countryID);
                cityID = getCityID(textFieldCity);
            }
            addAddress(textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldPhone, cityID);
            short addressID = getAddressID();
            addCustomer(textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail, addressID);
            textFieldAddress.clear();
            textFieldAddress2.clear();
            textFieldPostalCode.clear();
            textFieldDistrict.clear();
            textFieldCity.clear();
            textFieldCountry.clear();
            textFieldPhone.clear();
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldEmail.clear();
        });


bDelete.setOnAction(event -> {
   int customerCopy = tableViewResultArea.getSelectionModel().getSelectedIndex();
    try {
        int customerID = data.get(customerCopy).getCustomerID();
        deleteCustomer(customerID, hBoxInfoBannerCanNotDelete);
    } catch (ArrayIndexOutOfBoundsException e) {
    }
    addSearch(textFieldSearchField);
    tableViewResultArea.setItems(data);


});

        bSearch.setOnAction(event -> {
            data.clear();
                addSearch(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void deleteCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete) {
       EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("DELETE FROM customer WHERE customer_id = " + customerID);
            query.executeUpdate();
            transaction.commit();

        } catch (PersistenceException f) {
            hBoxInfoBannerCanNotDelete.setVisible(true);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private void addSearch(TextField textFieldSearchField) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> customerIDList = new ArrayList<>(1);
        List<String> firstNameList = new ArrayList<>(1);
        List<String> lastNameList = new ArrayList<>(1);
        List<String> emailList = new ArrayList<>(1);
        List<String> addressList = new ArrayList<>(1);
        List<String> address2List = new ArrayList<>(1);
        List<String> districtList = new ArrayList<>(1);
        List<String> postalCodeList = new ArrayList<>(1);
        List<String> phoneList = new ArrayList<>(1);
        List<String> cityList = new ArrayList<>(1);
        List<String> countryList = new ArrayList<>(1);
        List<Byte> storeIDList = new ArrayList<>(1);

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query0 = entityManager.createNativeQuery("SELECT customer_id FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            customerIDList = query0.getResultList();
            Query query1 = entityManager.createNativeQuery("SELECT first_name FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            firstNameList = query1.getResultList();
            Query query2 = entityManager.createNativeQuery("SELECT last_name FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            lastNameList = query2.getResultList();
            Query query3 = entityManager.createNativeQuery("SELECT email FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            emailList = query3.getResultList();
            Query query4 = entityManager.createNativeQuery("SELECT address FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            addressList = query4.getResultList();
            Query query5 = entityManager.createNativeQuery("SELECT address2 FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            address2List = query5.getResultList();
            Query query6 = entityManager.createNativeQuery("SELECT postal_code FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            postalCodeList = query6.getResultList();
            Query query7 = entityManager.createNativeQuery("SELECT district FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            districtList = query7.getResultList();
            Query query8 = entityManager.createNativeQuery("SELECT city FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            cityList = query8.getResultList();
            Query query9 = entityManager.createNativeQuery("SELECT country FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            countryList = query9.getResultList();
            Query query10 = entityManager.createNativeQuery("SELECT phone FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            phoneList = query10.getResultList();
            Query query12 = entityManager.createNativeQuery("SELECT customer.store_id FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'"   + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'"  + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");
            storeIDList = query12.getResultList();

            for(int i = 0; i < customerIDList.size(); i ++) {
                data.add(new CustomerCopy(customerIDList.get(i), firstNameList.get(i), lastNameList.get(i), emailList.get(i), addressList.get(i),
                        address2List.get(i), postalCodeList.get(i), districtList.get(i), cityList.get(i), countryList.get(i), phoneList.get(i), storeIDList.get(i)));
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        };
    }

    private void createCountry(TextField countryName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Country country = new Country();
            country.setCountry(countryName.getText());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            country.setLast_update(timestamp);
            entityManager.persist(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private short getCountryID(TextField countryName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> country_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT country_id FROM country WHERE country = " + "'" + countryName.getText() + "'");
            country_List = query.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        if(country_List.isEmpty())
        {
            return -1;

        }
        else {
            return country_List.get(0);
        }
    }

    private short getCityID(TextField cityName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> city_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT city_id FROM city WHERE city = " + "'" + cityName.getText() + "'");
            city_List = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        if(city_List.isEmpty())
        {
            return -1;
        }
        else {
            return city_List.get(0);
        }
    }

    private void createCity(TextField cityName, short countryID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            City city = new City ();
            city.setCity(cityName.getText());
            city.setCountry_id(countryID);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            city.setLast_update(timestamp);
            entityManager.persist(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private void addAddress(TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode, TextField textFieldDistrict, TextField textFieldPhone, short cityID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Address address = new Address();
            address.setAddress(textFieldAddress.getText());
            address.setAddress2(textFieldAddress2.getText());
            address.setPostal_code(textFieldPostalCode.getText());
            address.setDistrict(textFieldDistrict.getText());
            address.setCity_id(cityID);
            address.setPhone(textFieldPhone.getText());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            address.setLast_update(timestamp);
            entityManager.persist(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private short getAddressID() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> address_idList = new ArrayList<>(1);
        short addressID;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT address_id FROM address ORDER BY last_update DESC LIMIT 1");

            address_idList = query.getResultList();
            addressID = address_idList.get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return address_idList.get(0);
    }

    private void addCustomer(TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName, TextField textFieldEmail, Short addressID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer customer = new Customer();
            customer.setStore_id(Integer.parseInt(textFieldStoreID.getText()));
            customer.setFirstName(textFieldFirstName.getText());
            customer.setLastName(textFieldLastName.getText());
            customer.setEmail(textFieldEmail.getText());
            customer.setAddress_id(1);
            customer.setActive(1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            customer.setCreate_date(timestamp);
            customer.setLast_update((timestamp));
            customer.setAddress_id(addressID);
            entityManager.persist(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
