package ComplexObjectToString;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConvertObjectToString {

	@Test
	public void nestedObjectTostring() throws JsonProcessingException {
		
		PojoParentObjectClass mainObj = new PojoParentObjectClass();
		mainObj.setStreet("1st Street");
		mainObj.setState("new state");
		mainObj.setCompanyname("sample company");
		mainObj.setCity("sample city");
		mainObj.setPin(78979);
		
		List<String> listObj = new ArrayList<String>();
		listObj.add("TCS");
		listObj.add("Wipro");
		listObj.add("cognizent");
		mainObj.setBankaccount(listObj);
		
		List<Pojo2ndLevelObjectClass> objCollectList = new ArrayList<Pojo2ndLevelObjectClass>();
		
		//need to create a new Pojo2ndLevelObjectClass and AddressNestedObjectToString instance for each employee, as reusing the same instance would overwrite the previous values.
		
		Pojo2ndLevelObjectClass employeeObj1 = new Pojo2ndLevelObjectClass();
		employeeObj1.setFirstname("Sritam");
		employeeObj1.setLastname("kumar");
		employeeObj1.setAge(76);
		employeeObj1.setGender("male");
		employeeObj1.setSalary(98979.98);
		
		AddressNestedObjectToString addressObj1 = new AddressNestedObjectToString();
		
		addressObj1.setStreet("new street");
		addressObj1.setCity("new city");
		addressObj1.setState("new state");
		addressObj1.setPin(89080);
		
		employeeObj1.setAddress(addressObj1);
		
		
		objCollectList.add(employeeObj1);
		
		Pojo2ndLevelObjectClass employeeObj2 = new Pojo2ndLevelObjectClass();
		employeeObj2.setFirstname("Sritam Kumar");
		employeeObj2.setLastname("Rout");
		employeeObj2.setAge(77);
		employeeObj2.setGender("female");
		employeeObj2.setSalary(89797.98);
		
		AddressNestedObjectToString addressObj2 = new AddressNestedObjectToString();
		
		addressObj2.setStreet("new street new");
		addressObj2.setCity("new city added");
		addressObj2.setState("new state added");
		addressObj2.setPin(89089);
		
		employeeObj2.setAddress(addressObj2);
		
		
		objCollectList.add(employeeObj2);
		
		mainObj.setEmployees(objCollectList);
		
		// converting class object to json string 
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonBodyString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mainObj);
		//System.out.println(jsonBodyString);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.contentType(ContentType.JSON).body(jsonBodyString);
		
		Response res = reqSpec.post();
		
		res.prettyPrint();
		
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
	}
}
