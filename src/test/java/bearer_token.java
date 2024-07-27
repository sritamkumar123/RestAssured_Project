import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class bearer_token {
	
	@Test
	void bearer_method() {
		
		// create req spec
		
		RequestSpecification resSpec = given();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "sritam");
		jsonObj.put("gender","male");
		jsonObj.put("email", "sritamKumar5@gmail.com");
		jsonObj.put("status","Active");
		
		String authToken = "Bearer 1b5e664d3d874497a93f4505bbe99876faf7e5ae1a5f9309479ec817db975b8f";
		
		resSpec.baseUri("https://gorest.co.in").basePath("/public/v2/users").header("Authorization",authToken).contentType(ContentType.JSON).body(jsonObj.toJSONString());
		
		Response res = resSpec.post();
		
		Assert.assertEquals(res.statusCode(), 201, "Status code is not matching");
		
		System.out.println(res.body().asString());
		
		JsonPath jsonPath = res.jsonPath();
		int id = jsonPath.get("id");
		System.out.println(id);
		Assert.assertEquals(jsonPath.get("name"), "sritam","name is not matching");
		
		String email= jsonPath.get("email");
		
		System.out.println(email);
		
		
		
	}

}
