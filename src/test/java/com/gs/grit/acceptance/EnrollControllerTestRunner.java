package com.gs.grit.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.gs.grit.acceptance",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class EnrollControllerTestRunner {
}
