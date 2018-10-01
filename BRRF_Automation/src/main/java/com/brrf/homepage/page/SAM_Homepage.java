package com.brrf.homepage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.brrf.utils.BRRF_Util;

public class SAM_Homepage extends BRRF_Util {
	WebDriver driver;
	
	By searchText=By.name("search");
	By search_go=By.className("search-btn");
	By viewIcon=By.xpath("//*[@id=\"example-datatable\"]/tbody[1]/tr[1]/td[11]/a/i");
	By brandDivision_Text=By.xpath("//*[@class='site-details-overview-wrapper']/div[3]/div[1]");
	
    public SAM_Homepage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public void enterSearch(String text) {
    	waiter(3);
    	enterText(driver, searchText, text);
    }
    
    public void clickSearchBtn() {
    	waiter(1);
    	clickonLink(driver, search_go);
    }
    
    public void clickViewIcon() {
    	waiter(2);
    	clickonLink(driver, viewIcon);
    	waiter(3);
    }
    
 public String getBrandDivisonText() {
    	
    	return getText(driver, brandDivision_Text);
    }
}
