import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class auth_2 {
	
	static String authToken;
	
	@Test
	void auth_2_method(){
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://api-m.sandbox.paypal.com").basePath("/v1/oauth2/token");
		reqSpec.auth().preemptive().basic("AY3dWRI7CREDUl1qiNXuoNjIgWF9_QR6jb7oSchGK2c1fIsXz39Rwdf1jLYHxnzcN9v-3eq1Las6qa5X", "EL8DkAiQ71l9jAwPlXH8W5sAtqxfnT80rh5U9Sn0bV-r1jbjeF8nH96udnN-d5OQh9zDSKa7STsx7HkY");
		
		// Param function is added to pass values within the request body encoded tab
		Response res = reqSpec.param("grant_type", "client_credentials").post();
		
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200, "Staus code is not matching");
		JsonPath jsonPath = res.jsonPath();
		 authToken = jsonPath.get("access_token");
		 System.out.println(authToken);
		
	}
	
	@Test(dependsOnMethods="auth_2_method")
	public void get_invoice() {
//		Response res = RestAssured.given().header("Content-Type","application/json").header("Authorization","Bearer "+authToken.toString())
//		.get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true");
		
		Response res = RestAssured.given().auth().oauth2(authToken).contentType(ContentType.JSON)
				.get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true");
		
		Assert.assertEquals(res.statusCode(), 200,"Code are not matching");
		
		System.out.println("\n-------------------Printing invoice-----------------------");
		res.prettyPrint();
	}

}
