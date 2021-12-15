package Herokuapp.Restful_Booker.TestCases;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Herokuapp.Restful_Booker.Utilities.BaseTest;
import Herokuapp.Restful_Booker.Utilities.ExcelUtils;
import Herokuapp.Restful_Booker.Utilities.Log;
import static io.restassured.RestAssured.given; 
import io.restassured.response.Response;
import pojoClasses.BookingDates;
import pojoClasses.BookingDetails;


public class CreateBooking extends BaseTest {
	
	public static String newID = "";
	
	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelUtils xl = new ExcelUtils("CreateBooking", this.getClass().getSimpleName());
		return xl.getTestdata();
		
	}
    
	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs") 
	public void createNewBooking(String firstname, 
							  String lastname,
							  String totalprice, 
							  String depositpaid, 
							  String checkin, 
							  String checkout, 
							  String additionalneeds
							  ){
		
		Log.startTestCase("Starting the test to create new details");
		/*******************************************************
		 * Send a POST request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the GET request for a specific booking id and receiving the response
		Log.info("Posting a new booking detail");
		
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setFirstname(firstname);
		bookingDetails.setLastname(lastname);
		bookingDetails.setTotalprice(Integer.parseInt(totalprice));
		bookingDetails.setDepositpaid(Boolean.parseBoolean(depositpaid));
		bookingDetails.setAdditionalneeds(additionalneeds);
		
		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin(checkin);
		bookingDates.setCheckout(checkout);
		bookingDetails.setBookingdates(bookingDates);
				
		Log.info("Sending the POST request to create new booking");
		Response response = given().
								spec(requestSpec).
								contentType("application/json").
					            body(bookingDetails).log().body().
					        when().
					        	post("/booking");
		
		//Verify the response code
		Log.info("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
		
		//To get the newly created bookign id
		//System.out.println(response.then().extract().path("bookingid"));
		newID = response.then().extract().path("bookingid").toString();
		Log.info("Retrieved booking id : "+response.then().extract().path("bookingid"));
		
	}	
}
