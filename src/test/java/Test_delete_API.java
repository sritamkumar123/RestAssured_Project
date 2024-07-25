import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Test_delete_API {
	
	
    @Test
	void delete_api() {
    	baseURI="https://reqres.in/api/users/2";
    	given().
    	when().delete().
    	then().statusCode(204).log().all();
    	
		
	}
}
