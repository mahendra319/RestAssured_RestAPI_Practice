package com.rest.api.get;



//RestAssured own't give automatic imports. we need to do two static imports manually. below are two 
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; // Hamcrest package is for Assertions/validation
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETBDDAPI {

	
	//RestAssured BDD approach - Given, When, And , Then
	@Test
	public void getApiCircuiteTest_1() {
		
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
}
	
	@Test
	public void getApiCircuiteTest_2() {
		
		Response response = 
		given()
		.when()
		.get("http://ergast.com/api/f1/2017/circuits.json");
		
		int statusCode = response.getStatusCode();
		System.out.println("Response status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
		
	}
	
	@Test
	public void getApiCircuiteTest_3() {
		
		RestAssured.baseURI ="http://ergast.com/";
		
		given()
		.when()
			.get("api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.statusCode(200)
		.and()
				.contentType(ContentType.JSON)
		.and()
				.header("Content-Length", equalTo("4552"));
		
	}
	
	@Test
	public void getJsonAPI_VerifyMd5Test() {
		
//		given().log().all()
//		.when().log().all()
//			.get("http://md5.jsontest.com?text=test") //passing query parameter - ? indicates query param and / indicates path param
//		.then().log().all()
//			.assertThat()
//				.body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
		
		
		//OR
		
		String pramValue = "test";
		String expectedMd5Val = "098f6bcd4621d373cade4e832627b4f6";
		
		given().log().all()
			.param("text", pramValue)
		.when().log().all()
			.get("http://md5.jsontest.com")
		.then()
			.assertThat()
				.body("md5", equalTo(expectedMd5Val));
		
	
	}
	
	
	@DataProvider(name="getTotalCircuitsByYear")
	public Object[][] getCircuitYearInfo() {
		return new Object[][] {
			
			{"2017",20},
			{"2016",21},
			{"2000",17},
			{"1966",9}
		};
		
}
	@Test(dataProvider = "getTotalCircuitsByYear")
	public void getCircuitesYearwiseInfoTest(String circuitYear, int totalCircuites) {
		
		
		given()
			.pathParam("raceYear", circuitYear)
		.when()
			.get("http://ergast.com/api/f1/{raceYear}/circuits.json")
		.then()
			.assertThat()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuites));
		
	}
	
	
	
	
}
