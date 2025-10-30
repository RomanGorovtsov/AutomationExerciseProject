package api.clients;

import api.models.User;
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

    public Response postToVerifyLoginWithEmailAndPassword(User user){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        return postWithFormData("/verifyLogin", formParams);
    }

    public Response postToVerifyLoginWithInvalidEmailAndPassword(String invalidEmail, String invalidPassword){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", invalidEmail + System.currentTimeMillis() + "@yahoo.com");
        formParams.put("password", invalidPassword);
        return postWithFormData("/verifyLogin", formParams);
    }

    public Response postToVerifyLoginWithoutEmail(String password){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        return postWithFormData("/verifyLogin", formParams);
    }

    public Response postToCreateAccount(User user) {
        globalEmail  = user.getEmail();
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", user.getName());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("title", user.getTitle());
        formParams.put("birth_day", user.getBirthDay());
        formParams.put("birth_month", user.getBirthMonth());
        formParams.put("birth_year", user.getBirthYear());
        formParams.put("firstname", user.getFirstName());
        formParams.put("lastname", user.getLastName());
        formParams.put("company", user.getCompany());
        formParams.put("address1", user.getAddress1());
        formParams.put("country", user.getCountry());
        formParams.put("zipcode", user.getZipcode());
        formParams.put("state", user.getState());
        formParams.put("city", user.getCity());
        formParams.put("mobile_number", user.getMobileNumber());
        return postWithFormData("/createAccount", formParams);
    }

    public Response putToUpdateAccount(User user) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", user.getName());
        formParams.put("email", globalEmail);
        formParams.put("password", user.getPassword());
        formParams.put("title", user.getTitle());
        formParams.put("birth_day", user.getBirthDay());
        formParams.put("birth_month", user.getBirthMonth());
        formParams.put("birth_year", user.getBirthYear());
        formParams.put("firstname", user.getFirstName());
        formParams.put("lastname", user.getLastName());
        formParams.put("company", user.getCompany());
        formParams.put("address1", user.getAddress1());
        formParams.put("country", user.getCountry());
        formParams.put("zipcode", user.getZipcode());
        formParams.put("state", user.getState());
        formParams.put("city", user.getCity());
        formParams.put("mobile_number", user.getMobileNumber());

        return putWithFormData("/updateAccount", formParams);
    }

    public Response deleteUserAccount(User user){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        return deleteWithFormData("/deleteAccount", formParams);
    }

    public Response getUserDetailByEmail(String email){
        return getWithQueryParams("/getUserDetailByEmail", email);
    }
}