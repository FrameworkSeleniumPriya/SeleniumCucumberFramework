package com.pages;

import com.factory.Driverfactory;

import com.qa.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //by locators
    private By userName=By.name("username");
    private By password=By.name("password");
    private By loginButton=By.className("orangehrm-login-button");
    private By forgotPasswordLink =By.className("orangehrm-login-forgot");
    private WebDriver driver;
    private ElementUtils element=new ElementUtils(Driverfactory.getDriver());
    //constructor of class
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    //page actions should be public in nature
    public String getLoginPageTitle(){
        return driver.getTitle();
    }
    public boolean forgotPasswordDisplay(){
        return element.getElement(forgotPasswordLink).isDisplayed();
    }

    public void enterUserName(String uname){
        element.getElement(userName).sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        element.getElement(password).sendKeys(pwd);
    }

    public boolean loginButton() {
        return element.getElement(loginButton).isDisplayed();
    }

    public void clickLogin() {
        element.getElement(loginButton).click();
    }

    public HomePage goHome(String uname,String pwd) {
        Driverfactory.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        element.getElement(userName).sendKeys(uname);
        element.getElement(password).sendKeys(pwd);
        element.getElement(loginButton).click();
        return new HomePage(driver);
    }
}
