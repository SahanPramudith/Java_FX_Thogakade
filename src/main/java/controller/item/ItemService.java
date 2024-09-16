package controller.item;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {
    boolean additem(Item item);
    boolean updateitem(Item item);
    boolean deleteitem(String id);
    ObservableList<Item> getall();

}
