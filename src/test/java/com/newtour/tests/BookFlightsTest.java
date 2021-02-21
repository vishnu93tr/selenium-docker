package com.newtour.tests;

import com.aventstack.extentreports.Status;
import com.newtours.pages.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightsTest extends BaseTest{

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setupParameters(String noOfPassengers,String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
    }
    @Test(dataProvider = "dp_registration")
    public void registrationPageTest(String name,String json_case) {
        JSONObject jsonObject=new JSONObject(json_case);
        JSONObject contact_info=jsonObject.getJSONObject("contactinfo");
        JSONObject mail_info=jsonObject.getJSONObject("mail_info");
        JSONObject user_info=jsonObject.getJSONObject("user_info");
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterContactInfo(contact_info.getString("firstname"),
                contact_info.getString("lastname"),
                contact_info.getString("phone_number"),
                contact_info.getString("email"));

        registrationPage.enterMailingInfo(mail_info.getString("address"),
                mail_info.getString("city"),
                mail_info.getString("state"),
                mail_info.getString("pincode"));

        registrationPage.userInfo(user_info.getString("user_name"),user_info.getString("password"));
        registrationPage.dropDown(jsonObject.getString("country"));
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
    @DataProvider
    public Object[][] dp_registration(){
       Object[][] obj= getCases("testcases/FlightsModule/Registration.json");
       for(int i=0;i<obj.length;i++){
           JSONObject js=new JSONObject(obj[i][1].toString());
           obj[i][1]=js.toString();
       }
       return obj;
    }
}
