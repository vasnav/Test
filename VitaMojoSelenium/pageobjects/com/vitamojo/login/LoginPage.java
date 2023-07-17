package com.vitamojo.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page objects for Login.
 */
public class LoginPage {

	// Driver
	WebDriver driver;

	// Repository
	private LoginRepository loginRepository;
	private LoginRepository.LoginTabRepository loginTabRepository;

	/**
	 * Initialize all elements in the 'Related Articles' window.
	 * 
	 * @param driver
	 *            The driver for the Browser that will run the application
	 */
	public LoginPage(WebDriver driver) {

		try {
			// Set driver
			this.driver = driver;

			// Initializing repositories
			loginRepository = new LoginRepository(driver);
			loginTabRepository = new LoginRepository.LoginTabRepository(driver);
		} catch (Exception error) {
			throw error;
		}
	}

	/**
	 * Open the 'Login' page.
	 */
	public void openLoginPage(){
		System.out.println("Open the 'Login' page");

		// Open the 'Login' page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginRepository.loginPageLinkXpath)));
		loginRepository.loginPageLink.click();

		// Wait for the elements to appear to make sure the page has loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginRepository.tabLoginXpath)));
	}

	/**
	 * Login.
	 * 
	 * @param emailAddress
	 * 		Email address/Username for login
	 * @param password
	 * 		Password for login
	 * 
	 * @return 
	 * 		True/False
	 */
	public boolean login(String name, String emailAddress, String password){
		System.out.println("Login using 'username' = '" + emailAddress + "' and 'password' = '" + password+"'");

		// Choose the 'Login' tab
		loginRepository.tabLogin.click();

		// Enter username
		loginTabRepository.loginEmail.click();
		loginTabRepository.loginEmail.sendKeys(emailAddress);

		// Enter password
		loginTabRepository.loginPassword.click();
		loginTabRepository.loginPassword.sendKeys(password);

		// Click 'Login' button
		loginTabRepository.loginButton.click();

		// Verify account has been logged in
		boolean loggedIn = verifyLogin(name);
		System.out.println("Login status of 'User' = '" + name + "' = '" + loggedIn + "'");

		// Return
		return loggedIn;
	}

	/**
	 * Verify the account has been logged into by checking the username at the top end.
	 * 
	 * @param name
	 * 		Name of the account holder
	 *
	 *@return 
	 * 		True/False
	 */
	public boolean verifyLogin(String name){
		System.out.println("Verify account with 'name' = '" + name + "' has been logged in");

		// Verify the account has been created by checking the presence of the username at the top end
		try {
			String loggedUserXpath = loginRepository.loggedUserXpath.replace("DYNAMIC_VARIABLE", name);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loggedUserXpath)));
			return true;
		} catch (Exception ex) {
			System.out.println("Element = '" + loginRepository.loggedUserXpath + "' is not visible. " + ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Login the account.
	 * 
	 * @param name
	 * 		Name of the account holder
	 *
	 *@return 
	 * 		True/False
	 */
	public boolean logout(String name){
		System.out.println("Logout account with 'name' = '" + name + "'");
		
		// Click the username
		String loggedUserXpath = loginRepository.loggedUserXpath.replace("DYNAMIC_VARIABLE", name);
		WebElement loggedUserWebElement = driver.findElement(By.xpath(loggedUserXpath));
		loggedUserWebElement.click();
		
		// Click the 'Sign Out' button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginRepository.logoutXpath)));
		loginRepository.logout.click();
		
		// Check the presence of the 'Login' button to make sure user has logged out
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginRepository.loginPageLinkXpath)));
			return true;
		} catch (Exception ex) {
			System.out.println("Sign out is NOT successful");
			return false;
		}
	}
}
