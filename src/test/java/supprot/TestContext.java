package supprot;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private final Map<String, String> headers = new HashMap<>();
    private Response lastResponse;
    private String lastRequestBody;

    public Map<String, String> headers() { return headers; }
    public Response response() { return lastResponse; }
    public void response(Response r) { this.lastResponse = r; }
    public String body() { return lastRequestBody; }
    public void body(String b) { this.lastRequestBody = b; }
}
