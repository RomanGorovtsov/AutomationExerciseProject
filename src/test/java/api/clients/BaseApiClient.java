package api.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseApiClient {

protected RequestSpecification request() {
    log.debug("Create base request");
    return RestAssured.given()
                .filter(new AllureRestAssured());
    }

    protected RequestSpecification formRequest() {
        log.debug("Create base request with form-data");
        return request().contentType("multipart/form-data");
    }

    protected Response get(String endpoint) {
        log.info("→ GET {}", endpoint);
        Response response = request().get(endpoint);
        logResponse(response);
        return response;
    }

    protected Response post(String endpoint) {
        log.info("→ POST {}", endpoint);
        Response response = request().post(endpoint);
        logResponse(response);
        return response;
    }

    protected Response delete(String endpoint) {
        log.info("→ DELETE {}", endpoint);
        Response response = request().delete(endpoint);
        logResponse(response);
        return response;
    }

    protected Response postWithFormData(String endpoint, Map<String, String> formParams) {
        log.info("→ POST {} with form data: {}", endpoint, formParams);
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        Response response = request.post(endpoint);
        logResponse(response);
        return response;
    }

    protected Response putWithFormData(String endpoint, Map<String, String> formParams) {
        log.info("→ PUT {} with form data: {}", endpoint, formParams);
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        Response response = request.put(endpoint);
        logResponse(response);
        return response;
    }

    protected Response deleteWithFormData(String endpoint, Map<String, String> formParams){
        log.info("→ DELETE {} with form data: {}", endpoint, formParams);
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        Response response = request.delete(endpoint);
        logResponse(response);
        return response;
    }

    protected Response getWithQueryParams(String endpoint, String email) {
        log.info("→ GET {}?email={}", endpoint, email);
        Response response = RestAssured.given()
                .queryParam("email", email)
                .get(endpoint);
        logResponse(response);
        return response;
    }

    private void logResponse(Response response) {
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        log.debug("← Response status: {}", statusCode);
        if (statusCode >= 400) {
            log.warn("← Error response body: {}", responseBody);
        } else {
            log.debug("← Response body: {}", responseBody);
        }
    }

    public static void assertMethodNotSupported(Response response) {
        assertEquals(405, response.jsonPath().getInt("responseCode"));
        assertEquals("This request method is not supported.", response.jsonPath().getString("message"));
    }

}