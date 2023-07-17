package com.vitamojo.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page objects for Login.
 */
public class CreateAccountPage {

	// Driver
	WebDriver driver;
	
	// Page objects
	private LoginPage loginPage;

	// Repository
	private LoginRepository loginRepository;
	private LoginRepository.CreateAccountTabRepository createAccountTabRepository;

	/**
	 * Initialize all elements in the 'Related Articles' window.
	 * 
	 * @param driver
	 *            The driver for the Browser that will run the application
	 */
	public CreateAccountPage(WebDriver driver) {

		try {
			// Set driver
			this.driver = driver;
			
			// Initialiaze page objects
			loginPage = new LoginPage(driver);

			// Initializing repositories
			loginRepository = new LoginRepository(driver);
			createAccountTabRepository = new LoginRepository.CreateAccountTabRepository(driver);
		} catch (Exception error) {
			throw error;
		}
	}

	/**
	 * Create account.
	 * 
	 * @param name
	 * 		Name of the account holder
	 * @param emailAddress
	 * 		Email address of the account holder
	 * @param password
	 * 		Password for login
	 * 
	 * @return 
	 * 		True/False
	 */
	public boolean createAccount(String name, String emailAddress, String phoneNumber, String password){
		System.out.println("Create account with 'name' = '" + name + "', 'emailAddress' = '" + emailAddress + "' and 'password' = '" + password+"'");

		// Choose the 'Create Account' tab
		loginRepository.tabCreateAccount.click();

		// Enter name
		createAccountTabRepository.createAccountName.click();
		createAccountTabRepository.createAccountName.sendKeys(name);

		// Enter email addess
		createAccountTabRepository.createAccountEmail.click();
		createAccountTabRepository.createAccountEmail.sendKeys(emailAddress);
		
		// Enter phone number
		createAccountTabRepository.createAccountPhoneCountry.click();
		createAccountTabRepository.createAccountPhoneCountryToChoose.click();
		createAccountTabRepository.createAccountPhone.click();
		createAccountTabRepository.createAccountPhone.sendKeys(phoneNumber);

		// Enter password
		createAccountTabRepository.createAccountPassword.click();
		createAccountTabRepository.createAccountPassword.sendKeys(password);

		// Click 'Create Account' button
		createAccountTabRepository.createAccountButton.click();

		// Verify account has been created
		boolean accountCreated = loginPage.verifyLogin(name);
		System.out.println("Account created for 'User' = '" + name + "' = '" + accountCreated + "'");

		// Return
		return accountCreated;
	}
}
