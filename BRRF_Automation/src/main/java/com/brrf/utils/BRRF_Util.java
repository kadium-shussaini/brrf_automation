package com.brrf.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import org.openqa.selenium.WebDriver;

public class BRRF_Util extends InstantiateBrowser{
	
	private static Logger BRRFUTILLOGGER = Logger.getLogger(BRRF_Util.class);
	private WebElement ele = null;
	
	
	public void waiter(int n){
		try{
			Thread.sleep(1000 * n);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static int length = 4;
	public String generateRandomString(){
		Random rnd = new Random();
	   StringBuilder sb = new StringBuilder( length );
	   for( int i = 0; i < length; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	public void hoverOverElement(WebDriver driver, By element) throws Exception{
		try {
			Actions builder = new Actions(driver);
			ele=driver.findElement(element);
			builder.moveToElement(ele).build().perform();
		} 
		catch (Exception e){
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	public String getText(WebDriver driver, By element) {
		
		return driver.findElement(element).getText();
	}

	public void enterText(WebDriver driver, By element, String text) {
		
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(text);
	}
	
    public void clickonLink(WebDriver driver, By element) {
		
		driver.findElement(element).click();
	}
    
 public String pageTitle(WebDriver driver) {
		
		return driver.getTitle();
	}
 
 public boolean compareImages(WebDriver driver, By imageelement,  BufferedImage expectedImage) {
	 WebElement logoImage = driver.findElement(imageelement);
     Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
     BufferedImage actualImage = logoImageScreenshot.getImage();
     
     ImageDiffer imgDiff = new ImageDiffer();
     ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
     return diff.hasDiff();
 }
 
 public ArrayList<String> openNewTabandMoveDriverControl(WebDriver driver) throws AWTException {
	 waiter(2);
	 Robot robot = new Robot();
     robot.keyPress(KeyEvent.VK_CONTROL);
     robot.keyPress(KeyEvent.VK_T);
     robot.keyRelease(KeyEvent.VK_T);
     robot.keyRelease(KeyEvent.VK_CONTROL);
     ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
     driver.switchTo().window(tabs.get(1));
     waiter(5);
     return tabs;
 }
 
 public void loginYahooMailInbox(WebDriver driver, String email, String password) {
	 waiter(2);
	 System.out.println("BEFORE");
	 driver.findElement(By.xpath("//*[@id=\"login-username-form\"]/p[2]/span[1]/label")).click();
	 System.out.println("AFTER");
	 driver.findElement(By.id("login-username")).sendKeys(email);
     driver.findElement(By.id("login-signin")).click();
     waiter(2);
     driver.findElement(By.id("login-passwd")).sendKeys(password);
     driver.findElement(By.id("login-signin")).click();
     waiter(2);
     driver.navigate().to("https://mail.yahoo.com/d/folders/1?.intl=in&.lang=en-IN&.partner=none&.src=fp");
     //driver.findElement(By.xpath("//*[@id='uh-mail']/a/i")).click();
     waiter(2);
 }
 
 public void logoutYahooMail(WebDriver driver) {
	 driver.findElement(By.xpath("//*[@class='D_F p_R k_w r_P H_6NIX W_6D6F X_6DEy N_fq7 ab_C ir3_1JO2M7 it3_0 I4_2tvNsT n_Z1YKD9t I_1kC8gG']")).click();
	 waiter(2);
	 driver.findElement(By.xpath("//*[@class='G_e p_R']")).click();
     driver.findElement(By.xpath("//*[@class='M_1MYwXA cdPFi_Z1RVMOO C_Z1RVMOO C4_Z2aVTcY cdPFi4_Z2aVTcY']")).click();
     driver.findElement(By.xpath("//*[@id=\"ybar\"]/div[3]/div[1]/div/label/img")).click();
     driver.findElement(By.xpath("//*[@id=\"ybarAccountMenuBody\"]/a[3]/span")).click();
     driver.close();
 }
 
 public void waitForElement(WebDriver driver, WebElement element, int time) {
	 		 WebDriverWait wait = new WebDriverWait(driver,time);
			 wait.until(ExpectedConditions.visibilityOf(element));
 }
	
	
}
