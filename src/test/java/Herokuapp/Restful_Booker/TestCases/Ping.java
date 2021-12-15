package Herokuapp.Restful_Booker.TestCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Herokuapp.Restful_Booker.Utilities.Log;
import io.restassured.response.Response;
import Herokuapp.Restful_Booker.Utilities.BaseTest;


public class Ping extends BaseTest {
	
	@Test(description= "To check if application can be pingged.")
	public void pingApplication() {
		
		Log.startTestCase("Starting the test to ping application");
		/*******************************************************
		 * Send a GET request to /ping/
		 * and check that the response has HTTP status code 201
		 ******************************************************/
		
		
		Log.info("Ping the application");
		Response response =  given().
				spec(requestSpec).
				when().
				get("/ping");
		
		Log.info("Asserting the response if the status code returned is 201 as this is a ping request");
		response.then().assertThat().statusCode(201);	
		
		
	}

}
