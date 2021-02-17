package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private PageFactory pageFactory;

    @FindBy(name = "passCount")
    private WebElement passengers;

    @FindBy(id = "findFlights")
    private WebElement continue_button;



    public FlightDetailsPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }

    public void selectPassengers(String numberofpassengers){
        this.wait.until(ExpectedConditions.elementToBeClickable(passengers));
        Select passenger_count=new Select(passengers);
        passenger_count.selectByValue(numberofpassengers);
    }
    public void goToFindFlightsPage(){
        this.continue_button.click();
    }
}
