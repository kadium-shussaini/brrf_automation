package com.brrf.forgotpassword.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.brrf.utils.BRRF_Util;

public class ForgotPassword extends BRRF_Util {
	WebDriver driver;
	
	By userid=By.id("UserName");
	By requestPassword_btn=By.xpath("//*[@class='btn btn-sm login-btn']");
	By popUp_OK_btn=By.xpath("//*[@class='modal-footer-btn-active']");
	By password=By.id("Password");
	By confirmPassword=By.id("ConfirmPassword");
	By submit=By.name("button");
	By successText=By.xpath("//*[@class='modal-success-message']");
	By successPopUp_OK_btn=By.xpath("//*[@class='modal-footer-btn-active']");
	
	
	
    public ForgotPassword(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public void enterUserID(String text) {
    	
    	enterText(driver, userid, text);
    }
    
    public void clickRequestPasswordBtn() {
    	
    	clickonLink(driver, requestPassword_btn);
    	waiter(3);
    }
    
 public void clickSuccessPopUpOKBtn() {
    	
    	clickonLink(driver, popUp_OK_btn);
    }
 
 public String getSubjectTextYahooMail() {
	 WebElement element = driver.findElement(By.xpath("//*[@class='D_F o_h ab_C H_6D6F em_sL ej_0']"));
	 waitForElement(driver, element, 10);
	 driver.findElement(By.xpath("//*[@class='D_F o_h ab_C H_6D6F em_sL ej_0']")).click();
     return driver.findElement(By.xpath("//*[@class='en_N i_3mCHE']/span")).getText();
 }
 
 public void enterPassword(String text) {
 	
 	enterText(driver, password, text);
 }
 
 public void enterConfirmPassword(String text) {
	 	
	 	enterText(driver, confirmPassword, text);
	 }
    
 public void clickSubmitBtn() {
 	
 	clickonLink(driver, submit);
 }
 
 public String getForgotPasswordSuccessText() {
 	
 	return getText(driver, successText);
 }
 
 public void clickSuccessPopUpOKBtnInResetPwd() {
	 	
	 	clickonLink(driver, successPopUp_OK_btn);
	 }
 
}
