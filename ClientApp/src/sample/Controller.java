package sample;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

  private Connection con;
  private int pos;
  private BigDecimal sum;
  private BigDecimal buffsum;

  @FXML private TableView allproducts;
  @FXML private TableView coffeeproducts;
  @FXML private TableView teaproducts;
  @FXML private TableView softdrinksproducts;
  @FXML private TableView shoppingcart;
  @FXML private TableView energydrinksproducts;
  @FXML private TableView orderListTable;
  @FXML private TableView productorderTable;

  @FXML private TextField clientUsername;
  @FXML private PasswordField clientPassword;
  @FXML private TextField productID;
  @FXML private TextField productnameText;
  @FXML private TextField singlepriceText;
  @FXML private TextField bulkpriceText;
  @FXML private TextField amountText;
  @FXML private TextField searchText;

  @FXML private Label totalLabel;
  @FXML private Label productDescLabel;

  @FXML private ChoiceBox selectProductCategory;

  private ObservableList productorderList = FXCollections.observableArrayList();
  private ObservableList orderList = FXCollections.observableArrayList();
  private ObservableList shoppingcartList = FXCollections.observableArrayList();

  private TableColumn<String, String> shoppingcartCol =
      new TableColumn<String, String>("Shoppingcart");

  public void initialize() throws SQLException {
    shoppingcartCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
    shoppingcart.getColumns().add(shoppingcartCol);
    // Connection to the DB, happens only once
    con =
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" +
                    "feelgoodltd" +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
            "root",
            "");
    createCoffeeTable();
    createTeaTable();
    createSoftDrinksTable();
    createEnergyDrinksTable();
    createAllProductsTable();
      Statement statement = con.createStatement();
      ResultSet rs =
              statement.executeQuery(
                      "SELECT productID,productname FROM product WHERE instock <> 0");

      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
          selectProductCategory.getItems().add(rs.getMetaData().getColumnName(i + 1));
      }
      selectProductCategory.getItems().add("All");
  }

  private void createAllProductsTable() throws SQLException {
      ObservableList allproductsList = FXCollections.observableArrayList();
      Statement stmt = con.createStatement();

    // Execute Query for the CoffeeTable data
    ResultSet rs =
        stmt.executeQuery(
            "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE instock <> 0");

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
      allproducts.getColumns().addAll(col);
    }
    while (rs.next()) {
      // Iterate Row
      ObservableList<String> row = FXCollections.observableArrayList();
      for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        // Iterate Column
        row.add(rs.getString(i));
      }
      allproductsList.add(row);
    }
    allproducts.setItems(allproductsList);
  }
  private void createCoffeeTable() throws SQLException {
      ObservableList coffeeproductsList = FXCollections.observableArrayList();
      Statement stmt = con.createStatement();

    // Execute Query for the CoffeeTable data
    ResultSet rs =
        stmt.executeQuery(
            "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE category LIKE ('Coffee') AND instock <> 0");

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
      coffeeproducts.getColumns().addAll(col);
    }
    while (rs.next()) {
      // Iterate Row
      ObservableList<String> row = FXCollections.observableArrayList();
      for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        // Iterate Column
        row.add(rs.getString(i));
      }
      coffeeproductsList.add(row);
    }
    coffeeproducts.setItems(coffeeproductsList);
  }
  private void createTeaTable() throws SQLException {
      ObservableList teaproductsList = FXCollections.observableArrayList();
      //// Execute Query for the TeaTable data
    Statement stmt2 = con.createStatement();
    ResultSet rs2 =
        stmt2.executeQuery(
            "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE category LIKE ('Tea') AND instock <> 0");

    // Filling TeaTable with data
    for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {
      // We are using non property style for making dynamic table
      final int j = i;
      TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i + 1));
      col.setCellValueFactory(
          new Callback<
              TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(
                TableColumn.CellDataFeatures<ObservableList, String> param) {
              return new SimpleStringProperty(param.getValue().get(j).toString());
            }
          });
      teaproducts.getColumns().addAll(col);
    }
    while (rs2.next()) {
      // Iterate Row
      ObservableList<String> row = FXCollections.observableArrayList();
      for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
        // Iterate Column
        row.add(rs2.getString(i));
      }
      teaproductsList.add(row);
    }
    teaproducts.setItems(teaproductsList);
  }
  private void createSoftDrinksTable() throws SQLException {
      ObservableList softdrinksproductsList = FXCollections.observableArrayList();
      //// Execute Query for the SoftDrinksTable data
    Statement stmt3 = con.createStatement();
    ResultSet rs3 =
        stmt3.executeQuery(
            "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE category LIKE ('Soft Drinks') AND instock <> 0");

    // Filling SoftDrinksTable with data
    for (int i = 0; i < rs3.getMetaData().getColumnCount(); i++) {
      // We are using non property style for making dynamic table
      final int j = i;
      TableColumn col = new TableColumn(rs3.getMetaData().getColumnName(i + 1));
      col.setCellValueFactory(
          new Callback<
              TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(
                TableColumn.CellDataFeatures<ObservableList, String> param) {
              return new SimpleStringProperty(param.getValue().get(j).toString());
            }
          });
      softdrinksproducts.getColumns().addAll(col);
    }
    while (rs3.next()) {
      // Iterate Row
      ObservableList<String> row = FXCollections.observableArrayList();
      for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
        // Iterate Column
        row.add(rs3.getString(i));
      }
      softdrinksproductsList.add(row);
    }
    softdrinksproducts.setItems(softdrinksproductsList);
  }
  private void createEnergyDrinksTable() throws SQLException {
      ObservableList energydrinksproductsList = FXCollections.observableArrayList();
      // Execute Query for the EnergyDrinksTable data
    Statement stmt4 = con.createStatement();
    ResultSet rs4 =
        stmt4.executeQuery(
            "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE category LIKE ('Energy Drinks') AND instock <> 0");

    // Filling SoftDrinksTable with data
    for (int i = 0; i < rs4.getMetaData().getColumnCount(); i++) {
      // We are using non property style for making dynamic table
      final int j = i;
      TableColumn col = new TableColumn(rs4.getMetaData().getColumnName(i + 1));
      col.setCellValueFactory(
          new Callback<
              TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(
                TableColumn.CellDataFeatures<ObservableList, String> param) {
              return new SimpleStringProperty(param.getValue().get(j).toString());
            }
          });
      energydrinksproducts.getColumns().addAll(col);
    }
    while (rs4.next()) {
      // Iterate Row
      ObservableList<String> row = FXCollections.observableArrayList();
      for (int i = 1; i <= rs4.getMetaData().getColumnCount(); i++) {
        // Iterate Column
        row.add(rs4.getString(i));
      }
      energydrinksproductsList.add(row);
    }
    energydrinksproducts.setItems(energydrinksproductsList);
  }

  public void selectAllProductsTable() throws SQLException {
    amountText.setText("");
    String selectedItem = allproducts.getSelectionModel().getSelectedItem().toString();
    String[] buff = selectedItem.split(", ");
    String buff3 = buff[0].substring(1);
    String[] buff2 = buff[3].split("]");
    productnameText.setText(buff[1]);
    singlepriceText.setText(buff[2]);
    bulkpriceText.setText(buff2[0]);
    productID.setText(buff3);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT description FROM product WHERE productID = " + buff3);
    while (rs.next()) {
      productDescLabel.setText(rs.getString("description"));
    }
  }
  public void selectItemcoffee() throws SQLException {
    amountText.setText("");
    String selectedItem = coffeeproducts.getSelectionModel().getSelectedItem().toString();
    String[] buff = selectedItem.split(", ");
    String buff3 = buff[0].substring(1);
    String[] buff2 = buff[3].split("]");
    productnameText.setText(buff[1]);
    singlepriceText.setText(buff[2]);
    bulkpriceText.setText(buff2[0]);
    productID.setText(buff3);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT description FROM product WHERE productID = " + buff3);
    while (rs.next()) {
      productDescLabel.setText(rs.getString("description"));
    }
  }
  public void selectItemtea() throws SQLException {
    amountText.setText("");
    String selectedItem = teaproducts.getSelectionModel().getSelectedItem().toString();
    String[] buff = selectedItem.split(", ");
    String buff3 = buff[0].substring(1);
    String[] buff2 = buff[3].split("]");
    productnameText.setText(buff[1]);
    singlepriceText.setText(buff[2]);
    bulkpriceText.setText(buff2[0]);
    productID.setText(buff3);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT description FROM product WHERE productID = " + buff3);
    while (rs.next()) {
      productDescLabel.setText(rs.getString("description"));
    }
  }
  public void selectItemsoftdrinks() throws SQLException {
    amountText.setText("");
    String selectedItem = softdrinksproducts.getSelectionModel().getSelectedItem().toString();
    String[] buff = selectedItem.split(",");
    String buff3 = buff[0].substring(1);
    String[] buff2 = buff[3].split("]");
    productnameText.setText(buff[1]);
    singlepriceText.setText(buff[2]);
    bulkpriceText.setText(buff2[0]);
    productID.setText(buff3);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT description FROM product WHERE productID = " + buff3);
    while (rs.next()) {
      productDescLabel.setText(rs.getString("description"));
    }
  }
  public void selectItemenergydrinks() throws SQLException {
    amountText.setText("");
    String selectedItem = energydrinksproducts.getSelectionModel().getSelectedItem().toString();
    String[] buff = selectedItem.split(",");
    String buff3 = buff[0].substring(1);
    String[] buff2 = buff[3].split("]");
    productnameText.setText(buff[1]);
    singlepriceText.setText(buff[2]);
    bulkpriceText.setText(buff2[0]);
    productID.setText(buff3);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT description FROM product WHERE productID = " + buff3);
    while (rs.next()) {
      productDescLabel.setText(rs.getString("description"));
    }
  }
  public void selectitemshoppingcart() throws SQLException {
        pos = shoppingcart.getSelectionModel().getSelectedIndex();
        Statement stmt = con.createStatement();
        String buff = shoppingcart.getSelectionModel().getSelectedItem().toString();
        String[] buff2 = buff.split("No. ");
        String[] buff3 = buff2[1].split(" productname: ");
        String[] buff4 = buff3[1].split(" amount: ");
        String[] buff5 = buff4[1].split(" total");
        String buffID = buff3[0];
        productID.setText(buffID);
        productnameText.setText(buff4[0]);
        amountText.setText(buff5[0]);
        ResultSet rs =
                stmt.executeQuery("SELECT singleprice,bulkprice FROM product WHERE productID = " + buffID);
        while (rs.next()) {
            singlepriceText.setText(rs.getString("singleprice"));
            bulkpriceText.setText(rs.getString("bulkprice"));
        }
        BigDecimal bigDecimalSinglePrice=new BigDecimal(singlepriceText.getText());
        BigDecimal bigDecimalBulkPrice=new BigDecimal(bulkpriceText.getText());
        if (Integer.parseInt(amountText.getText()) < 10) {
           buffsum = bigDecimalSinglePrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));

        } else {
            buffsum = bigDecimalBulkPrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));
        }
    }

  public void addtoshoppingcart() {
      BigDecimal bigDecimalTotal= BigDecimal.ZERO;
      BigDecimal bigDecimalSinglePrice=new BigDecimal(singlepriceText.getText());
      BigDecimal bigDecimalBulkPrice=new BigDecimal(bulkpriceText.getText());
      if (Integer.parseInt(amountText.getText()) < 10) {
          bigDecimalTotal = bigDecimalSinglePrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));

      } else {
          bigDecimalTotal = bigDecimalBulkPrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));
      }
      String total = bigDecimalTotal.toString();
    shoppingcartList.add(
        "No. "
            + productID.getText()
            + " productname: "
            + productnameText.getText()
            + " "
            + "amount: "
            + amountText.getText()
            + " total: "
            + total
            + "€");
    shoppingcart.setItems(shoppingcartList);
    shoppingcart.refresh();
    sum = sum.add(bigDecimalTotal);
    totalLabel.setText("Total:" + sum.toString() + "€");
  }

  public void deletefromshoppingcart() {
    shoppingcart.getItems().remove(shoppingcart.getSelectionModel().getSelectedItem());
    sum = sum.subtract(buffsum);
    totalLabel.setText("Total:" + sum.toString() + "€");
  }

  public void updateamount() throws SQLException {

      sum = sum.subtract(buffsum);
      shoppingcart.getItems().remove(pos);
      BigDecimal bigDecimalTotal= BigDecimal.ZERO;
      BigDecimal bigDecimalSinglePrice=new BigDecimal(singlepriceText.getText());
      BigDecimal bigDecimalBulkPrice=new BigDecimal(bulkpriceText.getText());
      if (Integer.parseInt(amountText.getText()) < 10) {
          bigDecimalTotal = bigDecimalSinglePrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));

      } else {
          bigDecimalTotal = bigDecimalBulkPrice.multiply(new BigDecimal(Integer.parseInt(amountText.getText())));
      }
      String total = bigDecimalTotal.toString();
    shoppingcartList.add(
        "No. "
            + productID.getText()
            + " productname: "
            + productnameText.getText()
            + " "
            + "amount: "
            + amountText.getText()
            + " total: "
            + total
            + "€");
    shoppingcart.setItems(shoppingcartList);
    shoppingcart.refresh();
    sum = sum.add(bigDecimalTotal);
    totalLabel.setText("Total:" + sum.toString() + "€");
  }

  public void userlogin() throws SQLException {

    Statement stmt2 = con.createStatement();
    if(stmt2.execute("SELECT username, password FROM client " +
            "WHERE username LIKE('" +clientUsername.getText()+ "') AND " +
            "password LIKE ('" +clientPassword.getText()+ "')")){
        sum = BigDecimal.ZERO;
        buffsum = BigDecimal.ZERO;
        totalLabel.setText("Total: "+sum+"€");
        orderList.clear();
        orderListTable.setItems(orderList);
        orderListTable.getColumns().clear();
        productorderList.clear();
        productorderTable.setItems(productorderList);
        productorderTable.getColumns().clear();
        shoppingcartList.clear();
        shoppingcart.setItems(shoppingcartList);
        shoppingcart.refresh();
        Statement stmt = con.createStatement();
        ResultSet rs =
                stmt.executeQuery(
                        "SELECT orderID,status,total,date " +
                                "FROM ordertab " +
                                "INNER JOIN client ON client.clientID = ordertab.clientID " +
                                "WHERE username LIKE ('" + clientUsername.getText()+"')");
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
    }
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

  public void sendorder() throws SQLException {

      totalLabel.setText("Total: ");
    int orderID_FK = 0;
    int orderlistID_FK = 0;
    Date date = new Date();
    String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT MAX(orderID) FROM ordertab");
    while (rs.next()) {
      orderID_FK = Integer.parseInt(rs.getString("MAX(orderID)")) + 1;
    }
      Statement stmt3 = con.createStatement();
      ResultSet rs2 = stmt3.executeQuery("SELECT clientID FROM client " +
              "WHERE username LIKE ('" + clientUsername.getText()
              +"')");
      String buffIDText ="";
      while (rs2.next()){
          buffIDText = rs2.getString("clientID");
      }
    rs = stmt.executeQuery("SELECT * FROM client WHERE ");
    stmt.execute(
        "INSERT INTO `ordertab`(`status`, `total`, `date`, `clientID`, `shippingteamID_FK`) "
            + "VALUES (0,"
            + sum
            + ",'"
            + dateString
            + "',"
            + buffIDText
            + ","
            + "null)");
    stmt.execute("INSERT INTO `orderlist`(`orderID_FK`) VALUES (" + orderID_FK + ")");
    rs = stmt.executeQuery("SELECT COUNT(orderlistID) FROM orderlist");
    while (rs.next()) {
      orderlistID_FK = Integer.parseInt(rs.getString("COUNT(orderlistID)")) + 1;
    }
    int buffID = 0;
    ObservableList buffList = shoppingcart.getItems();
    for (int i = 0; i < shoppingcart.getItems().size(); i++) {
      String buff = buffList.get(i).toString();

      String[] buff2 = buff.split("No. ");
      String[] buff3 = buff2[1].split(" productname: ");
      String[] buff4 = buff3[1].split(" amount: ");
      String[] buff5 = buff4[1].split(" total: ");
      String[] buff6 = buff5[1].split("€");

      buffID = Integer.parseInt(buff3[0]);
      int buffAmount = Integer.parseInt(buff5[0]);
      BigDecimal buffTotalBigDecimal = new BigDecimal(buff6[0]);
      String buffTotal = buffTotalBigDecimal.toString();
      stmt.execute(
          "INSERT INTO `productorderlist`(`orderlistID_FK`, `productID_FK`, `amount`, `subtotal`) "
              + "VALUES ("
              + orderlistID_FK
              + ","
              + buffID
              + ","
              + buffAmount
              + ","
              + buffTotal
              + ")");
    }
    // stmt.execute("INSERT INTO `orderlist`(`orderID_FK`) VALUES ("+orderID_FK+")");
    orderList.clear();
    orderListTable.setItems(orderList);
    orderListTable.getColumns().clear();

    rs =
        stmt.executeQuery(
            "SELECT orderID,status,total,date FROM ordertab WHERE clientID = "
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
    shoppingcartList.clear();
    shoppingcart.setItems(shoppingcartList);
    shoppingcart.refresh();
      sum = BigDecimal.ZERO;
      buffsum = BigDecimal.ZERO;
  }
  public void searchProducts() throws SQLException {
        Statement stmt = con.createStatement();
        ObservableList allprodutsList = FXCollections.observableArrayList();
        ResultSet rs;
        switch (selectProductCategory.getValue().toString()){
            case "productID":
                rs = stmt.executeQuery("SELECT productID,productname,singleprice,bulkprice,instock " +
                        "FROM product WHERE instock <> 0 AND productID = '"+searchText.getText()+"'");
                System.out.println("Query Send");
                allproducts.getItems().clear();
                allproducts.getColumns().clear();
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
                    allproducts.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    allprodutsList.add(row);
                }
                allproducts.setItems(allprodutsList);
                break;
            case "productname":
                rs = stmt.executeQuery("SELECT productID,productname,singleprice,bulkprice,instock " +
                        "FROM product WHERE instock <> 0 AND productname LIKE ('%"+searchText.getText()+"%')");
                allproducts.getItems().clear();
                allproducts.getColumns().clear();
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
                    allproducts.getColumns().addAll(col);
                }
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    allprodutsList.add(row);
                }
                allproducts.setItems(allprodutsList);
                break;
            case "All":
                 rs =
                        stmt.executeQuery(
                                "SELECT productID,productname,singleprice,bulkprice,instock FROM product WHERE instock <> 0");

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
                    allproducts.getColumns().addAll(col);
                }
                ObservableList allproductsList = FXCollections.observableArrayList();
                while (rs.next()) {
                    // Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        // Iterate Column
                        row.add(rs.getString(i));
                    }
                    allproductsList.add(row);
                }
                allproducts.setItems(allproductsList);
            default: break;
        }

    }
}
