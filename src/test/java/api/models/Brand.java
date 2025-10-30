package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {

    @JsonProperty("id")
    private int id;

    @JsonProperty("brand")
    private String brand;

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
}
