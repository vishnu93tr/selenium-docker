package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void launchBrowser() throws MalformedURLException{
        String host="localhost";
        DesiredCapabilities desiredCapabilities;
        if(System.getProperty("BROWSER")!=null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            desiredCapabilities=DesiredCapabilities.firefox();
        }
        else {
            desiredCapabilities=DesiredCapabilities.chrome();
        }
        if(System.getProperty("HUB_HOST")!=null){
            host=System.getProperty("HUB_HOST");
        }
        String complete_url = "http://" + host + ":4444/wd/hub";
        this.driver=new RemoteWebDriver(new URL(complete_url),desiredCapabilities);

    }

    @AfterTest(alwaysRun = true)
    public void quitBrowser(){
        System.out.println("closing browser");
        driver.quit();
    }
}
