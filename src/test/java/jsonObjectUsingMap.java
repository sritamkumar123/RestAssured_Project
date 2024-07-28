import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class jsonObjectUsingMap {
	
	@Test(enabled=false)
	public void createSimpleObject() {
//		curl -X POST \
//		  https://restful-booker.herokuapp.com/auth \
//		  -H 'Content-Type: application/json' \
//		  -d '{
//		    "username" : "admin",
//		    "password" : "password123"
//		}'
	
		Map<String, String> jsonObject = new HashMap<String,String>();
		jsonObject.put("username", "admin");
		jsonObject.put("password", "password123");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://restful-booker.herokuapp.com").basePath("/auth")
		.contentType(ContentType.JSON).body(jsonObject);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		
		Assert.assertEquals(res.statusCode(), 200, "Status code is not matching");
		
		JsonPath jsonPath = res.jsonPath();
		String authToken = jsonPath.get("token");
		System.out.println("\n");
		System.out.println("Auth token is :"+authToken);
		
	}
	@Test
	public void complexJsonObject() {
		
//		{
//		    "firstname": "sritam",
//		    "hobbies": [
//		        "gaming",
//		        "learning new skills",
//		        "watching comdey videos",
//		        "trading"
//		    ],
//		    "IsMarried": false,
//		    "techSkill": {
//		        "Language": [
//		            "java",
//		            "JavaScript"
//		        ],
//		        "WebAutomation": [
//		            "selenium",
//		            "Playwright"
//		        ],
//		        "APIAutomation": [
//		            "RestAsssured",
//		            "Playwright"
//		        ]
//		    },
//		    "salary": 78687686.89,
//		    "age": 28,
//		    "lastname": "Kumar"
//		}
		
		Map<String, Object> mainObject = new HashMap<String, Object>();
		mainObject.put("firstname","sritam");
		mainObject.put("lastname", "Kumar");
		mainObject.put("age", 28);
		mainObject.put("salary", 78687686.89);
		mainObject.put("IsMarried", false);
		
		ArrayList<String> hobbyList = new ArrayList<String>();
		hobbyList.add("gaming");
		hobbyList.add("learning new skills");
		hobbyList.add("watching comdey videos");
		hobbyList.add("trading");
		
		mainObject.put("hobbies", hobbyList); // added the array into the object
		
		ArrayList <String> languageList = new ArrayList<String>(); // arraylist created and added value in it
		languageList.add("java");
		languageList.add("JavaScript");
		
		ArrayList <String> webAutomationList = new ArrayList<String>();
		webAutomationList.add("selenium");
		webAutomationList.add("Playwright");
		
		ArrayList <String> apiAutomationList = new ArrayList<String>();
		apiAutomationList.add("RestAsssured");
		apiAutomationList.add("Playwright");
		
		Map<String,Object> techObject = new HashMap<String,Object>();
		
		techObject.put("Language", languageList); // added array into a object
		techObject.put("WebAutomation",webAutomationList); // added array into a object
		techObject.put("APIAutomation", apiAutomationList); // added array into a object
		
		mainObject.put("techSkill", techObject); // object added into the parent object
		
		
		
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in").basePath("/api/users")
		.contentType(ContentType.JSON).body(mainObject);
		
		Response res = reqSpec.post();
		
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200,"Status code is not matching");
	}

}
