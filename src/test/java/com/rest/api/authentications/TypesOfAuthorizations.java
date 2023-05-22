package com.rest.api.authentications;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TypesOfAuthorizations {
	
	//Types of Authorization in RestAssured
		
//1. Basic Authorization
	
	@Test
	public void basic_auth_api_Test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		given().log().all()
			.auth()
				.basic("admin", "admin")
		.when().log().all()
			.get("/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
//2. Preemptive Authorization
	
	@Test
	public void preemptive_auth_api_Test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		given().log().all()
			.auth()
				.preemptive()
					.basic("admin", "admin")
		.when().log().all()
			.get("/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
//3. Digest Authorization
	
	@Test
	public void digest_Auth_Api_Test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		given().log().all()
			.auth()
				.digest("admin", "admin")
		.when().log().all()
			.get("/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
//4. Form Authorization
	
	@Test
	public void form_Auth_Api_Test() {
		
		RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.auth()
				.form("Admin", "admin123", new FormAuthConfig("/index.php/auth/validateCredentials", "txtUsername", "txtPassword"))
		.when().log().all()
			.get("/index.php/auth/validateCredentials")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
//5. oAuth1 Authorization
	
	@Test
	public void oAuth1_Auth_Api_Test() {
		
		given()
			.auth()
				.oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		.when()
			.get()
		.then()
			.assertThat();

		
		
	}
	
//6. Oauth2 Authorization
	
	@Test
	public void oAuth2_Auth_Api_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("/public/v2/users/")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
