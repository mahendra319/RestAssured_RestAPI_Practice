package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETResponseXML {
	
	
	@Test
	public void get_UserResponse_XML_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String res = given().log().all()
			//.contentType("application/json")
			.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.header("Accept", "application/xml")
			//.accept(ContentType.XML)
		.when().log().all()
			.get("/public-api/users")
		.thenReturn()
			.asString();
//		.then()
//			.contentType(ContentType.XML)
//			.extract().response();
		
		//System.out.println("status Code is: "+res.getStatusCode());
		System.out.println(res);
			
	}
}
