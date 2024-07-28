import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class jsonArraywithJsonObject {
	
	@Test(enabled=false)
	public void jsonArrayWithObject() {
		
		//created json object with expected properties
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", "sritam");
		jsonObject.put("lastname", "Kumar");
		jsonObject.put("age", 21);
		jsonObject.put("salary", 866787.98);
		
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("firstname", "sritam01");
		jsonObject1.put("lastname", "Kumar01");
		jsonObject1.put("age", 22);
		jsonObject1.put("salary", 987987.98);
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("firstname", "sritam02");
		jsonObject2.put("lastname", "Kumar02");
		jsonObject2.put("age", 23);
		jsonObject2.put("salary", 887987.98);
		
		//Added json objet into the json array
		JSONArray jsonArray = new JSONArray ();
		jsonArray.add(jsonObject);
		jsonArray.add(jsonObject1);
		jsonArray.add(jsonObject2);
		
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in").basePath("/api/user")
		.contentType(ContentType.JSON).body(jsonArray);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
	}
	@Test
	public void jsonObjectWithArrayList() {
		
		Map <String, Object> object1 = new HashMap<String,Object>();
		object1.put("firstname","sritamNew");
		object1.put("lastname","KumarNew");
		object1.put("age",25);
		object1.put("salary",7987979.98);
		
		Map<String,Object> object2= new HashMap<String,Object>();
		object2.put("firstname", "sritamnew01");
		object2.put("lastname", "Kumar01");
		object2.put("age", 26);
		object2.put("salary", 9879879.98);
		
		Map<String,Object> object3= new HashMap<String,Object>();
		object3.put("firstname", "sritam02");
		object3.put("lastname", "kumar02");
		object3.put("age", 98);
		object3.put("salary", 786876.89);
		
		List<Object> jsonList = new ArrayList<Object>();
		jsonList.add(object1);
		jsonList.add(object2);
		jsonList.add(object3);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in").basePath("/api/user")
		.contentType(ContentType.JSON).body(jsonList);
		
		Response res = reqSpec.post();
		
		res.prettyPrint();
		
		Assert.assertEquals(res.statusCode(), 201,"Status code is not matching");
		
	}

}
