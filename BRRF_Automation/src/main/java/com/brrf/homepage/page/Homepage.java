package com.brrf.homepage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.brrf.utils.BRRF_Util;

public class Homepage extends BRRF_Util {
	WebDriver driver;
	
	By dashboardText=By.xpath("//*[@class='main-wrapper']/div/ng-component/h2");
	By userprofile_dd=By.xpath("//*[@class='dropdown userprofile-link-header']/a");
	By logout=By.linkText("Logout");
	
    public Homepage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public String getDashBoardText() {
    	
    	return getText(driver, dashboardText);
    }
    
    public void logout() throws Exception {
    	waiter(2);
    	hoverOverElement(driver, userprofile_dd);
    	clickonLink(driver, logout);
    }
    
    public String verifyPageTitle() {
    	String result;
    	waiter(5);
    	if(pageTitle(driver).contains("Login"))
    	 	result="true";
    	else
    		result="false";
    	return result;
    }
    
}
