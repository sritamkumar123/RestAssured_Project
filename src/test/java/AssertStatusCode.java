import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class AssertStatusCode {
	
	
	@Test (enabled= false)
	void assert_status() {
		baseURI="https://reqres.in/api/users/2";
		
		//Create request specification for the request
		RequestSpecification reqSpec = given();
		
		//call get method 
		Response res = reqSpec.get();
		
		// get response code 
		int actStatusCode=res.getStatusCode();
		
		System.out.println(res.getBody().asString());
		
		// Validate act with exp status code
		Assert.assertEquals(actStatusCode,200,"Code is not amtching on get request");
		
		String actStatusLine = res.getStatusLine();
		System.out.println(actStatusLine);
		Assert.assertEquals(actStatusLine,"HTTP/1.1 200 OK","code is not matching");
		
		
		
	}
	@Test(enabled=false)
	
	void validatableResponse() {
		baseURI = "https://reqres.in/api/users/2";
		RequestSpecification reqSpec = given();
		
		Response res = reqSpec.get();
		ValidatableResponse valRes=res.then();
		
		//status code 
		valRes.statusCode(200);
		//status Line
		
		valRes.statusLine("HTTP/1.1 200 OK");
		
	}
	@Test
	void bbdApproach() {
		given()
		
		.when().get("https://reqres.in/api/users/2")
		
		.then().statusCode(200).statusLine("HTTP/1.1 200 OK");
	}

}
