package definitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        tags = {"@test", "~@ignore"},
        glue = {"core","definitions"},
        plugin = {"pretty", "json:target/cucumber/report.json","html:target/cucumber", "junit:target/cucumber/cucumber.xml"},
        monochrome = true
)

public class RunTest {

}