package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
    private String title;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobileNumber;
}