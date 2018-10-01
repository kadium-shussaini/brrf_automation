package com.brrf.resources;

public interface BRRF_Setup 
{
		
		 public static final String UM_URL="https://admin-qa.brrflowsystems.com";
		 public static final String SAM_URL="https://sam-qa.brrflowsystems.com";
		 public static final String PHP_LIVE_URL="http://ec2-54-214-86-255.us-west-2.compute.amazonaws.com/SasView?shipToNumber=";
		 public final String USERID="superadmin";
		 public final String PASSWORD="password";
		 public final String BROWSER="Chrome";
		 public final String ENVIRONMENT ="QA";
		 public final String CHROMEDRIVERPATH =System.getProperty("user.dir")+"/Drivers/Chrome//chromedriver.exe"; 
		 public final String IEPATH = System.getProperty("user.dir")+"/Drivers/IE/IEDriverServer.exe";
		 public final String FIREFOXPATH = System.getProperty("user.dir")+"/Drivers/Firefox/geckodriver.exe";
		 public final String PROPERTIESPATH=System.getProperty("user.dir")+"/src/main/java/com/brrf/resources/DataProperties.properties";
		 public final String REPORTPATH = System.getProperty("user.dir")+"/Reports";
		 public final String FORGOT_PASSWORD="bppbrandadmin";
		 public final String YAHOO_MAIL = "https://login.yahoo.com/";
		 public final String FORGOTPASSWORD_EMAIL = "brandbp@yahoo.com";
		 public final String FORGOTPASSWORD_PWD = "Test@123";
		 public final String UNIVERSAL_PASSWORD = "Test@123";
}
