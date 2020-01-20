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

    @FXML private TextField clientIDText;
    @FXML private TextField clientName;
    @FXML private TextField clientAddress;
    @FXML private TextField clientEmail;
    @FXML private TextField clientPhone;
    @FXML private TextField shippingAreaText;
    @FXML private TextField clientName1;
    @FXML private TextField clientAddress1;
    @FXML private TextField clientEmail1;
    @FXML private TextField clientPhone1;
    @FXML private TextField shippingAreaText1;
    @FXML private TextField SearchField;

    @FXML private ChoiceBox choiceBoxSearch;

    private Connection con;
    DatabaseMetaData meta;
    private ObservableList CustomerList = FXCollections.observableArrayList();

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

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            choiceBoxSearch.getItems().add(rs.getMetaData().getColumnName(i + 1));
        }
    }
    public void createCostumerTable() throws SQLException {

        Statement stmt = con.createStatement();

        // Execute Query for the CoffeeTable data
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT * FROM client WHERE 1");

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

    public void selectCostumer(){

        String selectedItem = CustomerTable.getSelectionModel().getSelectedItem().toString();
        String values[] = selectedItem.split(", ");
        String clientID = values[0].substring(1);
        String buffAddress = values[2]+", "+values[3];
        String shippingAreaBuff[] = values[6].split("]");
        String shippingArea = shippingAreaBuff[0];

        clientIDText.setText(clientID);
        clientName.setText(values[1]);
        clientAddress.setText(buffAddress);
        clientEmail.setText(values[4]);
        clientPhone.setText(values[5]);
        shippingAreaText.setText(shippingArea);
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
                "`client`(`clientname`, `clientaddress`, `clientemail`, `clientphone`, `shippingarea`) " +
                "VALUES ('" +
                clientName1.getText() + "','" +
                clientAddress1.getText() + "','" +
                clientEmail1.getText() + "','" +
                clientPhone1.getText() + "','" +
                shippingAreaText1.getText() +
                "')");
        ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE 1");
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
        shippingAreaText1.setText("");
    }
    public void updateClient() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `client` SET " +
                "clientname = '" + clientName.getText() +"',`" +
                "clientaddress`='" + clientAddress.getText() +"',`" +
                "clientemail`='" + clientEmail.getText() +"',`" +
                "clientphone`='" + clientPhone.getText() +"',`" +
                "shippingarea`='" + shippingAreaText.getText() +"'" +
                " WHERE clientID = '"+ clientIDText.getText()+"'");
        ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE 1");
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE 1");
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
}
