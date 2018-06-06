package definitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        tags = {"@test", "~@ignore"},
        plugin = {"pretty", "html:target/cucumber-reports", "junit:target/cucumber-reports/Cucumber.xml"},
        monochrome = true
)

public class RunCukes {

}