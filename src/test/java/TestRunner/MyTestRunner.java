package TestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/appFeatures", glue = {"com.stepDefinition", "AppHooks"},tags="@smoke",
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        })
public class MyTestRunner extends AbstractTestNGCucumberTests {
}
