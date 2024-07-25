import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Test_get_method {
	
	@Test
	 void test01() {
		
	 Response res = get("https://reqres.in/api/users?page=2");
	 System.out.println("response code "+  res.getStatusCode());
	 System.out.println("response body "+  res.getBody().asString());
	 System.out.println("response body "+  res.getTime());
	 System.out.println("response body "+  res.getHeader("Content-Type"));
	 
	 int statusCode = res.getStatusCode();
	 Assert.assertEquals(200, statusCode);
		
	}
	@Test
	void test02() {
		// given when and then 
		baseURI="https://reqres.in/api/users";
		given()
		.queryParam("page", "2")
		.when().get().
		then().statusCode(200).log().all();
		
		
	}
	@Test
	void test03() {
		baseURI = "https://reqres.in/api/users/2";
		given()
		.when().get().
		then().statusCode(200).log().all();
	}

}
