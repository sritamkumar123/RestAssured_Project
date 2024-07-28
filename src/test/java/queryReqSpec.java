import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class queryReqSpec {

	@Test
	public void queryReqSpec_method() {
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://gorest.co.in")
		.basePath("/public/v2/users")
		.contentType(ContentType.JSON)
		.header("sample haeder","added");
		
		QueryableRequestSpecification queryReqSpec =SpecificationQuerier.query(reqSpec);
		System.out.println(queryReqSpec.getBaseUri());
		System.out.println(queryReqSpec.getBasePath());
		//System.out.println(queryReqSpec.getBody()); // It is a get request so req body is associated with it
		
		Headers headers = queryReqSpec.getHeaders();
		
		for(Header h: headers) {
			System.out.println("header key :"+ h.getName()+"\t"+"header value :"+h.getValue());
		}
		
		Response res = reqSpec.get();
		System.out.println("\n");
		System.out.println("-----------------Response Body Printed----------------------");
		res.prettyPrint();
		
		
	}
}
