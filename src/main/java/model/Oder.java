package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Oder {
    private String oderid;
    private LocalDate oderdate;
    private String custid;
    private List<OderDetails> oderdetailes;


}
