package Herokuapp.Restful_Booker.TestCases;

import org.testng.annotations.Test;

import Herokuapp.Restful_Booker.Utilities.BaseTest;
import Herokuapp.Restful_Booker.Utilities.Log;

import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;



public class DeleteBooking extends BaseTest {

	@Test(description="To delete the details of the booking IDs") 
	public void getBookingIDs(){
		
		Log.startTestCase("Starting the test to delete booking details");
		/*******************************************************
		 * Send a DELETE request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		//To get the auth token
		String newAuthToken = AuthToken.post_CreateAuth();
		Log.info("Auth token is : "+newAuthToken);
		String cookieValue = "token="+newAuthToken;
		
		//Created a new booking, this will be subsequently deleted
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Sanda", "Jon", "114", "false", "2018-01-03", "2018-01-05", "Dinner");
		String IDtoUpdate = createBooking.newID;
		Log.info("New Booking ID created is : "+IDtoUpdate);
		Log.info("Booking ID to be deleted is : "+IDtoUpdate);
		
		//Sending the GET request for a specific booking id and receiving the response
		Log.info("Sending the GET request for a specific booking id and receiving the response");
		Response response = given().
				spec(requestSpec).
				header("Content-Type", "application/json").
				header("Cookie", cookieValue).
				pathParam("id", IDtoUpdate).
			when().
				delete("/booking/{id}");
		
		//Verify the response code
		Log.info("Asserting the response if the status code returned is 201 as this is a Delete request");
		response.then().assertThat().statusCode(201);		

		//To log the response to report
		logResponseAsString(response);	
		
	}
}
