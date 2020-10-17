package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Features",
    glue = {"stepDefinitions"},
    //tags = "not @activity1_1 and @activity1_2 and not @SmokeTests and not @ConfirmAlert and not @PromptAlert and @activity1_3",
    //tags = "not @activity1_1 and not @activity1_2 and not @activity1_3 and @SmokeTest and not @activity2_4 and not @activity2_5",
    		//tags = "@SimleAlert and @SmokeTests",
    tags = "@ProjAct_4",
    plugin = { "pretty", "html:target/cucumber-reports/reports" , "json: target/cucumber-reports/json-report.json"},
    monochrome = true,
    publish = true
   
)

public class ActivitiesRunner {
	
}