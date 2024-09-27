package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private String itemcode;
    private String descripton;
    private int qty;
    private double total;
}
