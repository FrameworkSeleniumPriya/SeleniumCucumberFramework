package com.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    private final WebDriver driver;

    public ElementUtils(WebDriver driver){
        this.driver=driver;
    }
    public WebElement getElement(By locator){

        return driver.findElement(locator);

    }
}
