package Herokuapp.Restful_Booker.Utilities;



import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public abstract class BaseTest extends FrameworkUtility {

	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;

	@BeforeSuite
	public void setBaseURI() {
		DOMConfigurator.configure("log4j.xml");	

		Log.info("The base URI is : "+readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder().
				setBaseUri(readConfigurationFile("Base_URI")).
				build();

	}

	@BeforeMethod
	public void beforeMethod() {
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();        
	}



}
