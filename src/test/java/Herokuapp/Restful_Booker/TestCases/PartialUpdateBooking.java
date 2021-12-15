package Herokuapp.Restful_Booker.TestCases;


import org.testng.annotations.Test;
import Herokuapp.Restful_Booker.Utilities.BaseTest;
import Herokuapp.Restful_Booker.Utilities.Log;
import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;


public class PartialUpdateBooking extends BaseTest {
	
	@Test(description="To update the details of the booking IDs") 
	public void updateBooking(){
		
		Log.startTestCase("Starting the test to update details");
		/*******************************************************
		 * Send a PUT request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the PUT request for a specific booking id and receiving the response after updating the detals
		Log.info("PUT update booking detail");
		
		//To get the auth token
		String newAuthToken = AuthToken.post_CreateAuth();
		Log.info("Auth token is : "+newAuthToken);
		
		//Created a new booking
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Sanda", "Varys", "114", "false", "2018-01-03", "2018-01-05", "Dinner");
		String IDtoUpdate = createBooking.newID;
		Log.info("New Booking ID created is : "+IDtoUpdate);
		
		//Setting the value to be sent in the patch request
		String setValue = "{\"firstname\":\"sam\"}";
		
		String cookieValue = "token="+newAuthToken;
		System.out.println("cookieValue is :"+cookieValue);
		
		//Sending the PATCH request
		Log.info("Sending the PATCH request to partially update the booking detail of booking id : "+IDtoUpdate);
		Response response = given().
			spec(requestSpec).
			header("Content-Type", "application/json").
			header("Accept", "application/json").
			header("Cookie", cookieValue).
	        pathParam("id", IDtoUpdate).
	        body(setValue).log().body().
	    when().
			patch("/booking/{id}");
		
		//Verify the response code
		Log.info("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
	}
}
