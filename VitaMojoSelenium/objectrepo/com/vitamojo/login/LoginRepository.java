package com.vitamojo.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Object repository for 'Login' page.
 */
public class LoginRepository {

	/*
	 * Webelements for 'login' page
	 */   
	public final String loginPageLinkXpath = "//a[text()='Login']";
	public final String loggedUserXpath = "//header//span[text()='DYNAMIC_VARIABLE']";
	public final String tabLoginXpath = "//li[text()='Login']";
	public final String tabCreateAccountXpath = "//li[text()='Create Account']";
	public final String logoutXpath = "//button[text()='Sign Out']";

	@FindBy(xpath = loginPageLinkXpath)
	public WebElement loginPageLink;
	
	@FindBy(xpath = loggedUserXpath)
	public WebElement loggedUser;

	@FindBy(xpath = tabLoginXpath)
	public WebElement tabLogin;

	@FindBy(xpath = tabCreateAccountXpath)
	public WebElement tabCreateAccount;
	
	@FindBy(xpath = logoutXpath)
	public WebElement logout;

	/**
	 * Initialize all elements in the 'Login' page repository.
	 * 
	 * @param driver
	 *            The driver for the Browser that will run the application
	 */
	public LoginRepository(WebDriver driver) {

		try {
			// Create all 'WebElements'
			PageFactory.initElements(driver, this);
		} catch (Exception ex) {

			// Log and throw the error
			// LogWrapper.logErrorMsgs(ex.getMessage());
			throw ex;
		}
	}


	/**
	 * Object repository of 'Login' tab.
	 */
	public static class LoginTabRepository {

		/*
		 * Webelements for 'Login' tab
		 */
		public final String loginEmailXpath = "//input[@id='email']";
		public final String stringXpath = "//input[@id='password']";
		public final String loginButtonXpath = "//button[text()='Login']";

		@FindBy(xpath = loginEmailXpath)
		public WebElement loginEmail;

		@FindBy(xpath = stringXpath)
		public WebElement loginPassword;

		@FindBy(xpath = loginButtonXpath)
		public WebElement loginButton;

		/**
		 * Initialize all elements in the 'login' tab repository.
		 * 
		 * @param driver
		 *            The driver for the Browser that will run the application
		 */
		public LoginTabRepository(WebDriver driver) {

			try {
				// Create all 'WebElements'
				PageFactory.initElements(driver, this);
			} catch (Exception ex) {

				// Log and throw the error
				// LogWrapper.logErrorMsgs(ex.getMessage());
				throw ex;
			}
		}
	}


	/**
	 * Object repository of 'Create Account' tab.
	 */
	public static class CreateAccountTabRepository {

		/*
		 * Webelements for 'Login' tab
		 */
		public final String createAccountNameXpath = "//input[@id='firstName']";
		public final String createAccountEmailXpath = "//input[@id='email']";
		public final String createAccountPhoneXpath = "//input[@id='phoneNumber']";
		public final String createAccountPhoneCountryXpath = createAccountPhoneXpath + "/../div";
		public final String createAccountPhoneCountryToChooseXpath = "//li/span[text()='India +91']";
		public final String createAccountPasswordXpath = "//input[@id='password']";
		public final String createAccountButtonXpath = "//button[text()='Create Account']";
		
		@FindBy(xpath = createAccountNameXpath)
		public WebElement createAccountName;

		@FindBy(xpath = createAccountEmailXpath)
		public WebElement createAccountEmail;
		
		@FindBy(xpath = createAccountPhoneXpath)
		public WebElement createAccountPhone;
		
		@FindBy(xpath = createAccountPhoneCountryXpath)
		public WebElement createAccountPhoneCountry;
		
		@FindBy(xpath = createAccountPhoneCountryToChooseXpath)
		public WebElement createAccountPhoneCountryToChoose;
		
		@FindBy(xpath = createAccountPasswordXpath)
		public WebElement createAccountPassword;

		@FindBy(xpath = createAccountButtonXpath)
		public WebElement createAccountButton;

		/**
		 * Initialize all elements in the 'Create Account' tab repository.
		 * 
		 * @param driver
		 *            The driver for the Browser that will run the application
		 */
		public CreateAccountTabRepository(WebDriver driver) {

			try {
				// Create all 'WebElements'
				PageFactory.initElements(driver, this);
			} catch (Exception ex) {

				// Log and throw the error
				// LogWrapper.logErrorMsgs(ex.getMessage());
				throw ex;
			}
		}
	}
}
