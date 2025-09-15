package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import supprot.ApiClient;
import supprot.TestContext;


import java.io.InputStream;
import java.util.Properties;

public class Hooks {
    public static ApiClient client;
    public static TestContext ctx;

    @Before(order = 0)
    public void setup() throws Exception {
        Properties p = new Properties();
        try (InputStream is = Hooks.class.getResourceAsStream("/config.properties")) {
            p.load(is);
        }
        client = new ApiClient(p.getProperty("baseUrl"));
        ctx = new TestContext();
        ctx.headers().put("x-api-key", p.getProperty("apiKey"));
    }

    @After
    public void teardown() {
        // place for cleanup/logging if needed
    }
}
