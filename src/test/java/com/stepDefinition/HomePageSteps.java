package com.stepDefinition;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.factory.Driverfactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class HomePageSteps {
    private LoginPage loginPage=new LoginPage(Driverfactory.getDriver());
    private HomePage homePage;
    //In this the control moves to the next page so it should return next page objcet as per POM
    @Given("user has already logged in to application")
    public void home_Page_login_method(DataTable credentials){
        //This will store it ina list of maps
        List<Map<String,String>> cred=credentials.asMaps();
        String uname=cred.get(0).get("username");
        String pwd=cred.get(0).get("password");
        homePage=loginPage.goHome(uname,pwd);
    }

    @Given("user is on home page")
    public void user_is_on_homepage_method(){
       System.out.println(homePage.getHomePageTitle());

    }
    @Then("user gets accounts section")
    public void user_is_on_homepage_accounts(DataTable accountsSection) {
        List<String> expected = accountsSection.asList();
        List<String> actual= homePage.getAccountList();
        Assert.assertTrue(expected.containsAll(actual));
    }

    @Then("accounts section count should be {int}")
    public void account_selection_count(int count) {
        Assert.assertEquals(count, homePage.getCountAccount());

    }
}
