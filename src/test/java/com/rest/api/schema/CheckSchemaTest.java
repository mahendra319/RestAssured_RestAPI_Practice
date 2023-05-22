package com.rest.api.schema;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class CheckSchemaTest {
	
	@Test
	public void booking_schema_validation_Test() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		given()
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\java\\com\\rest\\api\\post\\datafiles\\createBooking.json"))
		.when()
			.post("/booking")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.body(matchesJsonSchemaInClasspath("CreateBookingSchema.json"));
			
		
	}

}
