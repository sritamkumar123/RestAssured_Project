import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Test_put_method {
	
	@Test
	void putTest() {
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Sritam");
		jsonData.put("job","EditDeveops");
		baseURI="https://reqres.in/api/users/735";
		given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
	    body(jsonData.toJSONString()).
	    when().put().
	    then().statusCode(200).log().all();
	    	
	}

}
