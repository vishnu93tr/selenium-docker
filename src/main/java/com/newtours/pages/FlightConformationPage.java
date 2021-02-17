package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightConformationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private PageFactory pageFactory;

    @FindBy(xpath = "//font[contains(text(),'Flight Confirmation')]")
    private WebElement flightConformationHeader;

    @FindBy(xpath = "//font[contains(text(),'USD')]")
    private List<WebElement> prices;

    @FindBy(linkText = "SIGN-OFF")
    private WebElement signoff;

    public FlightConformationPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }

    public String getPrice(){
        this.wait.until(ExpectedConditions.visibilityOf(flightConformationHeader));
        System.out.println(this.flightConformationHeader.getText());
        System.out.println(this.prices.get(1).getText());
        String price=this.prices.get(1).getText();
        this.signoff.click();
        return price;
    }
}
