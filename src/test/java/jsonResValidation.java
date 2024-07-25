import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class jsonResValidation {
	
	@Test
	public void jsonValiadtion() {
		
		RequestSpecification reqSpec = given();
		
		reqSpec.baseUri("https://reqres.in/");
		reqSpec.basePath("/api/users?page=2");
		
		//Added response object
		Response res=reqSpec.get();
		
		//read response body 
		ResponseBody resBody=res.getBody();
		String resString = resBody.asString();
		System.out.println("Response body "+ resString);
		
		Assert.assertEquals(resString.contains("George"),true,"Value is not found");
		
		//Added jsonpath object
		JsonPath jsonPath=resBody.jsonPath();
		String first_name= jsonPath.get("data[0].first_name");
		Assert.assertEquals(first_name,"George","Value of the firstname is not found");
		String mail = jsonPath.get("data[1].email");
		System.out.println(mail);
		
		
	}

}
