package com.rest.api.delete;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPITest {
	
	@Test
	public void delete_Api_Test() {
		
		
//Creating Object for POJO class		
		User user = new User("Mahi","active","male","mahi@gmail.com");

//Convert Java POJO into Json 
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		
		try {
			userJson=mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//POST Call
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body(user)
		.when().log().all()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(201)
				.contentType(ContentType.JSON)
				.extract().path("id");
		
		System.out.println("User id is: "+ userId);
		
//GET call
		
		given().log().all()
			.contentType(ContentType.JSON)
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body("name", equalTo(user.getName()))
				.and()
				.body("gender", equalTo(user.getGender()));
		
		
//DELETE call
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.delete("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(204);
		
		
//GET call
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			
			
		.when().log().all()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(404)
				.body("message", equalTo("Resource not found"));
		
		
		
		
		
		
		
		
	}

}
