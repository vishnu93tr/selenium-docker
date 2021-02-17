package com.duckduckgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DuckDuckGoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    PageFactory pageFactory;

    @FindBy(id = "search_form_input_homepage")
    private WebElement search_box;

    @FindBy(id = "search_button_homepage")
    private WebElement search_button;

    @FindBy(xpath = "//a[contains(text(),'Videos')]")
    private WebElement video_link;

    @FindBy(xpath = "//div[contains(@class,'tile  tile')]")
    private List<WebElement> get_number_of_videos;

    public DuckDuckGoPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        pageFactory.initElements(driver,this);
    }
    public void goTo(){
        this.driver.get("https://duckduckgo.com/");
    }
    public void dosearch(String search_text){
        this.wait.until(ExpectedConditions.visibilityOf(this.search_box));
        this.search_box.sendKeys(search_text);
        this.search_button.click();
    }
    public void GoToVideosLink(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.video_link));
        this.video_link.click();
    }
    public int getVideoCount(){
        By results=By.xpath("//div[contains(@class,'tile  tile')]");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(results,0));
        System.out.println(this.get_number_of_videos.size());
        return this.get_number_of_videos.size();
    }
}
