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
import model.Customer;

import java.net.URL;
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
    private TableView<?> tblTable;

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


    }


}
