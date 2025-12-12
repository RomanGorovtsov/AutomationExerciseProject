package api.clients;

import api.models.User;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static api.utils.ApiUtils.*;

@Slf4j
public class ProductsClient {

    private static final String PRODUCTS_LIST = "/productsList";
    private static final String BRANDS_LIST = "/brandsList";
    private static final String SEARCH_PRODUCT = "/searchProduct";
    private static final String VERIFY_LOGIN = "/verifyLogin";
    private static final String CREATE_ACCOUNT = "/createAccount";
    private static final String UPDATE_ACCOUNT = "/updateAccount";
    private static final String DELETE_ACCOUNT = "/deleteAccount";
    private static final String USER_DETAIL_BY_EMAIL = "/getUserDetailByEmail";

    public Response getAllProducts() {
        log.info("Getting all products from {}", PRODUCTS_LIST);
        return get(PRODUCTS_LIST);
    }

    public Response getAllBrandsList() {
        log.info("Getting all brands from {}", BRANDS_LIST);
        return get(BRANDS_LIST);
    }

    public Response getUserDetailByEmail(String email) {
        log.info("Getting user details by email: {}", email);
        return get(USER_DETAIL_BY_EMAIL, email);
    }

    public Response postToAllProducts() {
        log.warn("Making POST request to {} (should be GET according to REST)", PRODUCTS_LIST);
        return post(PRODUCTS_LIST);
    }

    public Response postToVerifyLoginWithEmailAndPassword(User user) {
        log.info("Verifying login for user: {}", user.getEmail());
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        return post(VERIFY_LOGIN, formParams);
    }

    public Response postToSearchProductsWithoutParams() {
        log.info("Searching products without parameters");
        return post(SEARCH_PRODUCT);
    }

    public Response postToVerifyLoginWithInvalidEmailAndPassword(String invalidEmail, String invalidPassword) {
        log.info("Testing login with invalid credentials");
        Map<String, String> formParams = new HashMap<>();
        String generatedEmail = invalidEmail + System.currentTimeMillis() + "@yahoo.com";
        formParams.put("email", generatedEmail);
        formParams.put("password", invalidPassword);
        log.debug("Invalid login attempt with email: {}", generatedEmail);
        return post(VERIFY_LOGIN, formParams);
    }

    public Response postToVerifyLoginWithoutEmail(String password) {
        log.info("Testing login without email (only password)");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("password", password);
        log.debug("Login without email");
        return post(VERIFY_LOGIN, formParams);
    }

    public Response postToCreateAccount(User user) {
        log.info("Creating account for user: {}", user.getName());
        Map<String, String> formParams = user.convertUserDataToMap();
        log.debug("User data for registration: {}", formParams);
        return post(CREATE_ACCOUNT, formParams);
    }

    public Response putBrandsList() {
        log.warn("Making PUT request to {} (should be GET according to REST)", BRANDS_LIST);
        return put(BRANDS_LIST);
    }

    public Response putToUpdateAccount(User user) {
        log.info("Updating account for user: {}", user.getEmail());
        Map<String, String> formParams = user.convertUserDataToMap();
        log.debug("User data for update (password hidden): {}", formParams);
        return put(UPDATE_ACCOUNT, formParams);
    }

    public Response deleteVerifyLogin() {
        log.warn("Making DELETE request to {} (should be POST)", VERIFY_LOGIN);
        return delete(VERIFY_LOGIN);
    }

    public Response deleteUserAccount(User user) {
        log.info("Deleting account for user: {}", user.getEmail());
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        log.debug("Account deletion params: {}", formParams);
        return delete(DELETE_ACCOUNT, formParams);
    }

    public Response searchProductWithFormatData(String searchTerm) {
        log.info("Searching products with term: '{}'", searchTerm);
        Map<String, String> formParams = new HashMap<>();
        formParams.put("search_product", searchTerm);
        return post(SEARCH_PRODUCT, formParams);
    }
}