package com.vitamojo.login;

import java.util.Random;
import org.testng.annotations.Test;
import com.vitamojo.utils.Utils;
import io.restassured.http.ContentType;

/**
 * Tests for login.
 */
public class LoginTest {

	// Variables for testing
	String urlRegisterUser = "https://vmos2.vmos-demo.com/user/v1/user";
	String urlLoginUser = "https://vmos2.vmos-demo.com/user/v1/auth";
	String tenant = "695a1486-80e7-4ee6-bc55-f4911944ef2a";
	int responseCode = 201;
	String password = "Test1234";
	String jsonRegisterUser = "{\"profile\":{\"firstName\":\"UNIQUE_VALUE\",\"phone\":\"+91 23001 UNIQUE_VALUE\"},\"email\":\"UNIQUE_VALUE@xxx.xom\",\"password\":\"" + password + "\",\"storeUUID\":\"ced48917-54c2-40ad-a646-5315f5dcb28f\",\"locale\":\"en-GB\"}";
	String jsonLoginUser = "{\"email\": \"UNIQUE_VALUE@xxx.xom\", \"password\": \"" + password +"\"}";
	int uniqNum = 0;
	
	// Initialize
	Utils utils = new Utils(ContentType.JSON, tenant);
	
	/**
	 * Set the request body.
	 */
	public void setup(){

		// Generate unique name and email address
		Random random = new Random();
		int lowerBound = 10000;
		int upperBound = 99999;
		uniqNum = random.nextInt(upperBound-lowerBound) + lowerBound;
		
		// Create the json request for registration
		jsonRegisterUser = jsonRegisterUser.replaceAll("UNIQUE_VALUE", Integer.toString(uniqNum));

		// Create the request json for login
		jsonLoginUser = jsonLoginUser.replaceAll("UNIQUE_VALUE", Integer.toString(uniqNum));
	}

	/**
	 * Test for user registration.
	 */
	@Test
	public void testRegisterUser()
	{
		System.out.println("############# START TEST: registerUser() ################");
		System.out.println("Register user");

		// Register user
		utils.post(urlRegisterUser, jsonRegisterUser, responseCode);

		System.out.println("############# END TEST: registerUser() ################");
	}

	/**
	 * Test for user login.
	 */
	@Test (dependsOnMethods = {"testRegisterUser"})
	public void testLoginUser()
	{
		System.out.println("############# START TEST: loginUser() ################");
		System.out.println("Login user");

		// Create user
		Utils utils = new Utils(ContentType.JSON, tenant);
		utils.post(urlLoginUser, jsonLoginUser, responseCode);
		
		System.out.println("############# END TEST: loginUser() ################");
	}
}