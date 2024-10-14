package controller.item;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OderDetails;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {

    private static ItemController instance;
    private  ItemController(){}

    public static ItemController getInstance() {
        return instance==null?instance=new ItemController():instance;
    }
//==================================================================================
    @Override
    public boolean additem(Item item) {
        String sql = "insert into item values (?,?,?,?,?)";
        boolean isadd;
        try {

            Object execute = CrudUtil.execute(sql ,
                    item.getItemcode(),
                    item.getDescription(),
                    item.getPacksize(),
                    item.getUnitprice(),
                    item.getQtyonhand()
            );
            System.out.println(execute);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateitem(Item item) {
        String sql = "update item set Description=? ,PackSize=?, UnitPrice=? ,QtyOnHand=? where ItemCode=?";
        boolean isupdate;

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1, item.getDescription());
            psTm.setObject(2, item.getQtyonhand());
            psTm.setObject(3, item.getUnitprice());
            psTm.setObject(4, item.getPacksize());
            psTm.setObject(5, item.getItemcode());

            isupdate = psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isupdate) {
            return true;
        }


        return false;
    }

    @Override
    public boolean deleteitem(String id) {
        boolean isdelete;

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            isdelete = connection.createStatement().executeUpdate("delete from item where ItemCode='" + id + "'") > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isdelete) {
            return true;
        }

        return false;
    }

    @Override
    public ObservableList<Item> getall() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        String sql = "select * from item ";
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();
           while (resultSet.next()){
               Item item = new Item(
                       resultSet.getString("ItemCode"),
                       resultSet.getString("Description"),
                       resultSet.getString("PackSize"),
                       resultSet.getDouble("UnitPrice"),
                       resultSet.getInt("QtyOnHand")
               );
               items.add(item);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

@Override
    public List<String> getItemCode(){
        ObservableList<Item> getall = getall();
        ArrayList<String> itemcode = new ArrayList<>();

        getall.forEach(code->{
            itemcode.add(code.getItemcode());
        });

        return itemcode;
    }

    @Override
    public Item Serach(String code) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from item where ItemCode=?", code);
            while (resultSet.next()){
               return new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateStoke(List<OderDetails> oderDetailsList)  {

        //->--------------------- chatgpt----------------------------------------------

            for (OderDetails oderDetail : oderDetailsList) {
                // Retrieve the item code (e.g., 'P001') and the quantity to be updated
                String itemCode = oderDetail.getItemcode(); // Ensure this is a valid string
                int quantity = oderDetail.getQty(); // Ensure this is a valid integer

                // Fetch the current stock for the item
                //පරන item code
                Item item = Serach(itemCode); // Assuming the method searches for an item by code

                // Calculate the new stock by subtracting the ordered quantity from the current stock
                int newQty = item.getQtyonhand() - quantity;

                // Update the stock in the database
                boolean isUpdated = false;
                try {
                    isUpdated = CrudUtil.execute(
                            "UPDATE item SET qtyonhand=? WHERE itemcode=?",
                            newQty, itemCode
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (!isUpdated) {
                    return false; // If any stock update fails, return false
                }
            }
            return true; // All stock updates successful
        }

    }




    //-> ---------------------------My code----------------------------------

//    public boolean updateStoke(List<OderDetails> oderdetailes) {
//        for (OderDetails oderDetails:oderdetailes){
//            boolean isUpdateStoke = updateStoke(oderDetails);
//            if (!isUpdateStoke){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean updateStoke(OderDetails oderDetails) {
//       * String sql ="Update item set QtyOnHand=QtyOnHand- ? where ItemCode=?";
//
//        try {
//           return CrudUtil.execute(sql,oderDetails.getItemcode(),oderDetails.getQty());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }



