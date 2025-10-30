package api.tests;

import api.clients.ProductsClient;
import api.generators.UserGenerator;
import api.models.Brand;
import api.models.Product;
import api.models.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApiPositiveTests {

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
    @DisplayName("POST запрос с параметром на поиск продуктов /searchProduct")
    public void testSearchProductsWithFormData() {
        Response response = productsClient.searchProductWithFormatData("top");
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        List<Object> products = response.jsonPath().getList("products");
        assertFalse(products.isEmpty());
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на валидацию логина с валидными имейлом и паролем /verifyLogin")
    public void testPostToVerifyLogin() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.postToVerifyLoginWithEmailAndPassword(user);
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("User exists!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("POST запрос на создание аккаунта /createAccount")
    public void testPostToCreateOrRegisterUserAccount() {
        User user = UserGenerator.generateRandomUser();
        Response response = productsClient.postToCreateAccount(user);
        assertEquals(201, response.jsonPath().getInt("responseCode"));
        assertEquals("User created!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("PUT запрос на обновление аккаунта /updateAccount")
    public void testPutToUpdateUserAccount() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.putToUpdateAccount(user);
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("User updated!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("DELETE запрос на удаление аккаунта /deleteAccount")
    public void testDeleteUserAccount() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.deleteUserAccount(user);
        assertEquals(200, response.jsonPath().getInt("responseCode"));
        assertEquals("Account deleted!", response.jsonPath().getString("message"));
    }

    @Test
    @Tag("api")
    @DisplayName("GET запрос на получение деталей пользователя /getUserDetailByEmail")
    public void testGetUserDetailByEmail() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.getUserDetailByEmail(user.getEmail());
        assertEquals(200, response.jsonPath().getInt("responseCode"));

        ResponseBody body = response.getBody();
        assertEquals(user.getName(), body.jsonPath().getString("user.name"));
        assertEquals(user.getEmail(), body.jsonPath().getString("user.email"));
        assertEquals(user.getCountry(), body.jsonPath().getString("user.country"));

    }

}
