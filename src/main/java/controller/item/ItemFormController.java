package controller.item;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public TableView tblItem;
    @FXML
    private TableColumn<?, ?> col;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescrip;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescrip;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSiz;

    ItemService service=new ItemController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));
        col.setCellValueFactory(new PropertyValueFactory<>("packsize"));

        reloard();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) ->{
                if (t1!=null){
                    addtotext((Item) t1);
                }
        } );
    }

    private void addtotext(Item t1) {
        txtCode.setText(t1.getItemcode());
        txtDescrip.setText(t1.getDescription());
        txtPrice.setText(""+t1.getUnitprice());
        txtQty.setText(""+t1.getQtyonhand());
        txtSiz.setText(""+t1.getPacksize());
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {

        String code = txtCode.getText();
        String descrip = txtDescrip.getText();
        String packsize = txtSiz.getText();
        Double price = Double.parseDouble(txtPrice.getText()) ;
        int qty =Integer.parseInt(txtQty.getText()) ;

        Item item = new Item(code,descrip,packsize,price,qty);

        if (service.additem(item)){
            new Alert(Alert.AlertType.INFORMATION).show();
            reloard();
        }

    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        if (service.deleteitem(txtCode.getText())){
            new  Alert(Alert.AlertType.INFORMATION,"Delete Successes").show();
            reloard();
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String code = txtCode.getText();
        String descrip = txtDescrip.getText();
        String packsize = txtSiz.getText();
        Double price = Double.parseDouble(txtPrice.getText()) ;
        int qty =Integer.parseInt(txtQty.getText()) ;

        Item item = new Item(code,descrip,packsize,price,qty);
        if (service.updateitem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item updated").show();
            reloard();
        }
    }

    public void reloard(){
        ObservableList<Item> getall = service.getall();
        tblItem.setItems(getall);
    }

    public void btnOnActionReloard(ActionEvent actionEvent) {
       reloard();



    }


}
