import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Test_post_Method {
	@Test (priority=1)
	void test03() {
		JSONObject jsondata = new JSONObject();
		jsondata.put("name", "sritam");
		jsondata.put("job", "Devops");
		
		baseURI="https://reqres.in/api/users/2";
		given().header("Content-type","application/json").
		//contentType(ContentType.JSON).
		body(jsondata.toJSONString()).
		when().post().
		then().statusCode(201).log().all();
		
	}
	
	@Test (priority=2)
	void patch_test() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "new name");
		jsonData.put("job", "new job");
		
		baseURI= "https://reqres.in/api/users/2";
		given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		body(jsonData.toJSONString()).
		when().patch().
		then().statusCode(200).log().all();
	}
	

}
