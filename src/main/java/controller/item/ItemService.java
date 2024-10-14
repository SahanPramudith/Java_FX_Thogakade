package controller.item;

import javafx.collections.ObservableList;
import model.Item;
import model.OderDetails;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    boolean additem(Item item);
    boolean updateitem(Item item);
    boolean deleteitem(String id);
    ObservableList<Item> getall();
    List<String> getItemCode();
    Item Serach(String code);
    boolean updateStoke(List<OderDetails> oderdetailes) throws SQLException;
}
