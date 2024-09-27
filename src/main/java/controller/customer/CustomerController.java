package controller.customer;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController implements CustomerService {
    //===============INstence==========================================================
   private static CustomerController instance ;
   private CustomerController(){}

   public static CustomerController getInstance(){
       return instance==null?instance=new CustomerController():instance;
   }
//=======================================================================================

    @Override
    public boolean addCustomer(Customer customer) {

        boolean isadd;
        try {
            String SQL = "INSERT INTO Customer values(?,?,?,?,?,?,?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, customer.getId());
            psTm.setObject(2, customer.getTitle());
            psTm.setObject(3, customer.getName());
            psTm.setObject(4, customer.getDob());
            psTm.setObject(5, customer.getSalary());
            psTm.setObject(6, customer.getAddress());
            psTm.setObject(7, customer.getCity());
            psTm.setObject(8, customer.getProvince());
            psTm.setObject(9, customer.getPostcode());

            isadd = psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isadd) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        boolean isupdated;
        try {
            String SQL = "UPDATE Customer SET CustTitle=?,CustName=?,Dob=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, customer.getTitle());
            psTm.setObject(2, customer.getName());
            psTm.setObject(3, customer.getDob());
            psTm.setObject(4, customer.getSalary());
            psTm.setObject(5, customer.getAddress());
            psTm.setObject(6, customer.getCity());
            psTm.setObject(7, customer.getProvince());
            psTm.setObject(8, customer.getPostcode());
            psTm.setObject(9, customer.getId());

            isupdated = psTm.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isupdated) {
            return true;
        }
        return false;
    }

    @Override
    public ObservableList<Customer> getAllCustomer() {

        ObservableList<Customer> custlist = FXCollections.observableArrayList();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("select * from customer");
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustTitle"),
                        resultSet.getDate("Dob").toLocalDate(),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")

                );
                System.out.println("customer = " + customer);
                custlist.add(customer);
            }
            return custlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean deleteCustomer(String id) {

        boolean isdelete;
        String sql="delete from customer where CustId="+id;
        try {
            isdelete = DbConnection.getInstance().getConnection().createStatement().executeUpdate("delete from customer where CustId='" + id + "'") > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isdelete) {
            return true;
        }

        return false;
    }

    @Override
    public Customer customerSearch(String id) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from customer where CustId=?", id);

            while (resultSet.next()){
              return   new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustTitle"),
                        resultSet.getDate("Dob").toLocalDate(),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllid() {

        ArrayList<String> custid = new ArrayList<>();
        ObservableList<Customer> allCustomer = getAllCustomer();

        allCustomer.forEach(id->{
            custid.add(id.getId());
        });
        return custid;

    }


}
