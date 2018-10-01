package com.brrf.loginpage.page;

import java.awt.image.BufferedImage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.brrf.utils.BRRF_Util;

import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class Loginpage extends BRRF_Util {
	WebDriver driver;
	
	By userid=By.id("Username");
	By password=By.id("Password");
	By login=By.name("button");
	By createnewaccount=By.linkText("Create New Account");
	By loginImage=By.className("page-wrapper");
	By forgotPassword=By.xpath("//*[@class='forgot-password-wrapper']/a");
	
    public Loginpage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public void enterUserID(String text) {
    	
    	enterText(driver, userid, text);
    }
    
    public void enterPassword(String text) {
    	
    	enterText(driver, password, text);
    }
    
    public void clickLoginBtn() {
    	
    	clickonLink(driver, login);
    }
    
  public void clickForgotPasswordLink() {
    	
    	clickonLink(driver, forgotPassword);
    }
    
    public void clickCreateNewAccountBtn() {
    	
    	clickonLink(driver, createnewaccount);
    }
    
    public boolean compareDifferentImage(BufferedImage expectedImage) {
    	driver.navigate().to(UM_URL+"/bp");
    	waiter(3);
    	return compareImages(driver, loginImage, expectedImage);
    }
    
    public boolean comparesameImage(BufferedImage expectedImage) {
    	waiter(5);
    	return compareImages(driver, loginImage, expectedImage);
    }
    
    
}
