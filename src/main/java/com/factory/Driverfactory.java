package com.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;


import java.util.concurrent.TimeUnit;

public class Driverfactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize a webdriver
     * @param browser-choose the type of browser
     * @return driver
     */

    public WebDriver initDriver(String browser){
        ChromeOptions options = new ChromeOptions();
        // Ensure headless is NOT set
        options.setHeadless(false);
        browser=System.getProperty("browser","chrome");
        System.out.println("Browser value is:"+browser);
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(options));

        }
        else if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return getDriver();
    }

    public static synchronized
    WebDriver getDriver(){
        return tlDriver.get();
    }
}
