package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OderDetails {
    private String oderid;
    private String itemcode;
    private Integer qty;
    private double descount;
}
