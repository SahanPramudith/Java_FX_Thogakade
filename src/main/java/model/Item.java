package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Item {

    private  String itemcode;
    private String description;
    private String packsize;
    private Double unitprice;
    private int qtyonhand;
}
