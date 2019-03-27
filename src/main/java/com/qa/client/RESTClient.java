package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RESTClient {

	//1.GET Method without headers

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient= HttpClients.createDefault();  				//create a http connectio
		HttpGet httpGet = new HttpGet(url);											//create a connection with url
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet);	//hit the url

		return closeableHttpResponse;
	}


	//1.GET Method with headers

	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient= HttpClients.createDefault();  				//create a http connection
		HttpGet httpGet = new HttpGet(url);											//create a connection with url

		for(Map.Entry<String, String> entry: headerMap.entrySet()){
			httpGet.addHeader(entry.getKey(),entry.getValue());

		}

		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet);	//hit the url

		return closeableHttpResponse;

	}  

	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);  //send post request
		httppost.setEntity(new StringEntity(entityString));  //to pass json
		
		//to pass headers
		for(Map.Entry<String, String> entry: headerMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());

		}
		CloseableHttpResponse closeableHttpResponse=httpClient.execute(httppost);
		return closeableHttpResponse;
	}
	
	
	
	
	

	//		//Status code
	//		
	//		int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();		//Get status code
	//		System.out.println("Status Code--->"+statusCode);
	//		
	//		//JSONresponse
	//		String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");			//get string response(json)in proper format
	//		
	//		JSONObject responseJson=new JSONObject(responseString);			//convert string back to JSON
	//		System.out.println("Response JSON from API--->"+responseJson);
	//		
	//		//Headers
	//		
	//		Header[] headerArray = closeableHttpResponse.getAllHeaders();
	//		
	//		HashMap<String,String> allHeaders= new HashMap<String, String>();
	//		
	//		for(Header header:headerArray) {
	//			
	//			allHeaders.put(header.getName(), header.getValue());
	//		}
	//		
	//		System.out.println("Header Array--->"+allHeaders);
}








