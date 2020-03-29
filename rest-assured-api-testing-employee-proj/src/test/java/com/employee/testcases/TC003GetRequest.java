package com.employee.testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.employee.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class TC003GetRequest extends TestBase{
	
	@BeforeClass
	public void getAuthentication() throws InterruptedException
	{
		logger.info("*****************************TC003 Starts*****************************");
		PreemptiveBasicAuthScheme basicauth=new PreemptiveBasicAuthScheme();
		basicauth.setUserName("ToolsQA");
		basicauth.setPassword("TestPassword");
		
		RestAssured.authentication=basicauth;
		request=RestAssured.given().contentType("application/json");
		response=request.get("http://restapi.demoqa.com/authentication/CheckForAuthentication");
		Thread.sleep(5000);
	}
	
	@Test
	public void verifyStatusCode() throws InterruptedException
	{
		logger.info("************************verifyStatusCode**********************************");
		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, 200);
		logger.info("Status Code is ==> " + statusCode);
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyResponseBody() throws ParseException, InterruptedException
	{
		logger.info("**************************verifyResponseBody*******************************");
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser.parse(response.asString());
		
		String responseBody=response.asString();
		logger.info("Repsonse Body is ==> " +responseBody);
		
		String faultId=(String) jsonObject.get("FaultId");
		Assert.assertEquals(faultId, "OPERATION_SUCCESS");
		
		String fault=(String) jsonObject.get("Fault");
		Assert.assertEquals(fault, "Operation completed successfully");
		
		logger.info("faultId==> " + faultId +"======="+ "fault==>" + fault);
		Thread.sleep(3000);
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		logger.info("*******************************************TC003 Ends************************************************************");
		Thread.sleep(3000);
	}

}
