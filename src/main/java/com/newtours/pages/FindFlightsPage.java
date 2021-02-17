package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindFlightsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private PageFactory pageFactory;

    @FindBy(id = "reserveFlights")
    private WebElement reserve_flights;

    @FindBy(id= "buyFlights")
    private WebElement book_flight;


    public FindFlightsPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }
    public void findFlightsPage(){
        this.wait.until(ExpectedConditions.visibilityOf(reserve_flights));
        this.reserve_flights.click();
    }
    public void bookFlights(){
        this.wait.until(ExpectedConditions.visibilityOf(book_flight));
        this.book_flight.click();
    }
}
