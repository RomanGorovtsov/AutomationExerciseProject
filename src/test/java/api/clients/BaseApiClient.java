package api.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseApiClient {
    protected static final String BASE_URL = "https://automationexercise.com/api";

    //шаблон обычного запроса
    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .filter(new AllureRestAssured())
                .log().all();
    }

    //шаблон запроса с form-data
    protected RequestSpecification formRequest() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("multipart/form-data")
                .filter(new AllureRestAssured())
                .log().all();
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

    //отправка post с несколькими параметрами form-data
    protected Response postWithFormData(String endpoint, Map<String, String> formParams) {
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.post(endpoint);
    }

    //отправка put с несколькими параметрами form-data
    protected Response putWithFormData(String endpoint, Map<String, String> formParams) {
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.put(endpoint);
    }

    //отправка delete с несколькими параметрами form-data
    protected Response deleteWithFormData(String endpoint, Map<String, String> formParams){
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request.delete(endpoint);
    }

    //отправка get с query params
    protected Response getWithQueryParams(String endpoint, String email) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("email", email)
                .log().all()
                .get(endpoint);
    }

    public static void assertMethodNotSupported(Response response) {
        assertEquals(405, response.jsonPath().getInt("responseCode"));
        assertEquals("This request method is not supported.", response.jsonPath().getString("message"));
    }

}