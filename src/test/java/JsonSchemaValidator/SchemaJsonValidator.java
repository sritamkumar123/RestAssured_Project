package JsonSchemaValidator;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaJsonValidator {

	@Test
	public void schemaValidation() {
		
		File obj = new File("C:\\Users\\SRITAMLINKAN\\Downloads\\Keys\\jsonSchemaValidator.json\\");
		 
		RestAssured.given()
		.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON)
		.when().get("/2").then().assertThat()
		.statusCode(200)
		.body("data",Matchers.notNullValue())
		.body(JsonSchemaValidator.matchesJsonSchema(obj));
	}
}
