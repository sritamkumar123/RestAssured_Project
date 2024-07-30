package IgnoreFiledsInPoJo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SerailandDeserialOfObject {
	
	@Test
	public void ignoreProperties() throws JsonProcessingException {
		ObjectProperties obj = new ObjectProperties();
		obj.setFirstname("Sritam");
		obj.setLastname("Lastname");
		obj.setFullname("Sritam Kumar ");
		obj.setAge(34);
		obj.setMarried(true);
		obj.setSalary(76578.76);
		
		//Serialization >> Converting java object to json String
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON).body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
		String string = "{\n" +
                "    \"firstname\": \"Sritam\",\n" +
                "    \"age\": 34,\n" +
                "    \"isMarried\": false,\n" +
                "    \"married\": false\n" +
                "}";
		
		ObjectProperties newObj=objMapper.readValue(string, ObjectProperties.class);
		
		System.out.println("First name: "+newObj.getFirstname());
		System.out.println("Is mirred:" + newObj.isMarried());
		
	}

}
