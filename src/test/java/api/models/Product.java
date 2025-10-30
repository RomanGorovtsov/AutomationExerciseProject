package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private String price;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("category")
    private Category category;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getBrand() { return brand; }
    public Category getCategory() { return category; }



    public static class Category {
        @JsonProperty("usertype")
        private UserType usertype;

        @JsonProperty("category")
        private String category;

        public UserType getUsertype() { return usertype; }
        public String getCategory() { return category; }
    }



    public static class UserType {
        @JsonProperty("usertype")
        private String usertype;

        public String getUsertype() { return usertype; }
    }
}

