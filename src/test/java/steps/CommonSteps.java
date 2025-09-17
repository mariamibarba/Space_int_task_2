package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import steps.Hooks;
import supprot.TestContext;

public class CommonSteps {
    private final TestContext ctx = Hooks.ctx;

    @Given("I set header {string} to {string}")
    public void ISetHeader(String key, String value) {
        ctx.headers().put(key, value);
    }

    @Given("the API base URL is configured")
    public void BaseConfigured() {
        // no-op, handled in Hooks
    }

    @Given("the request JSON body:")
    public void TheRequestJsonBody(String docString) {
        ctx.body(docString);
    }

    @And("the response body should be empty")
    public void ResponseBodyEmpty() {
        String content = ctx.response().getBody().asString();
        if (content != null) {
            String trimmed = content.trim();
            assert trimmed.isEmpty() || "{}".equals(trimmed) : "Expected empty body but got: " + trimmed;
        }
    }
}
