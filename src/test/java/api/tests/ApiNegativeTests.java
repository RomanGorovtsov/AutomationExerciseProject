package api.tests;

import api.clients.BaseApiClient;
import api.clients.ProductsClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiNegativeTests extends BaseApiClient {
    private final ProductsClient productsClient = new ProductsClient();

    @Test
    @Tag("api")
    @DisplayName("POST запрос на GET-эндпоинт /productsList")
    public void testPostToAllProductList() {
        Response response = productsClient.postToAllProducts();
        assertMethodNotSupported(response);
    }

    @Test
    @Tag("api")
    @DisplayName("PUT запрос на GET-эндпоинт /brandsList")
    public void testPutToBrandList() {
        Response response = productsClient.putBrandsList();
        assertMethodNotSupported(response);
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос без параметра поиска /searchProduct")
    public void testSearchProductsWithoutFormData() {
        Response response = productsClient.postToSearchProductsWithoutParams();
        assertEquals(400, response.jsonPath().getInt("responseCode"));
        assertEquals("Bad request, search_product parameter is missing in POST request.", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на валидацию логина без имейла /verifyLogin")
    public void testPostToVerifyLoginWithoutEmailParameter() {
        Response response = productsClient.postToVerifyLoginWithoutEmail("breakingbad");
        assertEquals(400, response.jsonPath().getInt("responseCode"));
        assertEquals("Bad request, email or password parameter is missing in POST request.", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("DELETE запрос на POST-эндроинт /toVerifyLogin")
    public void testDeleteToVerifyLogin() {
        Response response = productsClient.deleteVerifyLogin();
        assertMethodNotSupported(response);
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на валидацию логина с несуществующим имейлом и паролем /verifyLogin")
    public void testPostToVerifyLoginWithInvalidValues() {
        Response response = productsClient.postToVerifyLoginWithInvalidEmailAndPassword("invalidlogin", "invalidpassword");
        assertEquals(404, response.jsonPath().getInt("responseCode"));
        assertEquals("User not found!", response.jsonPath().getString("message"));
    }









}