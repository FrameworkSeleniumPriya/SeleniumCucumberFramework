package com.stepDefinition;

import com.epam.reportportal.service.ReportPortal;
import com.pages.LoginPage;
import com.factory.Driverfactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Date;

public class LoginPageSteps {
    private LoginPage loginPage = new LoginPage(Driverfactory.getDriver());

    private String actual;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        ReportPortal.emitLog("Test execution started", "INFO", new Date());
        Driverfactory.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        actual = loginPage.getLoginPageTitle();

    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expected) {
        Assert.assertEquals(actual, expected);
    }

    @Then("forgot your password link should be displayed")
    public void forgot_your_password_link_should_be_displayed() {
        Assert.assertTrue(loginPage.forgotPasswordDisplay());
    }

    @When("user enters username {string}")
    public void user_enters_username(String uname) {
        loginPage.enterUserName(uname);
    }

    @When("user enters password {string}")
    public void user_enters_password(String pwd) {
        loginPage.enterPassword(pwd);
    }

    @When("user clicks on Login button")
    public void user_clicks_on_login_button() {
        loginPage.clickLogin();
    }



}
