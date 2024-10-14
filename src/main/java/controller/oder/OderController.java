package controller.oder;

import controller.item.ItemController;
import db.DbConnection;
import javafx.scene.control.Alert;
import model.Oder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OderController {

    public boolean placeOder(Oder oder) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
         //====================Add oder ========================================================

         connection.setAutoCommit(false);
         PreparedStatement psTm = connection.prepareStatement("insert into orders values (?,?,?)");
         psTm.setObject(1,oder.getOderid());
         psTm.setObject(2,oder.getOderdate());
         psTm.setObject(3,oder.getCustid());
         boolean isOderAdd = psTm.executeUpdate()>0;

         if (isOderAdd){
             boolean isOderDetailsAdd =new OderDetailsController().addOderDetails(oder.getOderdetailes());
             if (isOderDetailsAdd){
                 boolean isUpdated = ItemController.getInstance().updateStoke(oder.getOderdetailes());
                 if (isUpdated){
                     connection.commit();
                     new Alert(Alert.AlertType.CONFIRMATION,"Oder Placed  ").show();
                     return true;
                 }
             }
         }
         connection.rollback();
         new Alert(Alert.AlertType.CONFIRMATION,"Not Oder placed  ").show();
         return false;
     }finally {
         connection.setAutoCommit(true);
     }
    }
}
