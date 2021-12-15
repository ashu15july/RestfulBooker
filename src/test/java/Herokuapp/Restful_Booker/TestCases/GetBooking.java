package Herokuapp.Restful_Booker.TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Herokuapp.Restful_Booker.Utilities.BaseTest;
import Herokuapp.Restful_Booker.Utilities.ExcelUtils;
import Herokuapp.Restful_Booker.Utilities.Log;

import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;
import pojoClasses.BookingDetails;


public class GetBooking extends BaseTest {

	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelUtils xl = new ExcelUtils("GetBooking", this.getClass().getSimpleName());
		return xl.getTestdata();
	}

	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs") 
	public void getBookingIDs( 
			String firstname, 
			String lastname,
			String totalprice, 
			String depositpaid, 
			String checkin, 
			String checkout, 
			String additionalneeds, String id
			){

		Log.startTestCase("Starting the test to get booking details");
		/*******************************************************
		 * Send a GET request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/

		//Sending the GET request for a specific booking id and receiving the response
		Log.info("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				pathParam("id", id).
				when().
				get("/booking/{id}");

		//Verify the response code
		Log.info("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);


		//Using the POJO class
		Log.info("Asserting of response body with the data from test data excel");

		BookingDetails bookingDetails = response.as(BookingDetails.class);

		Assert.assertEquals(bookingDetails.getFirstname(), firstname);
		Assert.assertEquals(bookingDetails.getLastname(), lastname);
		Assert.assertEquals(bookingDetails.getTotalprice(), totalprice);
		Assert.assertEquals(bookingDetails.getDepositpaid(), depositpaid);
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), checkin);
		Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), checkout);

	}
}
