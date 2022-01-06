package com.bookIt.stepDefinitions;

import com.bookIt.pages.LogInPages;
import com.bookIt.pages.SelfPage;
import com.bookIt.utils.ConfigurationReader;
import com.bookIt.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UIStep_defs {

    LogInPages logInPages =new LogInPages();
    SelfPage selfPage = new SelfPage();

    public static String UiFullName;
    public static String UiRole;
    public static String UiTeam;
    public static String batch;
    public static String campus;

    @Given("User logs in with {string} and {string}")
    public void user_logs_in_with_and(String username, String password) {
        Driver.get().get(ConfigurationReader.get("url"));
        logInPages.logIn(username,password);


    }

    @When("User navigates to mySelf page")
    public void user_navigates_to_mySelf_page() {
        logInPages.myLink.click();
        logInPages.selfLink.click();
    }

    @Then("User gets the UI info")
    public void user_gets_the_UI_info() {
        UiFullName = selfPage.name.getText();
        UiRole = selfPage.role.getText();
        batch = selfPage.batch.getText();
        campus = selfPage.campus.getText();
        UiTeam = selfPage.team.getText();
        System.out.println("name = " + UiFullName);
        System.out.println("role = " + UiRole);




    }

}
