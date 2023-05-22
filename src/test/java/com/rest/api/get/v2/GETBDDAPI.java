package com.rest.api.get.v2;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETBDDAPI {

	//Rest Assured BDD keywords: Given, When, Then, And 
	
	
	
	@Test
	public void getApiTest1() {
		
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
			
		
	}
	
	
	@Test
	public void getApiTest2() {
		
		Response response = given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json");
		
		
		int statusCode =response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		System.out.println(response.prettyPrint());
		
		
	}
	
	
	@Test
	public void getApiTest3() {
		
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
		.when().log().all()
			.get("/api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length", equalTo("4552"));
		
		

		
		
	}
	
	
	@Test
	public void getApi_VerifyMd5Test() {
		
//		given()
//		.when()
//			.get("http://md5.jsontest.com?text=test")
//		.then()
//			.assertThat()
//				.body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
		
		
		//OR
		RestAssured.baseURI = "http://md5.jsontest.com";
		
		given().log().all()
			.queryParam("text", "test")
			//.pathParam(parameterName, parameterValue)
		.when()
			//.get("http://md5.jsontest.com")
		.get()
		.then().log().all()
			.assertThat()
				.body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
