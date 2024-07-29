package jsonArraySerialization;

import java.util.ArrayList;
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
import pojoPackage.pojoClass;

public class jsonArrayToJsonString {

	@Test
	public void jsonArrayToString() throws JsonProcessingException {
		pojoClass obj1 = new pojoClass();
		obj1.setFirstName("Sritam");
		obj1.setLastname("kumar");
		obj1.setGender("male");
		obj1.setAge(34);
		obj1.setSalary(877868.88);
		
		pojoClass obj2 = new pojoClass();
		obj2.setFirstName("dilip");
		obj2.setLastname("sulemi");
		obj2.setGender("male");
		obj2.setAge(38);
		obj2.setSalary(987868.88);
		
		pojoClass obj3 = new pojoClass();
		obj3.setFirstName("new");
		obj3.setLastname("name");
		obj3.setGender("male");
		obj3.setAge(40);
		obj3.setSalary(987868.88);
		
		List<pojoClass> listOfObject = new ArrayList<pojoClass>();
		listOfObject.add(obj1);
		listOfObject.add(obj2);
		listOfObject.add(obj3);
		
		//Converting Class object to jsonString
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfObject);

		System.out.println(jsonString);
		System.out.println("---------------------Json array with json object string created successfully---------------");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON)
		.body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		System.out.println("---------class object added to array then converted into json string and then used in the request payload-------------");
		
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
		
		
		
		
		// using new request >> get the response >> then deserialize it (means convert the json string to jave class object)
		
		RequestSpecification reqSpec1 = RestAssured.given();
		reqSpec1.baseUri("http://httpbin.org").basePath("/post")
		.contentType(ContentType.JSON)
		.body(jsonString);
		
		Response res1 = reqSpec1.post();
		res1.prettyPrint();
		System.out.println("---------------------resource created successfully using json string -----------------------");
		Assert.assertEquals(res1.statusCode(), 200,"Status code is not matching");
		
		// Get the jsonString from the response and will convert it to java class object (deserialization)
		JsonPath jsonPath = res1.jsonPath();
		
		List <pojoClass> getAllList = jsonPath.getList("json",pojoClass.class);
		
		for(pojoClass list : getAllList) {
			System.out.println(list.getFirstName()+" "+list.getLastname());
			System.out.println(list.getAge()+" "+list.getSalary());
			System.out.println(list.getGender());
			
		}
		
		
	}
}
