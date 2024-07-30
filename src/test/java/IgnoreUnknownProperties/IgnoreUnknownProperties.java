package IgnoreUnknownProperties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IgnoreUnknownProperties {

	@Test
	public void ignoreUnknownFromResponse() throws JsonProcessingException {
		PojoProperties mainObj = new PojoProperties();
		mainObj.setFirstname("Sritam");
		mainObj.setLastname("Kumar");
		mainObj.setSalary(7987.87);
		mainObj.setAge(67);
		
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mainObj);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON).body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
		String resString = res.asString();
		
		PojoProperties resObj = objMapper.readValue(resString, PojoProperties.class);
		
		System.out.println("Firstname is: "+resObj.getFirstname());
	}
}
