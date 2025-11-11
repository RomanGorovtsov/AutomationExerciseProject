package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> toFormParamsMapWithFullUserData() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", this.name);
        formParams.put("email", this.email);
        formParams.put("password", this.password);
        formParams.put("title", this.title);
        formParams.put("birth_day", this.birthDay);
        formParams.put("birth_month", this.birthMonth);
        formParams.put("birth_year", this.birthYear);
        formParams.put("firstname", this.firstName);
        formParams.put("lastname", this.lastName);
        formParams.put("company", this.company);
        formParams.put("address1", this.address1);
        formParams.put("country", this.country);
        formParams.put("zipcode", this.zipcode);
        formParams.put("state", this.state);
        formParams.put("city", this.city);
        formParams.put("mobile_number", this.mobileNumber);
        return formParams;
    }
}