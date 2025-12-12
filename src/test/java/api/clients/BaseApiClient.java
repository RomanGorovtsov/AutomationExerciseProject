package api.clients;

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseApiClient {

    public static void assertMethodNotSupported(Response response) {
        assertEquals(405, response.jsonPath().getInt("responseCode"));
        assertEquals("This request method is not supported.", response.jsonPath().getString("message"));
    }

    public static void assertBadRequest400(Response response){
        assertEquals(400, response.jsonPath().getInt("responseCode"));
    }

    public static void assertSuccess200(Response response) {
        assertEquals(200, response.jsonPath().getInt("responseCode"));
    }


}