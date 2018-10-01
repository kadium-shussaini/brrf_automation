package com.brrf.utils;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.brrf.resources.BRRF_Setup;

public class InstantiateBrowser implements BRRF_Setup {
	private static Logger BROWSERINSTANTIATELOGGER = Logger.getLogger(InstantiateBrowser.class);
    private static WebDriver driver=null;
    private String DOWNLOADSPATH=System.getProperty("user.dir")+"\\fileDownloads";
    
    public InstantiateBrowser(){
    }
    
	public WebDriver setUp(String browser)
    {
    	if (browser.equalsIgnoreCase("Chrome")){
                        System.setProperty("webdriver.chrome.driver", CHROMEDRIVERPATH);
                        String downloadFilepath = DOWNLOADSPATH;
                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("download.default_directory", downloadFilepath);
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--test-type");
                        //options.setExperimentalOptions("prefs", chromePrefs);
                        options.setExperimentalOption("prefs", chromePrefs);
                        options.addArguments("--start-maximized");
                        options.addArguments("--always-authorize-plugins");
                        options.addArguments("--ignore-certificate-errors");
                        options.addArguments("chrome.switches","--disable-extensions");
                        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
                        DesiredCapabilities cap = DesiredCapabilities.chrome();
                        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
                        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        cap.setCapability(ChromeOptions.CAPABILITY, options);
                        options.addArguments("--always-authorize-plugins");
                        options.addArguments("--ignore-certificate-errors");
                        driver= new ChromeDriver(cap);
                        driver.manage().window().maximize();
                        BROWSERINSTANTIATELOGGER.info("Chrome broswer initiated");
        }
        else if (browser.equalsIgnoreCase("IE"))
        {               
                        System.setProperty("webdriver.ie.driver", IEPATH);
                        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                                        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                                        caps.setCapability("ignoreZoomSetting", true);
                                        caps.setCapability("ignoreProtectedModeSettings" , true);
                                        driver= new InternetExplorerDriver(caps);
                                        driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("Firefox")){
        	 driver = new FirefoxDriver();
        	 driver.manage().window().maximize();
        }
    	BROWSERINSTANTIATELOGGER.info("Returning driver from loginTest class : "+driver);
    	return driver;
    }
	
	public void openUrl(WebDriver driver, String url) throws Exception
	{
		BROWSERINSTANTIATELOGGER.info("URL=="+url);
		driver.get(url);
		try {
		} catch (Exception e) {
			BROWSERINSTANTIATELOGGER.error("Method: openUrl; Error Msg: "+e.getMessage());
			throw new Exception(e);
		}
		
	}
	
	public void navigateUrl(WebDriver driver, String url) throws Exception
	{
		BROWSERINSTANTIATELOGGER.info("URL=="+url);
		driver.navigate().to(url);
		try {
		} catch (Exception e) {
			BROWSERINSTANTIATELOGGER.error("Method: openUrl; Error Msg: "+e.getMessage());
			throw new Exception(e);
		}
		
	}
	
 }

	
