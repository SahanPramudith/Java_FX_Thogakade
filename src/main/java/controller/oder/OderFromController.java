package controller.oder;

import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OderFromController implements Initializable {

    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public JFXTextField txtcustid;
    public JFXTextField txtOderid;
    public JFXTextField txtOder;
    @FXML
    private ComboBox<String> cmdCustomerID;

    @FXML
    private ComboBox<String> cmditemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbldate;

    @FXML
    private TableView<Cart> tblOder;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtStoke;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdddateAndTime();
        getCustomerId();
        getItemCode();

        cmdCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if (t1!=null){
                searchCustomer(t1);
            }
        });

        cmditemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if (t1!=null){
                searchItemcode(t1);
            }
        });
    }

    private void searchItemcode(String itemcode) {
        Item code = ItemController.getInstance().Serach(itemcode);
        txtDescription.setText(code.getDescription());
        txtStoke.setText(code.getPacksize());
        txtPrice.setText(String.valueOf(code.getUnitprice()));
    }

    private void searchCustomer(String id) {
        Customer customer = CustomerController.getInstance().customerSearch(id);
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());

    }


    ObservableList<Cart> cartTm = FXCollections.observableArrayList();
    @FXML
    void btnAddCart(ActionEvent event) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descripton"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemc = cmditemCode.getValue();
        String text = txtDescription.getText();
        Integer qty =Integer.parseInt(txtQty.getText());
        Double price = Double.parseDouble(txtPrice.getText()) ;
        Double total=qty*price;

         Integer stoke =Integer.parseInt(txtQty.getText()) ;

        if ( stoke<qty) {
            new Alert(Alert.AlertType.ERROR).show();
        }else {
            cartTm.add(new Cart(itemc,text,qty,price,total));
            tblOder.setItems(cartTm);
            addNeTototal();

        }

    }


    private void AdddateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String format = f.format(date);
        lbldate.setText(format);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))

        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void getCustomerId(){
        ArrayList<String> allid = CustomerController.getInstance().getAllid();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        allid.forEach(id->{
            observableList.add(id);
        });
        cmdCustomerID.setItems(observableList);
    }

    public void  getItemCode(){
        List<String> itemCode = ItemController.getInstance().getItemCode();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        itemCode.forEach(itemcode->{
            observableList.add(itemcode);
        });

        cmditemCode.setItems(observableList);
    }

    private void addNeTototal(){
        Double nettotal=0.0;

        for (Cart obj : cartTm) {
            nettotal += obj.getTotal();
        }
        lblNetTotal.setText(String.valueOf(nettotal));
    }


    public void btnPlaceOderOnAction(ActionEvent actionEvent) {

        String oderid = txtOder.getText();
        LocalDate date=LocalDate.parse(lbldate.getText());
        String custmerid = cmdCustomerID.getValue();
        ArrayList<OderDetails> oderdetails = new ArrayList<>();

        cartTm.forEach(obj->{
            oderdetails.add(
                    new OderDetails(
                            oderid,
                            obj.getItemcode(),
                            obj.getQty(),
                            0.0)
            );

        });

        Oder oder = new Oder(oderid, date, custmerid, oderdetails);
        System.out.println(oder);

    }
}
