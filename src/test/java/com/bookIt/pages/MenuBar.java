package com.bookIt.pages;

import com.bookIt.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class MenuBar{




    @FindBy(linkText = "my")
    public WebElement myLink;

    @FindBy(linkText = "self")
    public WebElement selfLink;
}
