package api.clients;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;


public class ProductsClient extends BaseApiClient {

    String globalEmail;

    public Response getAllProducts() {
        return get("/productsList");
    }

    public Response getAllBrandsList(){
        return get("/brandsList");
    }

    public Response postToAllProducts(){
        return post("/productsList");
    }

    public Response postToSearchProductsWithoutParams(){
        return post("/searchProduct");
    }

    public Response putBrandsList(){
        return post("/brandsList");
    }

    public Response deleteVerifyLogin(){
        return delete("/verifyLogin");
    }

    public Response searchProductWithFormatData(String searchTerm) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("search_product", searchTerm);
        return postWithFormData("/searchProduct", formParams);
    }

    public Response postToVerifyLoginWithValidData(String email, String password){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", email);
        formParams.put("password", password);
        return postWithFormData("/verifyLogin", formParams);
    }

    public Response postToVerifyLoginWithoutEmail(String password){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        return postWithFormData("/verifyLogin", formParams);
    }

    public Response postToCreateAccount(String name, String email, String password, String title, String birthDate, String birthMonth, String birthYear,String firstName, String lastName, String company, String address1, String address2, String country, String zipcode, String state, String city, String MobileNumber){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        String uniqueEmail = email + System.currentTimeMillis() + "@test.com";
        globalEmail = uniqueEmail;
        formParams.put("email", uniqueEmail);
        formParams.put("password", password);
        formParams.put("title", title);
        formParams.put("birth_date", birthDate);
        formParams.put("birth_mounth", birthMonth);
        formParams.put("birth_year", birthYear);
        formParams.put("firstname", firstName);
        formParams.put("lastname", lastName);
        formParams.put("company", company);
        formParams.put("address1", address1);
        formParams.put("address2", address2);
        formParams.put("country", country);
        formParams.put("zipcode", zipcode);
        formParams.put("state", state);
        formParams.put("city", city);
        formParams.put("mobile_number", MobileNumber);
        return postWithFormData("/createAccount", formParams);
    }

    public Response putToUpdateAccount(String name, String email, String password, String title, String birthDate, String birthMonth, String birthYear,String firstName, String lastName, String company, String address1, String address2, String country, String zipcode, String state, String city, String MobileNumber){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("email", globalEmail);
        formParams.put("password", password);
        formParams.put("title", title);
        formParams.put("birth_date", birthDate);
        formParams.put("birth_mounth", birthMonth);
        formParams.put("birth_year", birthYear);
        formParams.put("firstname", firstName);
        formParams.put("lastname", lastName);
        formParams.put("company", company);
        formParams.put("address1", address1);
        formParams.put("address2", address2);
        formParams.put("country", country);
        formParams.put("zipcode", zipcode);
        formParams.put("state", state);
        formParams.put("city", city);
        formParams.put("mobile_number", MobileNumber);
        return putWithFormData("/updateAccount", formParams);
    }

    public Response deleteUserAccount(String email, String password){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", globalEmail);
        formParams.put("password", password);
        return deleteWithFormData("/deleteAccount", formParams);
    }

    public Response getUserDetailByEmail(){
        return getWithQueryParams("/getUserDetailByEmail", globalEmail);
    }

    public String getGlobalEmail() {
        return globalEmail;
    }
}