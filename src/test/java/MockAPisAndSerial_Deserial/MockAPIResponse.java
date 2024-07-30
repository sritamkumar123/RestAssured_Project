package MockAPisAndSerial_Deserial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockAPIResponse {

	@Test
	public void mockAPiResponse() throws JsonMappingException, JsonProcessingException {
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://run.mocky.io/v3/f28fab0b-629f-4ff1-8ecf-d34d9d83bed8")
		.contentType(ContentType.JSON);
		
		Response res = reqSpec.get();
		res.prettyPrint();
		
		String resString = res.asString();
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		PojoClass obj = objMapper.readValue(resString, PojoClass.class);
		System.out.println(obj.getColor());
		
		List<PojoClass2> lists = obj.getArrayObj();
		for(PojoClass2 objNew : lists) {
			System.out.println("name: "+objNew.getName()+"\t"+"lastname: "+objNew.getLastname());
		}
		
		PojoClass1 bodyObj = obj.getObject();
		System.out.println("a: "+ bodyObj.getA());
		System.out.println("b: "+ bodyObj.getc());
		
		  Map<String, String> map = obj.getMap();
	        for (Map.Entry<String, String> entry : map.entrySet()) {
	            System.out.println("Map Key: " + entry.getKey() + ", Value: " + entry.getValue());
	        }
	        
	     List<Integer> array = obj.getArray();
	     
	    // System.out.println(array);
	     
	     for(int arr: array) {
	    	 System.out.println(arr);
	     }
		
		
		
		
	}
}
