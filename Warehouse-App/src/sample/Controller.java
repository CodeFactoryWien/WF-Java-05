package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.*;

public class Controller {

    @FXML private TableView CustomerTable;
    @FXML private TableView loginDataTable;
    @FXML private TableView orderListTable;
    @FXML private TableView productorderTable;
    @FXML private TableView productTable;
    @FXML private TextArea descriptionText;
    @FXML private TextArea descriptionText1;
    @FXML private TextField singlePriceText;
    @FXML private TextField bulkpriceText;
    @FXML private TextField instockText;
    @FXML private TextField productnameText;
    @FXML private TextField productIDText;
    @FXML private TextField singlePriceText1;
    @FXML private TextField bulkpriceText1;
    @FXML private TextField instockText1;
    @FXML private TextField productnameText1;
    @FXML private TextField productIDText1;
    @FXML private TextField usernameText;
    @FXML private TextField passwordText;
    @FXML private TextField usernameText1;
    @FXML private TextField passwordText1;
    @FXML private TextField clientIDText;
    @FXML private TextField clientName;
    @FXML private TextField clientAddress;
    @FXML private TextField clientEmail;
    @FXML private TextField clientPhone;
    @FXML private TextField clientName1;
    @FXML private TextField clientAddress1;
    @FXML private TextField clientEmail1;
    @FXML private TextField clientPhone1;
    @FXML private TextField SearchFieldClient;
    @FXML private TextField SearchFieldProduct;
    @FXML private ChoiceBox choiceBoxSearchClient;
    @FXML private ChoiceBox choiceBoxSearchProduct;
    @FXML private ChoiceBox choiceBoxProduct;
    @FXML private ChoiceBox choiceBoxArea;
    @FXML private ChoiceBox choiceBoxArea1;
    @FXML private ChoiceBox choiceBoxManufacturer;
    @FXML private ChoiceBox choiceBoxAvail;
    @FXML private ChoiceBox choiceBoxLocation;
    @FXML private ChoiceBox choiceBoxCategory;
    @FXML private ChoiceBox choiceBoxManu1;
    @FXML private ChoiceBox choiceBoxAvail1;
    @FXML private ChoiceBox choiceBoxLocation1;
    @FXML private ChoiceBox choiceBoxCategory1;

    private Connection con;
    DatabaseMetaData meta;
    private ObservableList CustomerList = FXCollections.observableArrayList();
    private ObservableList productorderList = FXCollections.observableArrayList();
    private ObservableList orderList = FXCollections.observableArrayList();
    private ObservableList productList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {

        // Connection to the DB, happens only once
        con =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" +
                                "feelgoodltd" +
                                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
                        "root",
                        "");
        createCostumerTable();
        createProductTable();
        Statement statement = con.createStatement();
        ResultSet rs =
                statement.executeQuery(
                        "SELECT * FROM client WHERE 1");
        choiceBoxSearchClient.getItems().add("-- Select Option --");
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            choiceBoxSearchClient.getItems().add(rs.getMetaData().getColumnName(i + 1));
        }
        choiceBoxSearchClient.setValue("-- Select Option --");
        rs =
                statement.executeQuery(
                        "SELECT p.productID,p.category,p.productname,p.description,p.location,m.manuname " +
                                "FROM product p INNER JOIN manufacturer m ON p.manuID = m.manuID WHERE 1");
        choiceBoxSearchProduct.getItems().add("-- Select Option --");
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            choiceBoxSearchProduct.getItems().add(rs.getMetaData().getColumnName(i + 1));
        }
        choiceBoxSearchProduct.setValue("-- Select Option --");
        rs =
                statement.executeQuery(
                        "SELECT manuname FROM manufacturer WHERE 1");
        choiceBoxManufacturer.getItems().add("-- Select Option --");
        choiceBoxManu1.getItems().add("-- Select Option --");
        while (rs.next()){
            choiceBoxManufacturer.getItems().add(rs.getString("manuname"));
            choiceBoxManu1.getItems().add(rs.getString("manuname"));
        }
        choiceBoxManufacturer.setValue("-- Select Option --");
        choiceBoxManu1.setValue("-- Select Option --");

        //choiceBoxAvail.getItems().add("-- Select Option --");
        choiceBoxAvail.getItems().add("Available");
        choiceBoxAvail.getItems().add("Not Available");
        choiceBoxAvail1.getItems().add("Available");
        choiceBoxAvail1.getItems().add("Not Available");
        //choiceBoxAvail.setValue("-- Select Option --");

        choiceBoxCategory.getItems().add("-- Select Option --");
        choiceBoxCategory.getItems().add("Tea");
        choiceBoxCategory.getItems().add("Coffee");
        choiceBoxCategory.getItems().add("Soft Drinks");
        choiceBoxCategory.getItems().add("Energy Drinks");
        choiceBoxCategory.setValue("-- Select Option --");

        choiceBoxCategory1.getItems().add("-- Select Option --");
        choiceBoxCategory1.getItems().add("Tea");
        choiceBoxCategory1.getItems().add("Coffee");
        choiceBoxCategory1.getItems().add("Soft Drinks");
        choiceBoxCategory1.getItems().add("Energy Drinks");
        choiceBoxCategory1.setValue("-- Select Option --");

        choiceBoxLocation.getItems().add("-- Select Option --");
        choiceBoxLocation.getItems().add("Zone 1");
        choiceBoxLocation.getItems().add("Zone 2");
        choiceBoxLocation.getItems().add("Zone 3");
        choiceBoxLocation.getItems().add("Zone 4");
        choiceBoxLocation.setValue("-- Select Option --");

        choiceBoxLocation1.getItems().add("-- Select Option --");
        choiceBoxLocation1.getItems().add("Zone 1");
        choiceBoxLocation1.getItems().add("Zone 2");
        choiceBoxLocation1.getItems().add("Zone 3");
        choiceBoxLocation1.getItems().add("Zone 4");
        choiceBoxLocation1.setValue("-- Select Option --");

        choiceBoxArea.getItems().add("-- Select Option --");
        choiceBoxArea.getItems().add("WEST");
        choiceBoxArea.getItems().add("SÜD");
        choiceBoxArea.getItems().add("OST");
        choiceBoxArea.getItems().add("NORD");
        choiceBoxArea.setValue("-- Select Option --");
        choiceBoxArea1.getItems().add("-- Select Option --");
        choiceBoxArea1.getItems().add("WEST");
        choiceBoxArea1.getItems().add("SÜD");
        choiceBoxArea1.getItems().add("OST");
        choiceBoxArea1.getItems().add("NORD");
        choiceBoxArea1.setValue("-- Select Option --");
    }
    public void createCostumerTable() throws SQLException {

        Statement stmt = con.createStatement();

        // Execute Query for the CoffeeTable data
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT clientID,clientname,clientaddress,clientemail,clientphone,shippingarea FROM client WHERE 1");

        // Filling CoffeeTable with data
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            CustomerTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            CustomerList.add(row);
        }
        CustomerTable.setItems(CustomerList);
    }
    public void createProductTable() throws SQLException {

        Statement stmt = con.createStatement();

        // Execute Query for the CoffeeTable data
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT p.productID,p.category,p.productname,m.manuname," +
                                "p.singleprice, p.bulkprice, p.instock, p.availabilty,p.location " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE 1");
        // Filling CoffeeTable with data
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            productList.add(row);
        }
        productTable.setItems(productList);
    }
    public void selectCostumer() throws SQLException {

        String selectedItem = CustomerTable.getSelectionModel().getSelectedItem().toString();
        String values[] = selectedItem.split(", ");
        String clientID = values[0].substring(1);
        String buffAddress = values[2]+", "+values[3];
        String shippingAreaBuff[] = values[6].split("]");
        String shippingArea = shippingAreaBuff[0];
        choiceBoxArea.setValue(shippingArea);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT username,password FROM client WHERE clientID = "+clientID);
        loginDataTable.getColumns().clear();
        loginDataTable.getItems().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            loginDataTable.getColumns().addAll(col);
        }
        ObservableList loginDataList = FXCollections.observableArrayList();
        String buffString[] = new String[3];
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
                buffString[i] = rs.getString(i);
            }
            loginDataList.add(row);
        }
        loginDataTable.setItems(loginDataList);

        orderList.clear();
        orderListTable.setItems(orderList);
        orderListTable.getColumns().clear();
        productorderList.clear();
        productorderTable.setItems(productorderList);
        productorderTable.getColumns().clear();
        stmt = con.createStatement();
        rs =
                stmt.executeQuery(
                        "SELECT orderID,status,total,date " +
                                "FROM ordertab " +
                                "INNER JOIN client ON client.clientID = ordertab.clientID " +
                                "WHERE client.clientID =" + clientID);
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            orderListTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            orderList.add(row);
        }
        orderListTable.setItems(orderList);

        usernameText.setText(buffString[1]);
        passwordText.setText(buffString[2]);
        clientIDText.setText(clientID);
        clientName.setText(values[1]);
        clientAddress.setText(buffAddress);
        clientEmail.setText(values[4]);
        clientPhone.setText(values[5]);
    }
    public void searchClientDB() throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs;
        switch (choiceBoxSearchClient.getValue().toString()){
            case "clientID":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientID = "+SearchFieldClient.getText());
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            case "clientname":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientname LIKE ('%"+SearchFieldClient.getText()+"%')");
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            case "clientaddress":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientaddress LIKE ('%"+SearchFieldClient.getText()+"%')");
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            case "clientemail":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientemail LIKE ('%"+SearchFieldClient.getText()+"%')");
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            case "clientphone":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientphone LIKE ('%"+SearchFieldClient.getText()+"%')");
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            case "shippingarea":
                rs = stmt.executeQuery("SELECT * FROM client WHERE shippingarea LIKE ('%"+SearchFieldClient.getText()+"%')");
                CustomerTable.getItems().clear();
                CustomerTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    CustomerTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    CustomerList.add(row);
                }
                CustomerTable.setItems(CustomerList);
                break;
            default: break;
        }
        //ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE ");
    }
    public void searchProductDB() throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs;
        switch (choiceBoxSearchProduct.getValue().toString()){
            case "productID":
                rs = stmt.executeQuery("SELECT * FROM product WHERE productID = "+SearchFieldProduct.getText());
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                productList.clear();
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            case "productname":
                rs = stmt.executeQuery("SELECT * FROM product WHERE productname LIKE ('%"+SearchFieldProduct.getText()+"%')");
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                productList.clear();
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            case "category":
                rs = stmt.executeQuery("SELECT * FROM product WHERE category LIKE ('%"+SearchFieldProduct.getText()+"%')");
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            case "description":
                rs = stmt.executeQuery("SELECT * FROM product WHERE description LIKE ('%"+SearchFieldProduct.getText()+"%')");
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            case "location":
                rs = stmt.executeQuery("SELECT * FROM product WHERE location LIKE ('%"+SearchFieldClient.getText()+"%')");
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            case "manuname":
                rs = stmt.executeQuery(" SELECT p.productID,p.category,p.productname,p.description,p.location,m.manuname " +
                                "FROM product p INNER JOIN manufacturer m ON p.manuID = m.manuID "+
                        "WHERE shippingarea LIKE ('%"+SearchFieldProduct.getText()+"%')");
                productTable.getItems().clear();
                productTable.getColumns().clear();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(
                            new Callback<
                                    TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                                public ObservableValue<String> call(
                                        TableColumn.CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });
                    productTable.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    productList.add(row);
                }
                productTable.setItems(productList);
                break;
            default: break;
        }
    }
    public void addClient() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO " +
                "`client`(`clientname`,`username`,`password`, `clientaddress`, `clientemail`, `clientphone`, `shippingarea`) " +
                "VALUES ('" +
                clientName1.getText() + "','" +
                usernameText1.getText() + "','" +
                passwordText1.getText() + "','" +
                clientAddress1.getText() + "','" +
                clientEmail1.getText() + "','" +
                clientPhone1.getText() + "','" +
                choiceBoxArea1.getValue().toString() +
                "')");
        ResultSet rs = stmt.executeQuery("SELECT clientID,clientname,clientaddress,clientemail,clientphone,shippingarea " +
                "FROM client WHERE 1");
        CustomerTable.getItems().clear();
        CustomerTable.getColumns().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            CustomerTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            CustomerList.add(row);
        }
        CustomerTable.setItems(CustomerList);
        clientName1.setText("");
        clientAddress1.setText("");
        clientEmail1.setText("");
        clientPhone1.setText("");
        usernameText1.setText("");
        passwordText1.setText("");
        //shippingAreaText1.setText("");
    }
    public void addProduct() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT manuID FROM manufacturer " +
                "WHERE manuname LIKE('" +choiceBoxManu1.getValue().toString()+"')");
        String manuID = "";
        while(rs.next()){
            manuID = rs.getString("manuID");
        }
        int l = 0;
        switch (choiceBoxAvail1.getValue().toString()){
            case "Available":
                l = 1;
                break;
            case "Not Available":
                l= 0;
                break;
            default:break;
        }
        stmt.execute("INSERT INTO " +
                "`product`( `category`, `productname`, `description`, `instock`, `singleprice`, " +
                "`bulkprice`, `availabilty`, `location`, `manuID`) " +
                "VALUES " +
                "('" + choiceBoxCategory1.getValue().toString() + "','" +
                productnameText1.getText()+"','" +
                descriptionText1.getText()+"','" +
                instockText1.getText()+"','" +
                singlePriceText1.getText()+"','" +
                bulkpriceText1.getText()+"', '" +
                l+"', '" +
                choiceBoxLocation1.getValue().toString()+"', '" +
                manuID+"')");
        productTable.getColumns().clear();
        productTable.getItems().clear();
        rs =
                stmt.executeQuery(
                        "SELECT p.productID,p.category,p.productname,m.manuname," +
                                "p.singleprice, p.bulkprice, p.instock, p.availabilty,p.location " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE 1");
        // Filling CoffeeTable with data
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            productList.add(row);
        }
        productTable.setItems(productList);
        productnameText1.setText("");
        choiceBoxCategory1.setValue("--SELECT OPTION--");
        singlePriceText1.setText("");
        bulkpriceText1.setText("");
        usernameText1.setText("");
        instockText1.setText("");
        choiceBoxAvail1.setValue("--SELECT OPTION--");
        choiceBoxLocation1.setValue("--SELECT OPTION--");
        choiceBoxManu1.setValue("--SELECT OPTION--");
    }
    public void updateClient() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `client` SET " +
                "clientname = '" + clientName.getText() +"',`" +
                "clientaddress`='" + clientAddress.getText() +"',`" +
                "clientemail`='" + clientEmail.getText() +"',`" +
                "clientphone`='" + clientPhone.getText() +"',`" +
                "shippingarea`='" + choiceBoxArea.getValue().toString() +"'" +
                " WHERE clientID = '"+ clientIDText.getText()+"'");
        ResultSet rs = stmt.executeQuery("SELECT clientID,clientname,clientaddress,clientemail,clientphone,shippingarea " +
                "FROM client WHERE 1");
        CustomerTable.getItems().clear();
        CustomerTable.getColumns().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            CustomerTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            CustomerList.add(row);
        }
        CustomerTable.setItems(CustomerList);
    }
    public void deleteClient() throws SQLException {

        Statement stmt = con.createStatement();
        stmt.execute("DELETE FROM `client` WHERE clientID ='" +clientIDText.getText()+ "'");
        ResultSet rs = stmt.executeQuery("SELECT clientID,clientname,clientaddress,clientemail,clientphone,shippingarea " +
                "FROM client WHERE 1");
        CustomerTable.getItems().clear();
        CustomerTable.getColumns().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            CustomerTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            CustomerList.add(row);
        }
        CustomerTable.setItems(CustomerList);
    }
    public void selectitemorderlist() throws SQLException {

        productorderList.clear();
        productorderTable.setItems(productorderList);
        productorderTable.getColumns().clear();

        String selectedItem = orderListTable.getSelectionModel().getSelectedItem().toString();
        String[] buff = selectedItem.split(",");
        String buffID = buff[0].substring(1);
        String[] buff2 = buff[3].split("]");

        Statement stmt = con.createStatement();

        ResultSet rs =
                stmt.executeQuery(
                        "SELECT p.productname,pol.amount,pol.subtotal FROM ordertab o "
                                + "INNER JOIN orderlist ol ON ol.orderID_FK= o.orderID "
                                + "INNER JOIN productorderlist pol ON ol.orderlistID = pol.orderlistID_FK "
                                + "INNER JOIN product p ON p.productID=pol.productID_FK "
                                + "WHERE o.orderID = "
                                + buffID);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productorderTable.getColumns().addAll(col);

        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }

            productorderList.add(row);
        }
        // FINALLY ADDED TO TableView
        productorderTable.setItems(productorderList);
    }
    public void updateUsernamePassword() throws SQLException {

        loginDataTable.getItems().clear();
        loginDataTable.getColumns().clear();

        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `client`" +
                " SET `username`= '"+ usernameText.getText() +
                "',`password`= '" + passwordText.getText()+ "' WHERE clientID = "+clientIDText.getText());
    ResultSet rs =
        stmt.executeQuery(
            "SELECT username,password FROM client WHERE clientID = " + clientIDText.getText());
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            loginDataTable.getColumns().addAll(col);
        }
        ObservableList loginDataList = FXCollections.observableArrayList();
        String buffString[] = new String[3];
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
                buffString[i] = rs.getString(i);
            }
            loginDataList.add(row);
        }
        loginDataTable.setItems(loginDataList);
    }
    public void selectProduct() throws SQLException {
        String selectedItem = productTable.getSelectionModel().getSelectedItem().toString();
        String buff[] = selectedItem.split(", ");
        String buffID = buff[0].substring(1);
        Statement stmt = con.createStatement();
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT * " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE productID = '"+buffID+"'");
        while (rs.next()){
            //System.out.println();
            productIDText.setText(rs.getString("productID"));
            choiceBoxManufacturer.setValue(rs.getString("manuname"));
            productnameText.setText(rs.getString("productname"));
            choiceBoxCategory.setValue(rs.getString("category"));
            singlePriceText.setText(rs.getString("singleprice"));
            bulkpriceText.setText(rs.getString("bulkprice"));
            instockText.setText(rs.getString("instock"));
            if(rs.getString("availabilty").equals(0)){
                choiceBoxAvail.setValue("Not Available");
            }else{
                choiceBoxAvail.setValue("Available");
            }
            choiceBoxLocation.setValue(rs.getString("location"));
            descriptionText.setText(rs.getString("description"));
        }
    }
    public void updateProduct() throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT manuID " +
                "FROM manufacturer " +
                "WHERE manuname LIKE('" + choiceBoxManufacturer.getValue() +"')");
        String manuID="";
        while (rs.next()){
            manuID = rs.getString("manuID");
        }
        String desc = descriptionText.getText();
        int k = 0;
        switch (choiceBoxAvail.getValue().toString()){
            case "Not Available":
                k = 0;
            case "Available":
                k = 1;
        }
        stmt.execute("UPDATE `product` SET " +
                "`category`= '"+ choiceBoxCategory.getValue().toString() +"'," +
                "`productname`= '"+ productnameText.getText()  +"'," +
                "`description`= ('"+ desc +"')," +
                "`instock`= '"+ instockText.getText() + "'," +
                "`singleprice`='" + singlePriceText.getText() + "'," +
                "`bulkprice`='"+ bulkpriceText.getText()+"'," +
                "`availabilty`='"+ k +"'," +
                "`location`='"+choiceBoxLocation.getValue().toString()+"'," +
                "`manuID`= '" + manuID + "'"+
                " WHERE productID = '"+productIDText.getText()+"'");
        // Execute Query for the CoffeeTable data
        rs =
                stmt.executeQuery(
                        "SELECT p.productID,p.category,p.productname,m.manuname," +
                                "p.singleprice, p.bulkprice, p.instock, p.availabilty,p.location " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE 1");
        // Filling CoffeeTable with data
        productTable.getItems().clear();
        productTable.getColumns().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            productList.add(row);
        }
        productTable.setItems(productList);
    }
    public void deleteProduct() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("DELETE FROM `product` WHERE productID ='" +productIDText.getText()+ "'");
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT p.productID,p.category,p.productname,m.manuname," +
                                "p.singleprice, p.bulkprice, p.instock, p.availabilty,p.location " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE 1");
        // Filling CoffeeTable with data
        productTable.getItems().clear();
        productTable.getColumns().clear();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            productList.add(row);
        }
        productTable.setItems(productList);
    }
    public void showAllProducts() throws SQLException {
        productTable.getItems().clear();
        productTable.getColumns().clear();
        createProductTable();
    }
    public void itemsToRestock() throws SQLException {
        productTable.getItems().clear();
        productTable.getColumns().clear();
        Statement stmt = con.createStatement();

        // Execute Query for the CoffeeTable data
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT p.productID,p.category,p.productname,m.manuname," +
                                "p.singleprice, p.bulkprice, p.instock, p.availabilty,p.location " +
                                "FROM  product p INNER JOIN manufacturer m ON p.manuID = m.manuID " +
                                "WHERE instock < 50");
        // Filling CoffeeTable with data
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            // We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(
                    new Callback<
                            TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(
                                TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
            productTable.getColumns().addAll(col);
        }
        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                row.add(rs.getString(i));
            }
            productList.add(row);
        }
        productTable.setItems(productList);
    }
}
