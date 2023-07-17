package com.vitamojo.utils;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/**
 * Utility for using RestAssured methods.
 */
public class Utils {

	// Variables
	ContentType contentType;
	String tenant = "";
	
	/**
	 * Constructor
	 * 
	 * @param json
	 * 		Content Type
	 * @param tenant
	 * 		Tenant
	 */
	public Utils(ContentType json, String tenant){
		// Set content type and tenant
		this.contentType = json;
		this.tenant =  tenant;
	}

	/**
	 * Post request.
	 */
	public void post(String url, String requestBody, int expectedResponseCode)
	{
		System.out.println(" START: post()");

		// Request
		RequestSpecification request= RestAssured.given();

		// Set content type as json
		request.contentType(ContentType.JSON);

		// Set header
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("tenant", tenant);
		request.headers(header);

		// Set URI
		request.baseUri(url);

		// Set body
		request.body(requestBody);

		// Call post for this request
		Response response = request.post();

		// Get the response
		System.out.println("url: " + url);
		System.out.println("Request: " + requestBody);
		System.out.println("Response: " + response.asString());

		// Get the validation to validate the response
		ValidatableResponse validatableResponse = response.then();

		// The expected status code is 201. If something else comes, then the test will fail
		validatableResponse.statusCode(expectedResponseCode);

		System.out.println("Response code: " + response.getStatusCode());
		System.out.println(" END: post()");
	}
}