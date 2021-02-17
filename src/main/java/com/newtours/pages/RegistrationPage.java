package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    PageFactory pageFactory;
    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(id = "userName")
    private WebElement email;

    @FindBy(name = "address1")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "postalCode")
    private WebElement postalcode;

    @FindBy(name = "country")
    private WebElement country_dropdown;

    @FindBy(name = "email")
    private WebElement user_name;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirm_password;

    @FindBy(id="register-btn")
    private WebElement register_button;

    public RegistrationPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }
    public void goTo(){
        this.driver.get("https://vins-udemy.s3.amazonaws.com/docker/docker-book-flight.html");
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
    }
    public void enterContactInfo(String firstName,String lastName,String phoneNumber,String email){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.phone.sendKeys(phoneNumber);
        this.email.sendKeys(email);
    }
    public void enterMailingInfo(String address,String city,String state,String postalcode){
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.postalcode.sendKeys(postalcode);

    }
    public void dropDown(String country){
        this.wait.until(ExpectedConditions.elementToBeClickable(country_dropdown));
        Select country_down=new Select(country_dropdown);
        country_down.selectByVisibleText(country);
    }
    public void userInfo(String userName,String password){
        this.user_name.sendKeys(userName);
        this.password.sendKeys(password);
        this.confirm_password.sendKeys(password);
    }
    public void submit(){
        this.register_button.click();
    }
}
