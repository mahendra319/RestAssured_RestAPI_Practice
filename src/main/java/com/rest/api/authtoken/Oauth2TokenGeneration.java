package com.rest.api.authtoken;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Oauth2TokenGeneration {

	public static String oAuth2_Toekn() {
		
		RequestSpecification req = RestAssured.given()
						.formParam("client_id", "RestAssuredApiPractice")
						.formParam("client_secret", "e7050225b83003bbca75636c0adfea4f")
						.formParam("grant_type", "client_credentials");
		
		Response response = req.post("http://coop.apps.symfonycasts.com/token");
		return response.jsonPath().getString("access_token");
		
	}
	
}
