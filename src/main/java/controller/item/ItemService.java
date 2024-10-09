package controller.item;

import javafx.collections.ObservableList;
import model.Item;

import java.util.List;

public interface ItemService {
    boolean additem(Item item);
    boolean updateitem(Item item);
    boolean deleteitem(String id);
    ObservableList<Item> getall();
    List<String> getItemCode();
    Item Serach(String code);

}
