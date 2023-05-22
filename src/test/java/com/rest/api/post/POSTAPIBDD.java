package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIBDD {
	
	
	@Test
	public void tokenPOSTBDDAPI_JSON_StringBody_Test() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		given()
			.contentType(ContentType.JSON)
			.body("{\"username\" : \"admin\",\"password\" : \"password123\"}") //passing String json body
		.when()
			.post("/auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	@Test
	public void tokenPOSTBDDAPI_JSON_FileBody_Test() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId =given()
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\java\\com\\rest\\api\\post\\datafiles\\Credentials_AuthToken.Json")) //passing File body
		.when()
			.post("/auth")
		.then().log().all()
			.extract().path("token");
		
//			.assertThat()
//				.statusCode(200);
		
		System.out.println("Token id is: "+tokenId);
		
		Assert.assertNotNull(tokenId);
			
	}
	
	
	@Test
	public void createUser_POST_API_JsonString_Test() {
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body("{\"name\":\"Samhitha\",\"email\":\"samhitha@xyz.com\",\"gender\":\"female\",\"status\":\"active\"}")
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.body("status", equalTo("active"));
		
	}
	

}
