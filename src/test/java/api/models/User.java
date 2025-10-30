package api.models;

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

    public User(String name, String email, String password, String title,
                String birthDay, String birthMonth, String birthYear,
                String firstName, String lastName, String company,
                String address1, String country, String zipcode,
                String state, String city, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.title = title;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address1 = address1;
        this.country = country;
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.mobileNumber = mobileNumber;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTitle() { return title; }
    public String getBirthDay() { return birthDay; }
    public String getBirthMonth() { return birthMonth; }
    public String getBirthYear() { return birthYear; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getCountry() { return country; }
    public String getZipcode() { return zipcode; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getMobileNumber() { return mobileNumber; }
}