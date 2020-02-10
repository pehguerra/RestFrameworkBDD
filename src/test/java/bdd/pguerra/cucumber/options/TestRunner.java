package bdd.pguerra.cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src/test/resources/location"}, 
		glue={"bdd.pguerra.stepDefinitions"},
		tags = {"@regression"},
		plugin = {"pretty",
//                "html:target/report/html",
//                "junit:target/report/junit/cucumber-report.xml",
                "json:target/jsonReports/cucumber-report.json"
        }
)
public class TestRunner {

}
