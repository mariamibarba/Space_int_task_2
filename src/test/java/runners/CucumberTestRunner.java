// src/test/java/runners/CucumberTestRunner.java
package runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // looks under classpath: features => src/test/resources/features
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,
        value = "steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, summary, html:target/cucumber.html, json:target/cucumber.json")
public class CucumberTestRunner { }
