package com.employee.testcases;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;

public class TC004GetRequest extends TestBase {
	
	@BeforeClass
	public void getBookDetails() throws InterruptedException
	{
		logger.info("**************************TC004 Starts******************************");
		request=RestAssured.given().contentType("application/json");
		response=request.get("https://reqres.in/api/users?page=2");
		Thread.sleep(5000);
	}
	
	@Test
	public void verifyStatusCode() throws InterruptedException
	{
		logger.info("************************Verify Status Code*************************");
		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, 200);
		logger.info("Status Code is ==> " + statusCode);
		Thread.sleep(3000);
		
	}
	
	@Test
	public void verifyResponseBody() throws ParseException, InterruptedException
	{
		logger.info("*************************Verify REsponse Body *************************");
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=(JSONObject) jsonParser.parse(response.asString());
		
		long page=(Long) jsonObject.get("page");
		long per_page=(Long) jsonObject.get("per_page");
		long total=(Long) jsonObject.get("total");
		long total_pages=(Long) jsonObject.get("total_pages");
		
		logger.info("page==>" +page);
		logger.info("per_page==> " +per_page );
		logger.info("total==>" + total);
		logger.info("total_pages==> " + total_pages);
		
		JSONArray jsonArray=(JSONArray) jsonObject.get("data");
		
		Iterator<JSONArray> i=jsonArray.iterator();
		
		while(i.hasNext())
		{
			Map map=(Map) i.next();
			Set keys=map.keySet();
			for(Object key:keys)
			{
				logger.info(key+"========"+ map.get(key));
			}
		}
		
		
		Map map1=(Map) jsonObject.get("ad");
		
		Set s=map1.keySet();
		for(Object key:s)
		{
			logger.info(key +"========"+ map1.get(key));
		}
		
		Thread.sleep(3000);
		
		
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		logger.info("****************************TC004 Ends********************************************");
		Thread.sleep(3000);
	}

}
