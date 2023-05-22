package com.rest.api.post;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostApi_POJO {

	
	
	//POJO - Plain Old Java Object: is a java class defined with variables, methods in the form of getters and setters (encapsulation)
	
	//ex: create User
			// POST call
			//Request Json Body - Use Java class (POJO) - convert into Json object
	
	@Test
	public void createUser_POJO_template_Test() {
		
//create object for User java class
		User user = new User("Mahika3","active","female","mahika3@123.com");
		
//Convert Java POJO into Json/Text/XML/javaScript/html - this concept called Serialization - Using JACKSON api is used for serialization
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson=null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(userJson);
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body(userJson)
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.statusCode(201)
			.and()
				.body("name", equalTo(user.getName()))
				.body("status", equalTo(user.getStatus()))
				.body("email", equalTo(user.getEmail()));
			
				
		
		
		
		
	}
}
