package com.employee.testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;
import com.employee.utilities.ImpUtilities;

import io.restassured.RestAssured;



public class TC002PostRequest extends TestBase{
	
	
	JSONObject jsonObject=new JSONObject();
	
	@BeforeClass
	public void registerCustomer() throws InterruptedException
	{
		logger.info("***************************TC002 Starts ****************************************");
		jsonObject.put("FirstName", ImpUtilities.getFirstName());
		jsonObject.put("LastName", ImpUtilities.getLastName());
		jsonObject.put("UserName", ImpUtilities.getUserName());
		jsonObject.put("Password", ImpUtilities.getPassword());
		jsonObject.put("Email", ImpUtilities.getEmail());
				
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RestAssured.basePath="/register";
		request=RestAssured.given().contentType("application/json");
		request.body(jsonObject.toJSONString());
		response=request.post();
		Thread.sleep(5000);
	}
	
	
	@Test
	public void verifyStatusCode() throws InterruptedException
	{
		logger.info("*****************************Verify Status Code*********************************");
		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, 201);
		logger.info("Sttus Code is ==> " + statusCode );
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyResponseBody() throws ParseException, InterruptedException
	{
		logger.info("******************************verifyResponseBody********************************");
		String responseBody=response.asString();
		Assert.assertTrue(response.asString()!=null);
		logger.info("REsponse Body is ==> " + responseBody);
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser.parse(response.asString());
		
		String successCode=(String)jsonObject.get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		String message=(String) jsonObject.get("Message");
		Assert.assertEquals(message, "Operation completed successfully");
		Thread.sleep(3000);
	
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		logger.info("*****************************TC002 Ends****************************************");
		Thread.sleep(3000);
	}
	

}
