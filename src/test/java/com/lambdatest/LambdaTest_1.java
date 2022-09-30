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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LambdaTest_1 {

	private RemoteWebDriver driver;
	private String Status = "failed";
	SoftAssert soft = new SoftAssert();
	Actions act;

	@Parameters(value = { "browser", "version", "platform" })
	@BeforeMethod
	public void setup(Method m, ITestContext ctx, String browser, String version, String platform)
			throws MalformedURLException {
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

	@Test(testName = "Lambda Test First Scenario")
	public void EnterAndValidateMessage() throws InterruptedException {
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
		act = new Actions(driver);
		System.out.println("<-----Lambda Test First Scenario Enter message and Validate it Started----->");
		// 2.Click “Simple Form Demo” under Input Forms.
		WebElement formLink = driver.findElement(By.xpath("//a[text()='Simple Form Demo']"));

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.elementToBeClickable(formLink));
			// formLink.click();
			act.moveToElement(formLink).click(formLink).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3.Validate that the URL contains “simple-form-demo”
		soft.assertTrue(driver.getPageSource().contains("Simple Form Demo"));

		// 4.Create a variable for a string value E.g: “Welcome to LambdaTest”.
		String ExpectedMessage = "Welcome to LambdaTest";

		// 5. Use this variable to enter values in the “Enter Message” text box.
		driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys(ExpectedMessage);

		// 6. Click “Get Checked Value”.
		driver.findElement(By.cssSelector("#showInput")).click();

		/**
		 * 7. Validate whether the same text message is displayed in the right-hand
		 * panel under the “Your Message:” section.
		 */

		String ActualMessage = driver.findElement(By.className("mt-20")).getText();

		soft.assertEquals(ActualMessage, ExpectedMessage);

		System.out.println("<------Lambda Test First Scenario Ended----->");

		soft.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("<===== End of Test Method =====>");
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}