package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.client.RESTClient;
import com.qa.util.TestUtil;



public class GetAPITest extends Base {

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


	@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException, JSONException {



		restClient = new RESTClient();
		closeableHttpResponse= restClient.get(url);


		//Status code

		int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();		//Get status code
		System.out.println("Status Code--->"+statusCode);

		Assert.assertEquals(statusCode, response_status_code_200,"Status code is not 200");

		//JSONresponse
		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");			//get string response(json)in proper format

		JSONObject responseJson=new JSONObject(responseString);			//convert string back to JSON
		System.out.println("Response JSON from API--->"+responseJson);

		//Assert single value
		String perPage =TestUtil.getValueByJpath(responseJson,"/per_page"); 
		System.out.println("per Page value--->"+perPage);	
		Assert.assertEquals(perPage, "3","Per Page value is not 3");


		//Assert data array value
		String lastname =TestUtil.getValueByJpath(responseJson,"/data[0]/last_name"); 
		System.out.println("Last name value--->"+lastname);	
		Assert.assertEquals(lastname, "Bluth","Last name is not Bluth");

		String id =TestUtil.getValueByJpath(responseJson,"/data[0]/id"); 
		System.out.println("Id value--->"+id);	
		Assert.assertEquals(id, "1","Id is not 1");
		
		String firstname =TestUtil.getValueByJpath(responseJson,"/data[0]/first_name"); 
		System.out.println("First name value--->"+firstname );	
		Assert.assertEquals(firstname, "George","First name is not Bluth");
		
		String avatar =TestUtil.getValueByJpath(responseJson,"/data[0]/avatar"); 
		System.out.println("Avatar value--->"+avatar);	
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg","Avatar is incorrect");

		//Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders= new HashMap<String, String>();

		for(Header header:headerArray) {

			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Header Array--->"+allHeaders);
		JSONObject responseHeader=new JSONObject(allHeaders);
		String Server =TestUtil.getValueByJpath(responseHeader,"/Server"); 
		System.out.println("Server value--->"+Server);	
		Assert.assertEquals(Server, "cloudflare","Server is incorrect");

	}

	@Test(priority=2)
	public void getAPITestwithHeaders() throws ClientProtocolException, IOException, JSONException {
		restClient = new RESTClient();
		
		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type","application/json");                 //parse header		
		
		
		
		closeableHttpResponse= restClient.get(url, headerMap);


		//Status code

		int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();		//Get status code
		System.out.println("Status Code--->"+statusCode);

		Assert.assertEquals(statusCode, response_status_code_200,"Status code is not 200");

		//JSONresponse
		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");			//get string response(json)in proper format

		JSONObject responseJson=new JSONObject(responseString);			//convert string back to JSON
		System.out.println("Response JSON from API--->"+responseJson);

		//Assert single value
		String perPage =TestUtil.getValueByJpath(responseJson,"/per_page"); 
		System.out.println("per Page value--->"+perPage);	
		Assert.assertEquals(perPage, "3","Per Page value is not 3");


		//Assert data array value
		String lastname =TestUtil.getValueByJpath(responseJson,"/data[0]/last_name"); 
		System.out.println("Last name value--->"+lastname);	
		Assert.assertEquals(lastname, "Bluth","Last name is not Bluth");

		String id =TestUtil.getValueByJpath(responseJson,"/data[0]/id"); 
		System.out.println("Id value--->"+id);	
		Assert.assertEquals(id, "1","Id is not 1");
		
		String firstname =TestUtil.getValueByJpath(responseJson,"/data[0]/first_name"); 
		System.out.println("First name value--->"+firstname );	
		Assert.assertEquals(firstname, "George","First name is not Bluth");
		
		String avatar =TestUtil.getValueByJpath(responseJson,"/data[0]/avatar"); 
		System.out.println("Avatar value--->"+avatar);	
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg","Avatar is incorrect");

		//Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders= new HashMap<String, String>();

		for(Header header:headerArray) {

			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Header Array--->"+allHeaders);
		JSONObject responseHeader=new JSONObject(allHeaders);
		String Server =TestUtil.getValueByJpath(responseHeader,"/Server"); 
		System.out.println("Server value--->"+Server);	
		Assert.assertEquals(Server, "cloudflare","Server is incorrect");
		
		
		
		
		
		
	}
}
