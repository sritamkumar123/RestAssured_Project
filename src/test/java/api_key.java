import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class api_key {
	
	@Test
	
	public void apiKey_method() {
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://api.openweathermap.org");
		reqSpec.basePath("/data/2.5/weather");
		reqSpec.queryParam("q", "jaipur").queryParam("appid", "bcd2aa51b0fca3bca2f5f09747ef0135");
		
		Response res = reqSpec.get();
		Assert.assertEquals(res.statusCode(), 200, "Status code is not matching");
		
		JsonPath jsonPath = res.jsonPath();
		String desc = jsonPath.get("weather[0].description");
		String location = jsonPath.get("name");
		
		
		System.out.println(res.body().asString());
		System.out.println(desc);
		System.out.println(location);
		
		
		
	}
	

}
