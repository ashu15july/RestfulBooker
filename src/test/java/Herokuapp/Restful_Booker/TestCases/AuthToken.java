package Herokuapp.Restful_Booker.TestCases;

import Herokuapp.Restful_Booker.Utilities.BaseTest;
import Herokuapp.Restful_Booker.Utilities.FrameworkConstants;
import Herokuapp.Restful_Booker.Utilities.Log;
import static io.restassured.RestAssured.given; //import this
import io.restassured.response.Response;


import org.json.simple.JSONObject;

public class AuthToken extends BaseTest {
	
    
	@SuppressWarnings("unchecked")
	public static String post_CreateAuth(){
		Log.startTestCase("Starting the test for POST method for create authentication");
		/*******************************************************
		 * Send a POST request to /auth
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		JSONObject jsonObject = returDefaultPayLoadObject(FrameworkConstants.POSTRequest_AUTH_DEFAULT_REQUEST);
		String username = readConfigurationFile("username");
		String password = readConfigurationFile("password");
		jsonObject.put("password", password);
		jsonObject.put("username", username);
		Log.info("Username from config file is : \n"+ username);
		Log.info("Password from config file is : \n"+ password);
		
		
		Response response = given().
								spec(requestSpec).
								contentType("application/json").
								body(jsonObject.toJSONString()).
							when().
								post("/auth");
		
		Log.info("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);
		
		
		String token = response.then().extract().path("token");
		return token;
	}
}
