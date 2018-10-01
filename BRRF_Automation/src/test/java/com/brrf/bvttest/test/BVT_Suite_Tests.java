package com.brrf.bvttest.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.brrf.forgotpassword.page.ForgotPassword;
import com.brrf.homepage.page.Homepage;
import com.brrf.homepage.page.SAM_Homepage;
import com.brrf.loginpage.page.Loginpage;
import com.brrf.resources.BRRF_Setup;
import com.brrf.utils.BRRF_Util;
import com.brrf.utils.InstantiateBrowser;

public class BVT_Suite_Tests extends BRRF_Util implements BRRF_Setup {
	
	private static Logger BVTLOGGER = Logger.getLogger(BVT_Suite_Tests.class);
	WebDriver driver = null;
	private Properties props;
	private String sTestcaseId;
	private String sTestcaseName;
	private InstantiateBrowser instantiateBrowser = new InstantiateBrowser();
	
	
	
	@BeforeClass
    public void setUp() throws IOException
    {
		BVTLOGGER.info("BVT Suite Tests");
		FileReader reader = new FileReader(PROPERTIESPATH);
	    props = new Properties();
	    props.load(reader);
        instantiateBrowser = new InstantiateBrowser();
   	    driver = instantiateBrowser.setUp(BROWSER);
    }
	
	@Test
	public void verifyValidLoginToUserManagement() throws Exception {
		sTestcaseId = "TC_001";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.openUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        Homepage homepage = new Homepage(driver);
        login.enterUserID(USERID);
        login.enterPassword(PASSWORD);
        login.clickLoginBtn();
        String actual=homepage.getDashBoardText();
        homepage.logout();
        Assert.assertEquals(actual, "Dashboard");
	}
	
	
	@Test
	public void verifyLoggingOutFromUserManagement() throws Exception {
		sTestcaseId = "TC_002";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        Homepage homepage = new Homepage(driver);
        waiter(5);
        login.enterUserID(USERID);
        login.enterPassword(PASSWORD);
        login.clickLoginBtn();
        homepage.logout();
        Assert.assertEquals(homepage.verifyPageTitle(), "true");
	}
	
	@Test
	public void verifyComparingSameScreenshots() throws Exception {
		sTestcaseId = "TC_003";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\Images\\ElementScreenshot.png"));
        login.comparesameImage(expectedImage);
        Assert.assertEquals(login.comparesameImage(expectedImage), false);
	}
	
	@Test
	public void verifyComparingDifferentScreenshots() throws Exception {
		sTestcaseId = "TC_004";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\Images\\ElementScreenshot.png"));
        login.compareDifferentImage(expectedImage);
        Assert.assertEquals(login.compareDifferentImage(expectedImage), true);
	}
	
	@Test
	public void verifyComparingTextFromDifferentURLs() throws Exception {
		sTestcaseId = "TC_005";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, SAM_URL);
        Loginpage login = new Loginpage(driver);
        SAM_Homepage sam_homepage = new SAM_Homepage(driver);
        Homepage homepage = new Homepage(driver);
        login.enterUserID(USERID);
        login.enterPassword(PASSWORD);
        login.clickLoginBtn();
        sam_homepage.enterSearch("200122");
        sam_homepage.clickSearchBtn();
        sam_homepage.clickViewIcon();
        String expectedText = sam_homepage.getBrandDivisonText();
        ArrayList<String> tab = openNewTabandMoveDriverControl(driver);
        waiter(5);
        instantiateBrowser.navigateUrl(driver, PHP_LIVE_URL+"200122");
        waiter(5);
        String actualText = driver.findElement(By.xpath("//*[@id=\"tabStrip-1\"]/table/tbody/tr[1]/td/table/tbody/tr[1]/td[4]")).getText();
        driver.close();
        driver.switchTo().window(tab.get(0));
        homepage.logout();
        Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void verifyResettingPasswordEmailReceived() throws Exception {
		sTestcaseId = "TC_006";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        ForgotPassword forgot = new ForgotPassword(driver);
        login.clickForgotPasswordLink();
        forgot.enterUserID(FORGOT_PASSWORD);
        forgot.clickRequestPasswordBtn();
        forgot.clickSuccessPopUpOKBtn();
        ArrayList<String> tab = openNewTabandMoveDriverControl(driver);
        instantiateBrowser.navigateUrl(driver, YAHOO_MAIL);
        loginYahooMailInbox(driver, FORGOTPASSWORD_EMAIL, FORGOTPASSWORD_PWD);
        String actualText=forgot.getSubjectTextYahooMail();
        logoutYahooMail(driver);
        driver.switchTo().window(tab.get(0));
        Assert.assertEquals(actualText, "Forgot Password Request");
	}
	
	@Test
	public void verifyResettingPasswordLinkInEmail() throws Exception {
		sTestcaseId = "TC_007";
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName(); 
        BVTLOGGER.info("Executing Testcase : "+sTestcaseId+"_"+sTestcaseName); 
        instantiateBrowser.navigateUrl(driver, UM_URL);
        Loginpage login = new Loginpage(driver);
        ForgotPassword forgot = new ForgotPassword(driver);
        waiter(5);
        login.clickForgotPasswordLink();
        forgot.enterUserID(FORGOT_PASSWORD);
        forgot.clickRequestPasswordBtn();
        forgot.clickSuccessPopUpOKBtn();
        openNewTabandMoveDriverControl(driver);
        instantiateBrowser.navigateUrl(driver, YAHOO_MAIL);
        loginYahooMailInbox(driver, FORGOTPASSWORD_EMAIL, FORGOTPASSWORD_PWD);
        WebElement element = driver.findElement(By.xpath("//*[@class='D_F o_h ab_C H_6D6F em_sL ej_0']"));
        waitForElement(driver, element, 10);
        driver.findElement(By.xpath("//*[@class='D_F o_h ab_C H_6D6F em_sL ej_0']")).click();
        driver.findElement(By.linkText("click here")).click();
        ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(2));
        forgot.enterPassword(UNIVERSAL_PASSWORD);
        forgot.enterConfirmPassword(UNIVERSAL_PASSWORD);
        forgot.clickSubmitBtn();
        String actualText=forgot.getForgotPasswordSuccessText();
        forgot.clickSuccessPopUpOKBtnInResetPwd();
        driver.close();
        driver.switchTo().window(tabs1.get(1));
        logoutYahooMail(driver);
        driver.switchTo().window(tabs1.get(0));
        Assert.assertEquals(actualText, "Password saved successfully");
	}
	
	@AfterClass
    public void tearDown() throws Exception
	{
		 driver.quit();
	}
	
}