package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By accList = By.xpath("//ul[@class='oxd-main-menu']//span");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public String getHomePageTitle(){
        return driver.getTitle();
    }

    public List<String> getAccountList() {
        List<String> accListTextValue = new ArrayList<>();
        List<WebElement> accListElement = driver.findElements(accList);
        for (WebElement e : accListElement) {
            String accListText = e.getText();
            System.out.println(accListText);
            accListTextValue.add(accListText);
        }
        return accListTextValue;
    }

    public int getCountAccount(){
       return driver.findElements(accList).size();
    }
}
