package ElliminateEmptyDefaultNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateObjectToString {
	
	@Test
	public void sampleObject() throws JsonProcessingException {
		
		RemoveDefaultEmptyNullProperties firstObj = new RemoveDefaultEmptyNullProperties();
		firstObj.setFirstname("firstname");
		firstObj.setLastname("Lastname");
		//firstObj.setAge(43);
		//firstObj.setLocation("my location");
		firstObj.setSalary(678.98);
		
		String [] array = new String[5];//{};
		array[0]="first";
		array[1]="second";
		array[2]="third";
		firstObj.setCourses(array);
		
		List<String> listObj = new ArrayList<String>();
		
		listObj.add("first element");
		listObj.add("second element");
		listObj.add("third element");
		
		firstObj.setList(listObj);
		
		Map<String,String> mapObj = new HashMap<String,String>();
		mapObj.put("key", "value");
		mapObj.put("key1", "value1");
		mapObj.put("key2", "value2");
		
		firstObj.setMembers(mapObj);
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(firstObj);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON).body(jsonString);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		

		}

}
