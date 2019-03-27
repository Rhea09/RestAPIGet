package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Base;
import com.qa.client.RESTClient;
import com.qa.data.Users;

public class PostAPITest extends Base{


	Base base;
	RESTClient restClient;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		base =new Base();
		String serviceUrl= prop.getProperty("URL");
		String apiUrl= prop.getProperty("serviceURL");

		url =serviceUrl+apiUrl;


	}

	@Test 
	public void PostAPITest() throws ClientProtocolException, IOException, JSONException {

		restClient = new RESTClient();

		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type","application/json");   

		//generate json
		//jackson API marshening-------------------------------------------------------------------------------

		ObjectMapper mapper = new ObjectMapper();
		Users users =new Users("rhea","manager"); //expected obj

		//object to json
		mapper.writeValue(new File("C:\\Users\\Rhea\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);

		// object to json string
		String usersJsonString=mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		//-------------------------------------------------------------------------------------------------------
		closeableHttpResponse= restClient.post(url, usersJsonString, headerMap);

		int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();		//Get status code
		System.out.println("Status Code--->"+statusCode);

		Assert.assertEquals(statusCode, response_status_code_201,"Status code is not 200");

		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");			//get string response(json)in proper format

		JSONObject responseJson=new JSONObject(responseString);			//convert string back to JSON
		System.out.println("Response JSON from API--->"+responseJson);

		//unmarshening------------------------------------------------------------------------------------
		Users responseobj=mapper.readValue(responseString, Users.class); // unmarshening- json to object actual obj
		System.out.println(responseobj);

		Assert.assertTrue(users.getName().equals(responseobj.getName()),"Names is not created correct");

		Assert.assertTrue(users.getJob().equals(responseobj.getJob()),"Job is not created correct");
		//--------------------------------------------------------------------------------------------------
	}









}
