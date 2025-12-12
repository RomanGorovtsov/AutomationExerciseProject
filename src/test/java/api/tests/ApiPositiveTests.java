package api.tests;

import api.clients.BaseApiClient;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("api")
@Tag("positive")
public class ApiPositiveTests extends BaseApiClient {

    private final ProductsClient productsClient = new ProductsClient();

    @Test()
    @DisplayName("Проверка GET запроса на получение списка всех продуктов /productsList")
    public void testGetAllProducts() {
        Response response = productsClient.getAllProducts();
        assertSuccess200(response);

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
    @DisplayName("Проверка GET запроса на получение всех брендов /brandsList")
    public void testGetAllBrands() {
        Response response = productsClient.getAllBrandsList();
        assertSuccess200(response);
        List<Brand> brands = response.jsonPath().getList("brands", Brand.class);
        assertTrue(brands.stream().allMatch(brand -> brand.getId() > 0
                && brand.getBrand() != null));
    }

    @Test
    @DisplayName("Проверка POST запроса с параметром на поиск продуктов /searchProduct")
    public void testSearchProductsWithFormData() {
        Response response = productsClient.searchProductWithFormatData("top");
        assertSuccess200(response);
        List<Object> products = response.jsonPath().getList("products");
        assertFalse(products.isEmpty());
    }

    @Test
    @DisplayName("Проверка POST запроса на валидацию логина с валидными имейлом и паролем /verifyLogin")
    public void testPostToVerifyLogin() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.postToVerifyLoginWithEmailAndPassword(user);
        assertSuccess200(response);
        assertEquals("User exists!", response.jsonPath().getString("message"));
        productsClient.deleteUserAccount(user);
    }

    @Test
    @DisplayName("Проверка POST запроса на создание аккаунта /createAccount")
    public void testPostToCreateOrRegisterUserAccount() {
        User user = UserGenerator.generateRandomUser();
        Response response = productsClient.postToCreateAccount(user);
        assertEquals(201, response.jsonPath().getInt("responseCode"));
        assertEquals("User created!", response.jsonPath().getString("message"));
        productsClient.deleteUserAccount(user);
    }

    @Test
    @DisplayName("Проверка PUT запроса на обновление аккаунта /updateAccount")
    public void testPutToUpdateUserAccount() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.putToUpdateAccount(user);
        assertSuccess200(response);
        assertEquals("User updated!", response.jsonPath().getString("message"));
        productsClient.deleteUserAccount(user);
    }

    @Test
    @DisplayName("Проверка DELETE запроса на удаление аккаунта /deleteAccount")
    public void testDeleteUserAccount() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.deleteUserAccount(user);
        assertSuccess200(response);
        assertEquals("Account deleted!", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка GET запроса на получение деталей пользователя /getUserDetailByEmail")
    public void testGetUserDetailByEmail() {
        User user = UserGenerator.generateRandomUser();
        productsClient.postToCreateAccount(user);
        Response response = productsClient.getUserDetailByEmail(user.getEmail());
        assertSuccess200(response);

        ResponseBody body = response.getBody();
        assertEquals(user.getName(), body.jsonPath().getString("user.name"));
        assertEquals(user.getEmail(), body.jsonPath().getString("user.email"));
        assertEquals(user.getCountry(), body.jsonPath().getString("user.country"));
    }
}
