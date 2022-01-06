package com.bookIt.pages;

import com.bookIt.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPages extends MenuBar{

    public LogInPages() {
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(name = "email")
    public WebElement emailField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//button[.='sign in']")
    public WebElement clickButton;

    public void logIn(String username,String password){
        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        clickButton.click();
    }

}
