package com.newtour.tests;

import com.newtours.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightsTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;
    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setupParameters(String noOfPassengers,String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
    }
    @Test
    public void registrationPageTest() {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterContactInfo("vishnu","vardhan","8074839733","vishnu26121993");
        registrationPage.enterMailingInfo("address","city","state","515004");
        registrationPage.userInfo("vishnu","password");
        registrationPage.dropDown("INDIA");
        registrationPage.submit();
    }
    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.gotoFlightDetailsPage();
    }
    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightsDetailsPageTest(){
        FlightDetailsPage flightDetailsPage=new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }
    @Test(dependsOnMethods = "flightsDetailsPageTest")
    public void findFlightsPageTest(){
        FindFlightsPage findFlightsPage=new FindFlightsPage(driver);
        findFlightsPage.findFlightsPage();
        findFlightsPage.bookFlights();
    }
    @Test(dependsOnMethods = "findFlightsPageTest")
    public void printConformationPageTest(){
        FlightConformationPage flightConformationPage=new FlightConformationPage(driver);
        String actual_price=flightConformationPage.getPrice();
        Assert.assertEquals(actual_price,expectedPrice);
    }
}
