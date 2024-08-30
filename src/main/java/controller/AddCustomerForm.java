package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCustomerForm implements Initializable {

    @FXML
    private DatePicker DateDiob;

    @FXML
    private JFXComboBox<String> cmdTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<Customer> tblTable;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> title = FXCollections.observableArrayList();
        title.add("MR.");
        title.add("Miss.");

        cmdTitle.setItems(title);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }


    ArrayList<Customer> customerList = new ArrayList<>();
    @FXML
    void btnOnActionAdd(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String title = cmdTitle.getValue();

        Customer customer = new Customer(id, name, address, salary, title);
        customerList.add(customer);
        System.out.println("customer = " + customer);




    }

    public void btnOnActionReloard(ActionEvent actionEvent) {

        ObservableList<Customer> custlist = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "sahan");
            PreparedStatement psTm = connection.prepareStatement("select * from customer");
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustTitle")
                );
                System.out.println("customer = " + customer);
                custlist.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        customerList.forEach(customer -> {
            custlist.add(customer);
        });
        tblTable.setItems(custlist);


    }


}
