package api.tests;

import api.clients.ProductsClient;
import api.models.Brand;
import api.models.Product;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import java.util.List;

import static api.clients.BaseApiClient.assertMethodNotSupported;
import static org.junit.jupiter.api.Assertions.*;

public class ProductsApiTest {
    private final ProductsClient productsClient = new ProductsClient();

    @Test()
    @Tag("api")
    @DisplayName("GET запрос на получение списка всех продуктов /productsList")
    public void testGetAllProducts() {
        Response response = productsClient.getAllProducts();
        assertEquals(200, response.jsonPath().getInt("responseCode"));

        List<Product> products = response.jsonPath().getList("products", Product.class);
        assertTrue(products.stream().allMatch(product ->
                product.getId() > 0 &&
                        product.getName() != null &&
                        product.getPrice() != null &&
                        product.getBrand() != null &&
                        product.getCategory() != null
        ));
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на GET-эндпоинт /productsList")
    public void testPostToAllProductList() {
        Response response = productsClient.postToAllProducts();
        assertMethodNotSupported(response);
    }

    @Test()
    @Tag("api")
    @DisplayName("GET запрос на получение всех брендов")
    public void testGetAllBrands() {
        Response response = productsClient.getAllBrandsList();
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        List<Brand> brands = response.jsonPath().getList("brands", Brand.class);
        assertTrue(brands.stream().allMatch(brand -> brand.getId() > 0
                && brand.getBrand() != null));
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
    @DisplayName("POST запрос с параметром на поиск продуктов /searchProduct")
    public void testSearchProductsWithFormData() {
        Response response = productsClient.searchProductWithFormatData("top");
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        List<Object> products = response.jsonPath().getList("products");
        assertFalse(products.isEmpty());
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
    @DisplayName("POST запрос на валидацию логина с валидными имейлом и паролем /verifyLogin")
    public void testPostToVerifyLogin() {
        Response response = productsClient.postToVerifyLoginWithValidData("walterwhite1958@gmail.com", "breakingbad");
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("User exists!", response.jsonPath().getString("message"));
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
        Response response = productsClient.postToVerifyLoginWithValidData("invalidlogin@gmail.com", "invalidpassword");
        assertEquals(404, response.jsonPath().getInt("responseCode"));
        assertEquals("User not found!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на создание аккаунта /createAccount")
    public void testPostToCreateOrRegisterUserAccount() {
        Response response = productsClient.postToCreateAccount("Jack", "jack", "1234", "Mr", "20", "April", "2003", "Jack", "Grealish", "MC", "Manchester", "England", "UK", "101000", "ENG", "Manchester", "+1239082987");
        assertEquals(201, response.jsonPath().getInt("responseCode"));
        assertEquals("User created!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("PUT запрос на обновление аккаунта /updateAccount")
    public void testPutToUpdateUserAccount() {
        productsClient.postToCreateAccount("Jack", "jack", "1234", "Mr", "20", "April", "2003", "Jack", "Grealish", "MC", "Manchester", "England", "UK", "101000", "ENG", "Manchester", "+1239082987");
        Response response = productsClient.putToUpdateAccount("Jack", "jack", "1234", "Mr", "20", "April", "2003", "Jack", "Grealish", "MC", "Manchester", "England", "UK", "101000", "ENG", "Manchester", "+1239082987");
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("User updated!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("DELETE запрос на удаление аккаунта /deleteAccount")
    public void testDeleteUserAccount() {
        productsClient.postToCreateAccount("Jack", "jack", "1234", "Mr", "20", "April", "2003", "Jack", "Grealish", "MC", "Manchester", "England", "UK", "101000", "ENG", "Manchester", "+1239082987");
        Response response = productsClient.deleteUserAccount("jack", "1234");
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("Account deleted!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("GET запрос на получение деталей пользователя /getUserDetailByEmail")
    public void testGetUserDetailByEmail() {
        productsClient.postToCreateAccount("Jack", "jack", "1234", "Mr", "20", "", "2003", "Jack", "Grealish", "MC", "Manchester", "", "UK", "101000", "ENG", "Manchester", "+1239082987");
        Response response = productsClient.getUserDetailByEmail();
        assertEquals(200, response.jsonPath().getInt("responseCode"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals("Jack", jsonPath.getString("user.name"));
        assertEquals(productsClient.getGlobalEmail(), jsonPath.getString("user.email"));
        assertEquals("Manchester", jsonPath.getString("user.city"));
        assertEquals("UK", jsonPath.getString("user.country"));
    }

}