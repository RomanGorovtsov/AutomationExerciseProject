package api.config;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public interface ApiTestConfig {

    @BeforeAll
    static void setupAPILogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}