import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class mockCURDOpetaion {
	
	@BeforeClass
	public void setUpDefault() {
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("http://localhost:3000").basePath("/users")
		.contentType(ContentType.JSON);
		
		RestAssured.requestSpecification = reqSpec ; //to set the reqSpec as default 
		
	}
	@Test(enabled=true,priority=2)
	public void getResource() {
		
		Response res = RestAssured.get();
		
		res.prettyPeek();
		System.out.println("-----------------fetch the resources successfully---------------");
		
		Assert.assertEquals(res.statusCode(), 200,"Status code is not matching");
	}
	
	@Test(enabled=true,priority=1)
	public void createResource() {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "4th name");
		jsonObject.put("age", 30);
		
        // Use the default request specification and add the request body
		// inherit the default base URI, base path, and content type set in the @BeforeClass method. (default setting)
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.body(jsonObject.toJSONString());
		
		Response res = reqSpec.post();
		
		res.prettyPrint();
		
		System.out.println("-----------------------new resource created-----------------");
		
		Assert.assertEquals(res.statusCode(), 201,"Status coe is not as expected");
	}
	
	@Test(priority=1,enabled=false)
	public void updateResource() {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "updatedNameeditedagain");
		jsonObject.put("age", 39);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.body(jsonObject.toJSONString());
		Response res = reqSpec.put("/4");
		
		res.prettyPrint();
		
		System.out.println("-------------------targeted resource updated----------------------------");
		
		Assert.assertEquals(res.statusCode(), 200,"update status is not matching");
		
	}
	
	@Test(priority=1,enabled=false)
	public void deleteResource() {
		
		Response res = RestAssured.delete("/7");
		res.prettyPrint();
		
		
		Assert.assertEquals(res.statusCode(), 200,"delete status code is not matching");
		
		System.out.println("---------------------------targeted resource deleted-------------");
		
	}
	
		
}
