package api.clients;

import api.models.User;
import io.restassured.response.Response;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ProductsClient extends BaseApiClient {

    String baseUrl;

    public ProductsClient() {
        this.baseUrl = getBaseUrlFromProperties();
    }

    private String getBaseUrlFromProperties() {
        Properties prop = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            return prop.getProperty("base.url");
        } catch (Exception e) {
            return "https://google.com/api";
        }
    }

    private static final String PRODUCTS_LIST = "/productsList";
    private static final String BRANDS_LIST = "/brandsList";
    private static final String SEARCH_PRODUCT = "/searchProduct";
    private static final String VERIFY_LOGIN = "/verifyLogin";
    private static final String CREATE_ACCOUNT = "/createAccount";
    private static final String UPDATE_ACCOUNT = "/updateAccount";
    private static final String DELETE_ACCOUNT = "/deleteAccount";
    private static final String USER_DETAIL_BY_EMAIL = "/getUserDetailByEmail";

    public Response getAllProducts() {
        return get(getFullEndPoint(PRODUCTS_LIST));
    }

    public Response getAllBrandsList(){
        return get(getFullEndPoint(BRANDS_LIST));
    }

    public Response postToAllProducts(){
        return post(getFullEndPoint(PRODUCTS_LIST));
    }

    public Response postToSearchProductsWithoutParams(){
        return post(getFullEndPoint(SEARCH_PRODUCT));
    }

    public Response putBrandsList(){
        return post(getFullEndPoint(BRANDS_LIST));
    }

    public Response deleteVerifyLogin(){
        return delete(getFullEndPoint(VERIFY_LOGIN));
    }

    public Response searchProductWithFormatData(String searchTerm) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("search_product", searchTerm);
        return postWithFormData(getFullEndPoint(SEARCH_PRODUCT), formParams);
    }

    public Response postToVerifyLoginWithEmailAndPassword(User user){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        return postWithFormData(getFullEndPoint(VERIFY_LOGIN), formParams);
    }

    public Response postToVerifyLoginWithInvalidEmailAndPassword(String invalidEmail, String invalidPassword){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", invalidEmail + System.currentTimeMillis() + "@yahoo.com");
        formParams.put("password", invalidPassword);
        return postWithFormData(getFullEndPoint(VERIFY_LOGIN), formParams);
    }

    public Response postToVerifyLoginWithoutEmail(String password){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        return postWithFormData(getFullEndPoint(VERIFY_LOGIN), formParams);
    }

    public Response postToCreateAccount(User user) {
        Map<String, String> formParams = user.toFormParamsMapWithFullUserData();
        return postWithFormData(getFullEndPoint(CREATE_ACCOUNT), formParams);
    }

    public Response putToUpdateAccount(User user) {
        Map<String, String> formParams = user.toFormParamsMapWithFullUserData();
        return putWithFormData(getFullEndPoint(UPDATE_ACCOUNT), formParams);
    }

    public Response deleteUserAccount(User user){
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        return deleteWithFormData(getFullEndPoint(DELETE_ACCOUNT), formParams);
    }

    public Response getUserDetailByEmail(String email){
        return getWithQueryParams(getFullEndPoint(USER_DETAIL_BY_EMAIL), email);
    }

    private String getFullEndPoint(String endpoint){
        return "%s%s".formatted(baseUrl,endpoint);
    }
}