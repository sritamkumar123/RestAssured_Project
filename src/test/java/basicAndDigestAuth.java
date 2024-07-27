import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class basicAndDigestAuth {
	
	@Test
	void basicAuth() {
		RequestSpecification reqSpec = given();
		reqSpec.baseUri("https://postman-echo.com/");
		reqSpec.basePath("basic-auth/");
		
		// premptive is only applicale to basic auth and in other auth client need token from the server first and send the request with valid token
		
		Response res = reqSpec.auth().preemptive().basic("postman","password").get();
		
		System.out.println("status code is :"+ res.statusCode());
		System.out.println("response body is: "+res.body().asString());
		
		
		
		
	}
	@Test
	
	public void digestAuth() {
		RequestSpecification reqSpec = given();
		
		reqSpec.baseUri("https://httpbin.org");
		reqSpec.basePath("/digest-auth/undefined/sritam/sritam");
		
		Response res = reqSpec.auth().digest("sritam","sritam").get();
		
		System.out.println(res.body().asString());
		
		Assert.assertEquals(res.statusCode(), 200,"Status on digest auth is not matching");
	}

}
