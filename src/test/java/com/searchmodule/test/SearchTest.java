package com.searchmodule.test;

import com.duckduckgo.pages.DuckDuckGoPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SearchTest extends BaseTest {


    private String search_text;

    @Test
    @Parameters("search_text")
    public void SearchTest(String search_text){
        DuckDuckGoPage duckDuckGoPage=new DuckDuckGoPage(driver);
        duckDuckGoPage.goTo();
        duckDuckGoPage.dosearch(search_text);
        duckDuckGoPage.GoToVideosLink();
        int size=duckDuckGoPage.getVideoCount();
        Assert.assertTrue(size>0);
    }

}
