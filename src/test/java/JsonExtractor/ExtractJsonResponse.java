package JsonExtractor;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.lessThan;

public class ExtractJsonResponse {

	@Test(enabled=false)
	public void extractResponse() {
		String obj = RestAssured.given()
		.baseUri("https://reqres.in/api").basePath("/users")
		.when().get("/2").then().extract().jsonPath().get("data").toString();
		
		System.out.println(obj);
	}
	
    @Test(enabled=true)
    public void directTest() {
        Response res = RestAssured.get("https://reqres.in/api/users/2");
        res.prettyPrint();
        long responseTime = res.time();
        System.out.println(responseTime);
        
        res.then().assertThat().time(lessThan(3000L)); // Validate that the response time is less than 3000 milliseconds (3 seconds)
    }
	
}
