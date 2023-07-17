package com.vitamojo.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Tests for login:
 * 1. Register an user
 * 2. Login using the registered user.
 **/
public class LoginTest {

	// Page objects
	CreateAccountPage createAccountPage;
	LoginPage loginPage;

	// Driver
	WebDriver driver;	

	// url
	String url = " https://fego.vmos-demo.com";

	// Login details
	String name = "";
	String emailAddress = "";
	String password = "Test1234";
	String phoneNum = "11111";
	String uniqNum = "";

	/**
	 * Load chrome browser, open the url and navigate to the login page.
	 * 
	 * @throws Exception
	 * 		Exception encountered
	 */
	@BeforeTest 
	public void setUp() throws Exception {
		
		// Generate unique name and email address
		Random random = new Random();
		int lowerBound = 10000;
		int upperBound = 99999;
		uniqNum = Integer.toString(random.nextInt(upperBound-lowerBound) + lowerBound);
        
        // Set user and email address
        name = "Name" + uniqNum;
    	emailAddress = "Name" + uniqNum + "@xxx.com";
    	phoneNum = uniqNum + phoneNum;

		// Chrome options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized");

		// Add chrome options to the driver
		driver = new ChromeDriver(options);

		// Initialize Page Objects
		createAccountPage = new CreateAccountPage(driver);
		loginPage = new LoginPage(driver);

		// Open the url
		driver.get(url);
	}

	/**
	 * Test for registration.
	 */
	@Test
	public void testRegistration() throws Exception {

		try {
			// Open login page
			loginPage.openLoginPage();

			// Create account and verify
			Assert.assertEquals(createAccountPage.createAccount(name, emailAddress, phoneNum,password), true, "Account '" + name + "' has NOT been created");
			
			// Logout
			Assert.assertEquals(loginPage.logout(name), true, "Account '" + name + "' has NOT been logged out");

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Test for login.
	 */
	@Test(dependsOnMethods={"testRegistration"})
	public void testLogin() throws Exception {

		try {
			// Open the login page
			loginPage.openLoginPage();
			
			// Login to account and verify
			Assert.assertEquals(loginPage.login(name, emailAddress, password), true, "User '" + name + "' has NOT been logged in");

			// Verify login is successful
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Close browser.
	 * 
	 * @throws Exception
	 * 		Exception encountered
	 */
	@AfterTest
	public void tearDown() {
		// Close and quit the driver
		driver.close();
		driver.quit();
	}
}
