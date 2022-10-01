package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LambdaTest_2 {
	private RemoteWebDriver driver;
    private String Status = "failed";
    SoftAssert soft= new SoftAssert();

    @Parameters(value={"browser", "version","platform"})
    @BeforeMethod
    public void setup(Method m, ITestContext ctx,String browser,String version, String platform) throws MalformedURLException {
    	System.out.println("<===== Start of Test Method =====>");
    	String username = "sanjaywaware04";
    	String accesskey = "fSImr5qgF3iOZPecUiFmSXHJDDmbzvXmcsi7ExEz7UHlMBkMCE";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        caps.setCapability("version", version);
        caps.setCapability("platform", platform);
        caps.setCapability("network", true); // To enable network logs
		caps.setCapability("visual", true); // To enable step by step screenshot
		caps.setCapability("video", true); // To enable video recording
		caps.setCapability("console", true); // To capture console logs
		 caps.setCapability("seCdp", true);
		 caps.setCapability("w3c", true);
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + hub), caps);
    }

    @Test (testName="Lambda Test second Scenario")
	public <IWebElement> void HandlingSliderBar() throws InterruptedException {
    	//Opening browser with the given URL and navigate to Registration Page
       	//1. Open LambdaTest’s Selenium Playground from
       //	https://www.lambdatest.com/selenium-playground

           try {
        	   System.out.println("Step 1:Open LambdaTests Selenium Playground form");
			driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.manage().window().maximize();
			   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			   driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("<---Lambda Test Second Scenario Handling Slider Bar Started ---->");
		
		System.out.println("Step 1: click Drag and Drop Sliders under Progress Bars and Sliders.");
	WebElement slider = driver.findElement(By.xpath("//a[normalize-space()='Drag & Drop Sliders']"));
	WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(slider));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", slider);

	} catch (Exception e) {
		e.printStackTrace();
	}
	
		//3. Select the slider “Default value 15” 

    System.out.println("Step 3: Select the slider Default value 15.");
	
//		WebElement slider_bar = driver.findElement(By.xpath("//input[@value='15']"));
//		soft.assertTrue(slider_bar.isDisplayed());
		
			//4. drag the bar to make it 95 
		System.out.println("Step 4: drag the bar to make it 95");
			try {
				Actions act = new Actions(driver);
				WebElement slider_bar = driver.findElement(By.xpath("//input[@value='15']"));
				soft.assertTrue(slider_bar.isDisplayed());
				 
				act.moveToElement(slider_bar, 454, 600).click();
		        int i = 0;
		        while (i < 80)
		        {
		        	slider_bar.sendKeys(Keys.RIGHT);
		            ++i;
		        } 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//5. validating whether the range value shows 95.
		System.out.println("Step 5: validating whether the range value shows 95.");
		
		String actual = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		System.out.println("The slider moved horizontal right at value of :" + actual);
	     soft.assertEquals(actual, "95");
		
		System.out.println("Lambda Test Second Scenario Ended");
	soft.assertAll();
}

    @AfterMethod
    public void tearDown() {
    	System.out.println("<=== End of the test method ====>");
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
    


}