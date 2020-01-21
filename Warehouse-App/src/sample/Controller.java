package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.sql.*;

public class Controller {

    @FXML private TableView CustomerTable;
    @FXML private TableView loginDataTable;
    @FXML private TableView orderListTable;
    @FXML private TableView productorderTable;

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
    @FXML private TextField SearchField;

    @FXML private ChoiceBox choiceBoxSearch;
    @FXML private ChoiceBox choiceBoxArea;
    @FXML private ChoiceBox choiceBoxArea1;

    private Connection con;
    DatabaseMetaData meta;
    private ObservableList CustomerList = FXCollections.observableArrayList();
    private ObservableList productorderList = FXCollections.observableArrayList();
    private ObservableList orderList = FXCollections.observableArrayList();

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
        Statement statement = con.createStatement();
        ResultSet rs =
                statement.executeQuery(
                        "SELECT * FROM client WHERE 1");
        choiceBoxSearch.getItems().add("-- Select Option --");
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            choiceBoxSearch.getItems().add(rs.getMetaData().getColumnName(i + 1));
        }
        choiceBoxArea.getItems().add("WEST");
        choiceBoxArea.getItems().add("SÜD");
        choiceBoxArea.getItems().add("OST");
        choiceBoxArea.getItems().add("NORD");
        choiceBoxArea1.getItems().add("WEST");
        choiceBoxArea1.getItems().add("SÜD");
        choiceBoxArea1.getItems().add("OST");
        choiceBoxArea1.getItems().add("NORD");
        //choiceBoxArea.setValue();
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
        switch (choiceBoxSearch.getValue().toString()){
            case "clientID":
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientID = "+SearchField.getText());
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
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientname LIKE ('%"+SearchField.getText()+"%')");
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
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientaddress LIKE ('%"+SearchField.getText()+"%')");
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
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientemail LIKE ('%"+SearchField.getText()+"%')");
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
                rs = stmt.executeQuery("SELECT * FROM client WHERE clientphone LIKE ('%"+SearchField.getText()+"%')");
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
                rs = stmt.executeQuery("SELECT * FROM client WHERE shippingarea LIKE ('%"+SearchField.getText()+"%')");
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
}
