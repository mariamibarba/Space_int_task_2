package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import supprot.ApiClient;
import supprot.TestContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersSteps {
    private final ApiClient client = Hooks.client;
    private final TestContext ctx = Hooks.ctx;

    @When("I send GET {string}")
    public void iSendGet(String path) {
        Response r = client.get(path, Map.of(), ctx.headers());
        ctx.response(r);
    }

    @When("I send GET {string} with query params:")
    public void iSendGetWithQuery(String path, Map<String, String> q) {
        Response r = client.get(path, new HashMap<>(q), ctx.headers());
        ctx.response(r);
    }

    @When("I send POST {string}")
    public void iSendPost(String path) {
        Response r = client.post(path, ctx.body(), ctx.headers());
        ctx.response(r);
    }

    @When("I send PUT {string}")
    public void iSendPut(String path) {
        Response r = client.put(path, ctx.body(), ctx.headers());
        ctx.response(r);
    }

    @When("I send PATCH {string}")
    public void iSendPatch(String path) {
        Response r = client.patch(path, ctx.body(), ctx.headers());
        ctx.response(r);
    }

    @When("I send DELETE {string}")
    public void iSendDelete(String path) {
        Response r = client.delete(path, ctx.headers());
        ctx.response(r);
    }

    @Then("the response status should be {int}")
    public void assertStatus(int code) {
        ctx.response().then().statusCode(code);
    }

    @Then("the response header {string} should contain {string}")
    public void assertHeaderContains(String name, String part) {
        String hv = ctx.response().getHeader(name);
        assertThat("Missing header: " + name, hv, notNullValue());
        assertThat(hv, containsString(part));
    }

    @Then("the response body path {string} should equal {int}")
    public void assertBodyPathInt(String path, int value) {
        ctx.response().then().body(path, equalTo(value));
    }

    @Then("the response body path {string} should equal {string}")
    public void assertBodyPathStr(String path, String value) {
        ctx.response().then().body(path, equalTo(value));
    }

    @Then("the response body path {string} should contain {string}")
    public void assertBodyPathContains(String path, String part) {
        ctx.response().then().body(path, containsString(part));
    }

    @Then("the response body should have keys:")
    public void assertBodyHasKeys(java.util.List<String> keys) {
        for (String k : keys) {
            ctx.response().then().body("$", hasKey(k));
        }
    }

    @Then("the response body path {string} should be > {int}")
    public void assertBodyPathGreaterThan(String path, int n) {
        ctx.response().then().body(path, (Matcher) greaterThan(n));
    }
}
