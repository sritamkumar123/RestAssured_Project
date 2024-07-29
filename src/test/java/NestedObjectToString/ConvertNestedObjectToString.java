package NestedObjectToString;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConvertNestedObjectToString {

	@Test
	public void nestedObjToString() throws JsonProcessingException {
		
		PojoClassNew obj1 = new PojoClassNew();
		obj1.setFirstname("Sritam");
		obj1.setLastname("Kumar");
		obj1.setGender("Male");
		obj1.setAge(34);
		obj1.setSalary(767868.98);
		
		PojoClassNew01 objNew = new PojoClassNew01();
		objNew.setStreet("charodhi");
		objNew.setCity("Ahemadabad");
		objNew.setState("gujurat");
		objNew.setPin(987897);
		
		// Nested object added into the object
		obj1.setAddress(objNew);
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj1);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON).body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 201,"status code is not matching");
		
		
	}
}
