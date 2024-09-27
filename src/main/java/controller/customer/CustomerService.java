package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;

import java.util.ArrayList;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    ObservableList<Customer> getAllCustomer();

    boolean deleteCustomer(String id);

    Customer customerSearch(String id);

    ArrayList<String> getAllid();
}
