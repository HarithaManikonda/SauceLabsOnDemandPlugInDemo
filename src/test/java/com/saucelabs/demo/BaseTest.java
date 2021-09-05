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

public class BaseTest
{
	WebDriver driver;
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
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
		cap.setCapability("driver", System.getenv("SELENIUM_DRIVER"));
		cap.setCapability("browserVersion", System.getenv("SELENIUM_VERSION"));
		cap.setCapability("platformName",System.getenv("SELENIUM_PLATFORM"));
		//WebDriverManager.chromedriver().setup();
		cap.setCapability("browserName", System.getenv("SELENIUM_BROWSER"));
		String id="843599348594385";
		sessionId.set(id);

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
	    public WebDriver getWebDriver() {
	    	return webDriver.get();
	    }
	    public String getSessionId() {
	    	return sessionId.get();
	    }
	    
	

}

