package com.saucelabs.demo;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	WebDriver driver;
	@BeforeMethod
	public void setUp() 
	{
		
		MutableCapabilities sauceOpts=new MutableCapabilities();
		sauceOpts.setCapability("build", "Java-W3C-Examples");
		sauceOpts.setCapability("seleniumVersion", "3.141.59");
		sauceOpts.setCapability("username", System.getenv("SAUCE_USERNAME"));
		sauceOpts.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
		sauceOpts.setCapability("tags", "w3c-chrome-tests");	

		//ChromeOptions cap=new ChromeOptions();
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOpts);
		cap.setCapability("browserVersion", "85.0");
		cap.setCapability("platformName","macOS 10.15");

		try 
		{
			driver=new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} 
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}
	
	

}

