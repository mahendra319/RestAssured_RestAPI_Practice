package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AuthApis {

	//basic Auth: we have to pass username and password
	
	@Test
	public void basicAuthApiTest() {
		
		given()
			.auth()
				.preemptive()
					.basic("admin", "admin")
		.when()
			.get("https://the-internet.herokuapp.com/basic_auth")
		.then()
			.assertThat()
				.statusCode(200);
		
	}
	
	//Oauth 2.0: we need to pass Access token as Authorization
	
	@Test
	public void oAuth2_Api_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		String nUser = "Sam";
		String userGender = "female";
		
		given().log().all()
			.auth()
				//.oauth2("Bearer fcc79b3bcda89dc8aa332d82d60e44dca9ed1e6d80af3748640c164621572669") 
							//OR
				.oauth2("fcc79b3bcda89dc8aa332d82d60e44dca9ed1e6d80af3748640c164621572669")
			.queryParams("name", nUser, "gender",userGender)
		.when().log().all()
			.get("/public/v2/users/")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	/**
	 * Oauth2.o - Bearer token passing with Header
	 */
	@Test
	public void oAuth_Api_Test_With_authHeader() {
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		given()
			.contentType("application/json")
			.header("Authorization","Bearer fcc79b3bcda89dc8aa332d82d60e44dca9ed1e6d80af3748640c164621572669")
		.when()
			.get("/public/v2/users/?name=Sam&gender=female")
		.then()
			.assertThat()
				.statusCode(200);
			
	}
	
	@Test
	public void oAuth_Api_With_TwoQueryParmeters_Test() {
RestAssured.baseURI ="https://gorest.co.in";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer fcc79b3bcda89dc8aa332d82d60e44dca9ed1e6d80af3748640c164621572669")
			.queryParam("name", "Sam")
			.queryParam("gender", "female")
		.when().log().all()
			.get("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	
	
	
	
	
	
}
