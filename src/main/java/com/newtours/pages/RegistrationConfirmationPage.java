package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationConfirmationPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private PageFactory pageFactory;

    @FindBy(xpath = "//a[contains(text(),'sign-in')]")
    private WebElement signin_link;
    @FindBy(id="flight-link")
    private WebElement flights_page;
    public RegistrationConfirmationPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }

    public void gotoFlightDetailsPage(){
        this.wait.until(ExpectedConditions.visibilityOf(this.signin_link));
        this.flights_page.click();
    }
}
