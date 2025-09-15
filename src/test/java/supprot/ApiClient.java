package supprot;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {
    public ApiClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public Response get(String path, Map<String, ?> query, Map<String, String> headers) {
        return given().headers(headers).queryParams(query == null ? Map.of() : query).when().get(path);
    }

    public Response post(String path, String json, Map<String, String> headers) {
        return given().headers(headers).contentType("application/json").body(json).when().post(path);
    }

    public Response put(String path, String json, Map<String, String> headers) {
        return given().headers(headers).contentType("application/json").body(json).when().put(path);
    }

    public Response patch(String path, String json, Map<String, String> headers) {
        return given().headers(headers).contentType("application/json").body(json).when().patch(path);
    }

    public Response delete(String path, Map<String, String> headers) {
        return given().headers(headers).when().delete(path);
    }
}
