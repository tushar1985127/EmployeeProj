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
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class TC001GetRequest extends TestBase {
	
	@BeforeClass
	public void getWhetherDetails() throws InterruptedException
	{
		
		logger.info("***********************************TC001 Starts********************************");
		String cityName="MUMBAI";
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather";
		RestAssured.basePath="/city/"+cityName;
		request=RestAssured.given().contentType("application/json");
		response=request.get();
		
		Thread.sleep(5000);
	}
	@Test
	public void verifyStatusCode() throws InterruptedException
	{
		logger.info("*****************************verifyStatusCode**********************************");
		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, 200);
		logger.info("Status Code==>" + statusCode);
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyStatusLine() throws InterruptedException
	{
		logger.info("*******************************verifyStatusLine*****************************");
		String statusLine=response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		logger.info("Status Line is ==> " + statusLine);
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyResponseBody() throws ParseException, InterruptedException
	{
		logger.info("*******************************verifyResponseBody*****************************");
		String responseBody=response.asString();
		Assert.assertTrue(response.asString()!=null);
		logger.info("Response Body is ==> " + response.asString());
		
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser.parse(response.asString());
		
		String city=(String) jsonObject.get("City");
		Assert.assertEquals(city,"Mumbai");
		
		String weatherDescription=(String) jsonObject.get("WeatherDescription");
		Assert.assertEquals(weatherDescription, "smoke");
		logger.info("city==>" + city +"======"+ "weatherDescription==>" + weatherDescription);
		Thread.sleep(3000);
		
	}
	
	
	@Test
	public void verifyContentLength() throws InterruptedException
	{
		logger.info("***********************************verifyContentLength*******************************");
		String contentLength=response.getHeader("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<166);
		logger.info("Content Length is ==>" + contentLength);
		Thread.sleep(3000);
		
	}

	@Test
	public void verifyContentEncoding() throws InterruptedException
	{
		logger.info("*******************************verifyContentEncoding***********************************");
		String contentEncoding=response.getHeader("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
		logger.info("contentEncoding==>" + contentEncoding);
		Thread.sleep(3000);
	}
	
	@Test
	public void fetchHeaders() throws InterruptedException
	{
		logger.info("*****************************fetchHeaders********************************************");
		Headers headers=response.headers();
		
		for(Header h:headers)
		{
			logger.info(h.getName() +"======"+ h.getValue());
		}
		
		Thread.sleep(3000);
		
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		logger.info("****************************TC001 Ends*********************************************");
		Thread.sleep(3000);
	}
	
}
