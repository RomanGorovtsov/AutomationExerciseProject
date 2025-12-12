package api.utils;

import api.config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import static io.restassured.RestAssured.given;


@Slf4j
public class ApiUtils {

    private static final AllureRestAssured ALLURE_FILTER = new AllureRestAssured();
    private static final ApiConfig CONFIG = new ApiConfig();

    private static String getBaseUrl() {
        return CONFIG.getUrlFromProperties();
    }

    public static RequestSpecification request() {
        log.debug("Create base request");
        return given()
                .filter(ALLURE_FILTER)
                .baseUri(getBaseUrl());
    }

    public static RequestSpecification formRequest() {
        log.debug("Create base request with form-data");
        return request().contentType("multipart/form-data");
    }

    public static RequestSpecification prepareFormDataRequest(Map<String, String> formParams) {
        RequestSpecification request = formRequest();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request = request.multiPart(entry.getKey(), entry.getValue());
        }
        return request;
    }

    public static Response get(String endpoint) {
        log.info("→ GET {}", endpoint);
        Response response = request().get(endpoint);
        logResponse(response);
        return response;
    }

    public static Response get(String endpoint, String email) {
        String url = buildFullUrl(endpoint);
        log.info("→ GET {}?email={}", url, email);
        log.debug("Query param - email: {}", email);
        Response response = request()
                .queryParam("email", email)
                .get(endpoint);
        logResponse(response);
        return response;
    }

    public static Response post(String endpoint) {
        log.info("→ POST {}", endpoint);
        Response response = request().post(endpoint);
        logResponse(response);
        return response;
    }

    public static Response post(String endpoint, Map<String, String> formParams) {
        log.info("→ POST {} with form data: {}", endpoint, formParams);
        Response response = prepareFormDataRequest(formParams).post(endpoint);
        logResponse(response);
        return response;
    }

    public static Response put(String endpoint) {
        log.info("→ PUT: {}", endpoint);
        Response response = request().put(endpoint);
        logResponse(response);
        return response;
    }

    public static Response put(String endpoint, Map<String, String> formParams) {
        log.info("→ PUT {} with form data: {}", endpoint, formParams);
        Response response = prepareFormDataRequest(formParams).put(endpoint);
        logResponse(response);
        return response;
    }

    public static Response delete(String endpoint) {
        log.info("→ DELETE {}", endpoint);
        Response response = request().delete(endpoint);
        logResponse(response);
        return response;
    }

    public static Response delete(String endpoint, Map<String, String> formParams){
        log.info("→ DELETE {} with form data: {}", endpoint, formParams);
        Response response = prepareFormDataRequest(formParams).delete(endpoint);
        logResponse(response);
        return response;
    }

    public static void logResponse(Response response) {
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        log.debug("← Response status: {}", statusCode);
        if (statusCode >= 400) {
            log.warn("← Error response body: {}", responseBody);
        } else {
            log.debug("← Response body: {}", responseBody);
        }
    }

    public static String buildFullUrl(String endPoint){
        String fullUrl = getBaseUrl() + endPoint;
        log.trace("Built full URL: {}", fullUrl);
        return fullUrl;
    }
}