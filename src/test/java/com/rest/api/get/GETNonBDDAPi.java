package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//static imports are only when we use BDD approach. For non BDD approach import will automatically download

public class GETNonBDDAPi {
	
	
	@Test
	public void get_User_NonBDD_Test() {
		//Prepare request -> Hit the API -> Get the response -> Fetch the values from response
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification reqSpec = RestAssured.given();
		//reqSpec.auth().oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
								//OR
		reqSpec.header("Authorization", "Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
		
		Response response = reqSpec.get("/public/v2/users");
		
		System.out.println(response.prettyPrint());
		System.out.println("Status code is: "+response.getStatusCode());
		
		
	}
	
	//Passing Query parameters along with Non BDD approach
	@Test
	public void get_User_NonBDD_withQueryParams_Test() {
		//Prepare request -> Hit the API -> Get the response -> Fetch the values from response
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification reqSpec = RestAssured.given();
		//reqSpec.auth().oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
								//OR
		reqSpec.header("Authorization", "Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
		reqSpec.queryParam("name", "Vyas");
		reqSpec.queryParam("status", "active");
		
		Response response = reqSpec.get("/public/v2/users");
		
		System.out.println(response.prettyPrint());
		System.out.println("Status code is: "+response.getStatusCode());
		
		
	}
	
	//Passing Query parameters using HashMap and getting values from response using JsonPath
	
			//HashMap is non-synchronized, which means it will attach the map to all tests irrespective of action.
				//therefore HashMap is not suitable for multithreading , instead we can use HashTable which is synchronized.
				//HashMap allows Null key and Null values, where as HashTable not allow Null keys and Null values.
	@Test
	public void get_User_NonBDD_HashMap_QueryParams_Test() {
		//Prepare request -> Hit the API -> Get the response -> Fetch the values from response
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RequestSpecification reqSpec = RestAssured.given();
		//reqSpec.auth().oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
								//OR
		reqSpec.header("Authorization", "Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
		
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Vyas");
		params.put("gender", "female");
		params.put("status", "active");
		
		reqSpec.queryParams(params);
		
		
		
		Response response = reqSpec.get("/public/v2/users");
		
		JsonPath jp = response.jsonPath();
		String uName = jp.get("name[0]");
		int uId = jp.get("id[0]");
		System.out.println("First name from the list is: "+uName);
		System.out.println("First id from the list is: "+uId);
		
		System.out.println(response.prettyPrint());
		System.out.println("Status code is: "+response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
