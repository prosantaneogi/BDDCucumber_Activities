package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Features",
    glue = {"stepDefinitions"},
    //tags = "not @activity1_1 and @activity1_2 and not @SmokeTests and not @ConfirmAlert and not @PromptAlert and @activity1_3",
    tags = "not @activity1_1 and not @activity1_2 and not @activity1_3 and not @SmokeTests and not @activity2_4 and @activity2_5",
    plugin = { "pretty", "html:target/cucumber-reports/reports" , "json: target/cucumber-reports/json-report.json"},
    monochrome = true
   
)

public class ActivitiesRunner {
    //empty
}