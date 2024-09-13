package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    ObservableList<Customer> getAllCustomer();

    boolean deleteCustomer(String id);

    Customer customerSearch(String id);

}
