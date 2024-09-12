package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
    private Double salary;
    private String title;
    private LocalDate dob;
    private String city;
    private String province;
    private String postcode;
}
