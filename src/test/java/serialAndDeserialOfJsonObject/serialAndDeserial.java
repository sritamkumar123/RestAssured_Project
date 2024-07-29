package serialAndDeserialOfJsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoPackage.pojoClass;

public class serialAndDeserial {

	@Test
	public void classObjToJsonString() throws JsonProcessingException {
		pojoClass objPojo = new pojoClass();
		objPojo.setFirstName("sritam");
		objPojo.setLastname("kumar");
		objPojo.setGender("male");
		objPojo.setAge(45);
		objPojo.setSalary(878678.00);
		
		// converting class object to json string >>Serialize
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objPojo);
		
		System.out.println(jsonString);
		
		System.out.println("--------------------Json string printed successfully------------------");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("http://httpbin.org").basePath("/post")
		.contentType(ContentType.JSON)
		.body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		
		System.out.println("------------------------json string associated sucessfullly with post http request-----------------------");
		
		Assert.assertEquals(res.statusCode(), 200,"Status code is not matching");
		
		
		// convert json string to class object >> Deserialize
		pojoClass objPojo1 = objMapper.readValue(jsonString, pojoClass.class);
		System.out.println("Firstname : "+objPojo1.getFirstName());
		System.out.println("Lastname : "+objPojo1.getLastname());
		System.out.println("Age : "+objPojo1.getAge());
		System.out.println("Gender : "+objPojo1.getGender());
		System.out.println("salary : "+objPojo1.getSalary());
	}
}
