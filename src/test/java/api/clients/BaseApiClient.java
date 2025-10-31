package api.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseApiClient {
    protected static final String BASE_URL = "https://automationexercise.com/api";

    @BeforeAll
    static void setupAPILogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .filter(new AllureRestAssured());
    }

    protected RequestSpecification formRequest() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("multipart/form-data")
                .filter(new AllureRestAssured());
    }

    protected Response get(String endpoint) {
        return request().get(endpoint);
    }

    protected Response post(String endpoint) {
        return request().post(endpoint);
    }

    protected Response delete(String endpoint) {
        return request().delete(endpoint);
    }

    protected Response postWithFormData(String endpoint, Map<String, String> formParams) {
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.post(endpoint);
    }

    protected Response putWithFormData(String endpoint, Map<String, String> formParams) {
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.put(endpoint);
    }

    protected Response deleteWithFormData(String endpoint, Map<String, String> formParams){
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.delete(endpoint);
    }

    protected Response getWithQueryParams(String endpoint, String email) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("email", email)
                .get(endpoint);
    }

    public static void assertMethodNotSupported(Response response) {
        assertEquals(405, response.jsonPath().getInt("responseCode"));
        assertEquals("This request method is not supported.", response.jsonPath().getString("message"));
    }

}