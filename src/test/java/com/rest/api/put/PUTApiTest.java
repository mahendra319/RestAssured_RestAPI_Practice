package com.rest.api.put;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PUTApiTest {
	
	
	@Test
	public void update_PUT_Api_Test() {
		
		//User Java class accessing for POJO
		User user = new User("Karthi2","active","female","karthi2@abc.com");
		
	//Convert Java POJO into Json	
		ObjectMapper mapper = new ObjectMapper();
		String userJson=null;
		
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
//POST call
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		int userID = given().log().all()
			.contentType(ContentType.JSON)
			//.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body(userJson)
		.when().log().all()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(201)
				.contentType(ContentType.JSON)
			.and()
				.extract().path("id");
				
		System.out.println("User id is: "+userID);
		
		
//PUT call
		user.setName("KarthikaR"); //updated user name using Setters 
		
		String updatedUserJson=null;
		try {
			updatedUserJson=mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		given().log().all()
			.contentType(ContentType.JSON)
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body(updatedUserJson)
		.when().log().all()
			.put("/public/v2/users/"+userID)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.and()
					.body("name",equalTo(user.getName()))
				.and()	
					.body("id", equalTo(userID))
				.and()
					.body("email", equalTo(user.getEmail()));
			
//GET call
		
		given().log().all()
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("/public/v2/users/"+userID)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.body("name", equalTo(user.getName()));
		
		
		
		
//Note: if it is POST call , do POST-->then -->GET call
		//if it is PUT call , do POST-->PUT-->GET call
		
	}
	
		

}
