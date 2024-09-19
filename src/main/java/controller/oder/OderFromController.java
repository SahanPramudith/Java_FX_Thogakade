package controller.oder;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class OderFromController implements Initializable {

    @FXML
    private ComboBox<?> cmdCustomerID;

    @FXML
    private ComboBox<?> cmditemCode;

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
    private Label lblOdetId;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbldate;

    @FXML
    private TableView<?> tblOder;

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
    }

    @FXML
    void btnAddCart(ActionEvent event) {

    }

    @FXML
    void btnAddCartOnAction(ActionEvent event) {

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


}
