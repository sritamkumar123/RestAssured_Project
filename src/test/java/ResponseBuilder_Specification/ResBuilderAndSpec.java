package ResponseBuilder_Specification;


import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResBuilderAndSpec {
	
	// Using ResSpec and ResSpecBuilder we cab make our code modular
	
	ResponseSpecification resSpec;
	
	@BeforeClass
	public void createBuilder() {
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
		resBuilder.expectContentType(ContentType.JSON);
		resBuilder.expectStatusLine("HTTP/1.1 200 OK");
		resBuilder.expectResponseTime(Matchers.lessThan(3000L));
		resSpec = resBuilder.build();
	}

	@Test
	public void validateResponse() {
		
		Resonse res = RestAssured.given()
		.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON)
		.when().get("/2")
		.then().spec(resSpec).extract().response();

		res.prettyPrint();	
		
	}
	
	@Test
	public void validateResponseWithRegularApproach() {
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON);
		
		Response res = reqSpec.get("/2");
		
		res.then().spec(resSpec);
		res.prettyPrint();
	}
}
