package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LambdaTest_3 {

	private RemoteWebDriver driver;
	private String Status = "failed";
	SoftAssert soft = new SoftAssert();
	Select select;

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
	        caps.setCapability("build", "TestNG With Java");
	        caps.setCapability("name", m.getName() + this.getClass().getName());
	        caps.setCapability("plugin", "git-testng");

	        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + hub), caps);
	    }

	@Test(testName = "Lambda Test Third Scenario")
	public void FormFilling() throws InterruptedException {
		// Opening browser with the given URL and navigate to Registration Page
		// 1. Open LambdaTest’s Selenium Playground from
		// https://www.lambdatest.com/selenium-playground

		try {
			System.out.println("Step 1:Open LambdaTest’s Selenium Playground from");
   			
			driver.get("https://www.lambdatest.com/selenium-playground/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Actions act = new Actions(driver);
		System.out.println("<-----Third Scenario Form filling started---->");
		// 2.click “Input Form Submit” under “Input Forms”.
		
		System.out.println("<----Third Scenario Form filling started----->");
		System.out.println("Step 2 : click on Input Form Submit link under Input Forms.");
		
		WebElement inputFormLink = driver.findElement(By.xpath("//a[contains(text(),'Input Form')]"));

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.elementToBeClickable(inputFormLink));
			act.moveToElement(inputFormLink).click(inputFormLink).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3. Click on Submit button without filling in any information in the form.
		System.out.println("Step 3: Click on Submit without filling in any information in the form."); 
		WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.presenceOfElementLocated((By) submitButton));
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 4. Assert “Please fill in the fields” error message.
		System.out.println("Step 4: Assert Please fill in the fields error message."); 
		boolean actualError = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("validationMessage") != null;
		System.out.println(actualError);
		soft.assertTrue(actualError);

		// 5. Fill in Name, Email, and other fields. locators of all form fields
		
		System.out.println("Step 5 : Fill in Name, Email, and other fields. locators of all form fields"); 
		
		driver.findElement(By.cssSelector("#name")).sendKeys("Sanjay Waware");

		driver.findElement(By.id("inputEmail4")).sendKeys("sanjay.waware@zensar.com");

		driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("Pass@123");

		driver.findElement(By.cssSelector("#company")).sendKeys("Zensar Technologies Ltd.");

		driver.findElement(By.cssSelector("#websitename")).sendKeys("https://www.zensar.com");

		try {
			WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));

			act.moveToElement(countryDropdown).click().sendKeys("United States");
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector("#inputCity")).sendKeys("Pune,Maharashtra");

		driver.findElement(By.cssSelector("#inputAddress1")).sendKeys("Evon IT Park, Kharadi, Pune");

		driver.findElement(By.cssSelector("#inputAddress2")).sendKeys("Evon IT Park, Kharadi, Pune");

		driver.findElement(By.cssSelector("#inputState")).sendKeys("Maharashtra");

		driver.findElement(By.cssSelector("#inputZip")).sendKeys("10038");
		
		

		// 7. Fill all fields and click “Submit”.
		System.out.println("Step 6: click Submit Button after fill the form");
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.presenceOfElementLocated((By) submitButton));
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("STep 7: validate the success message on screen ---> Thanks for contacting us, will get back to you shortly.");

		soft.assertTrue(driver.getPageSource().contains("Thanks for contacting us, we will get back to you shortly."));
		System.out.println("Lambda Test Form filling and Submission Ended");
		soft.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("<===== End of Test Method =====>");
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}