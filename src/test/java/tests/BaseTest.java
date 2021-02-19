package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static Object[][] getCases(String filename){
        List<Object[]> list=new ArrayList<>();
        try {
            List lines=IOUtils.readLines(BaseTest.class.getClassLoader().getResourceAsStream(filename),"UTF-8");
            JSONObject jsonObject=new JSONObject(lines.stream().collect(Collectors.joining()).toString());
            JSONArray jsonArray=jsonObject.getJSONArray("cases");
            for(Object o:jsonArray){
                JSONObject object=(JSONObject)o;
                list.add(new Object[]{object.getString("name"),object.getJSONObject("case").toString()});
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return list.toArray(new Object[][]{});
    }
}
