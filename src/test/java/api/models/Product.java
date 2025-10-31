package api.models;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String price;
    private String brand;
    private Category category;

    @Data
    public static class Category {
        private UserType usertype;
        private String category;
    }

    @Data
    public static class UserType {
        private String usertype;
    }
}