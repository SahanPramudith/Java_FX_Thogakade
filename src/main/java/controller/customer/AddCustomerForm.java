package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCustomerForm implements Initializable {


    public TableColumn colSity;
    public TableColumn colProvince;
    public TableColumn colPostalcode;
    public JFXTextField txtSity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalcode;
    @FXML
    private DatePicker DateDiob;

    @FXML
    private JFXComboBox<String> cmdTitle;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

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
        title.add("Mr");
        title.add("Miss");
        title.add("Ms");

        cmdTitle.setItems(title);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPostalcode.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));

        reload();

        tblTable.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, newval) -> {

            if (newval != null) {
                addvalueTOtext(newval);
            }
        });
    }

    private void addvalueTOtext(Customer newval) {
        txtId.setText(newval.getId());
        txtName.setText(newval.getName());
        txtAddress.setText(newval.getAddress());
        txtSalary.setText("" + newval.getSalary());
        cmdTitle.setValue(newval.getTitle());
        DateDiob.setValue(newval.getDob());
        txtSity.setText(newval.getCity());
        txtPostalcode.setText("" + newval.getPostcode());
        txtProvince.setText(newval.getProvince());

    }

    CustomerService service =CustomerController.getInstance();

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String title = cmdTitle.getValue();
        LocalDate dob = DateDiob.getValue();
        String city = txtSity.getText();
        String province = txtProvince.getText();
        String postcode = txtPostalcode.getText();

        Customer customer = new Customer(id, name, address, salary, title, dob, city, province, postcode);

        // customerList.add(customer);
        System.out.println("customer = " + customer);

        if (service.addCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION).show();
            reload();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }


    public void btnOnActionReloard(ActionEvent actionEvent) {

        reload();
    }

    private void reload() {

        ObservableList<Customer> allCustomer = service.getAllCustomer();

        tblTable.setItems(allCustomer);


    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        if (service.deleteCustomer(txtId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, " Done .. ").show();
            reload();
        }
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String title = cmdTitle.getValue();
        LocalDate dob = DateDiob.getValue();
        String city = txtSity.getText();
        String province = txtProvince.getText();
        String postcode = txtPostalcode.getText();

        Customer customer = new Customer(id, name, address, salary, title, dob, city, province, postcode);


        if (service.updateCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION).show();
            reload();
        } else {
            //new Alert(Alert.AlertType.ERROR).show();
        }
    }

    public void btnOnActionSearch(ActionEvent actionEvent) {
    }
}
