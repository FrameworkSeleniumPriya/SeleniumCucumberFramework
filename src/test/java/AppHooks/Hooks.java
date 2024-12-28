package AppHooks;

import com.epam.reportportal.service.ReportPortal;
import com.factory.Driverfactory;
import com.qa.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.Properties;
public class Hooks {
    private WebDriver driver;
    private Properties prop;
    private Driverfactory init_Driver;
    private ConfigReader config;

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        // Log to Report Portal before starting each scenario
        ReportPortal.emitLog("Starting scenario: " + scenario.getName(), "INFO", new Date());
    }
    @Before(order = 1)
    public void getProperties() {
        config = new ConfigReader();
        prop = config.initProp();
    }

    @Before(order = 2)
    public void getDriver() {
        init_Driver = new Driverfactory();
        String driverName = prop.getProperty("browser");
        driver =init_Driver.initDriver(driverName);
    }
 @After(order=0)
    public void quitBrowser(){
        driver.quit();
        ReportPortal.emitLog("End scenario: ", "INFO", new Date());
 }

 @After(order=1)
        public void tearDown(Scenario sc) {
        String screenshotName=sc.getName().replaceAll(" ","_");
        if(sc.isFailed()){
           byte[] sourcePath= ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
           sc.attach(sourcePath,"images/png",screenshotName);

     }
 }
}
