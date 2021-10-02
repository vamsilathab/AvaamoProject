package utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAppUtils {

	public static int getCurrentStatusCode(String currentPageUrl)
	{
		RestAssured.baseURI = currentPageUrl;
		Response response = RestAssured.given().get(RestAssured.baseURI);
		return response.getStatusCode();
	}
	
}
