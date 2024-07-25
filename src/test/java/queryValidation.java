import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class queryValidation {
	
	@Test
	
	public void filterItems() {
		
		RequestSpecification reqSpec= given();
		reqSpec.baseUri("https://reqres.in/");
		reqSpec.basePath("/api/users");
		reqSpec.queryParam("page",2).queryParam("id", 9);
		Response res = reqSpec.get();
		System.out.println(res.getBody().asString());
		
		JsonPath jsonpath = res.jsonPath();
		
		String email = jsonpath.get("data.email");
		String avatar = jsonpath.get("data.avatar");
		
		System.out.println(avatar);
		Assert.assertEquals(email, "tobias.funke@reqres.in","Expected mail is not received");
		
	}

	
}
