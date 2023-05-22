package com.rest.api.get;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	

	ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
	ResponseSpecification resSpec = specBuilder.expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	


@Test
public void responseSpecTest() {
	
	RestAssured.baseURI = "https://gorest.co.in";
	given()
		.header("Authorization","Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
	.when()
		.get("/public/v2/users")
	.then()
		.assertThat()
			.spec(resSpec);
}


}
