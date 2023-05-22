package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.rest.api.authtoken.Oauth2TokenGeneration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Oauth2ApiTest {
	
	
	@Test
	public void checkOauthApiTest() {
		
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
		
		RequestSpecification request = RestAssured.given()
			.auth()
				.oauth2("556b59550001a653f792785c088b7cb1f43d48bd");
		
		Response response = request.post("/api/3613/chickens-feed"); //3616 is useid from coop.apps site login with Kamm.mahi
		
		
		System.out.println("Status code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	
	@Test
	public void getOauth2TokenApiTest() {
		
		//Generating token during runtime
//		RequestSpecification request1= RestAssured.given()
//						.formParam("client_id", "RestAssuredApiPractice")
//						.formParam("client_secret", "e7050225b83003bbca75636c0adfea4f")
//						.formParam("grant_type", "client_credentials");
//		
//		Response res1 = request1.post("http://coop.apps.symfonycasts.com/token");
//		
//		System.out.println(res1.getStatusCode());
//		System.out.println(res1.prettyPrint());
//		
//		String token = res1.jsonPath().getString("access_token");
//		System.out.println("Access Token is: "+token);
		
		String tokenId = Oauth2TokenGeneration.oAuth2_Toekn();
		System.out.println("Access token is: "+tokenId);
		
		//passing token
		
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
		
		RequestSpecification request = RestAssured.given()
			.auth()
				.oauth2(tokenId);
		
		Response response = request.post("/api/3613/chickens-feed"); 
		
		System.out.println("Status code is: "+response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	
	
	
	
	
	

}
